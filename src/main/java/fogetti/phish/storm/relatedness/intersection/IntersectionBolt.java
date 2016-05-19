package fogetti.phish.storm.relatedness.intersection;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.db.JedisCallback;
import fogetti.phish.storm.db.JedisListener;
import fogetti.phish.storm.relatedness.AckResult;
import okhttp3.OkHttpClient;
import redis.clients.jedis.Jedis;

public class IntersectionBolt extends AbstractRedisBolt implements JedisCallback {

	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private static final Map<String, URLSegments> segmentindex = new ConcurrentHashMap<>();
	private final IntersectionAction intersectionAction;
	private final JedisPoolConfig config;
    private final File resultDataFile;
    private int connectTimeout = 5000;
    private int socketTimeout = 5000;
    private Decoder decoder;
    private Encoder encoder;

	public IntersectionBolt(IntersectionAction intersectionAction, JedisPoolConfig config, String resultDataFile) {
		super(config);
		this.intersectionAction = intersectionAction;
		this.config = config;
        this.resultDataFile = new File(resultDataFile);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		JedisListener listener = new JedisListener(config, "phish", this);
		new Thread(listener, "subscriberThread").start();
		this.decoder = Base64.getDecoder();
		this.encoder = Base64.getEncoder();
	}

	@Override
	public void execute(Tuple input) {
		@SuppressWarnings("unchecked")
		Set<String> termset = (Set<String>) input.getValue(0);
		String segment = input.getStringByField("word");
		save(segment, termset);
		String encodedURL = input.getStringByField("url");
		byte[] decoded = decoder.decode(encodedURL);
		String longURL = new String(decoded, StandardCharsets.UTF_8);
		String URL = StringUtils.substringBeforeLast(longURL, "#");
		String encodedShortURL = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
		updateSegmentIndex(termset, segment, encodedShortURL);
        logger.debug("Segment index updated with [url={}], [segment={}] and [termset={}]", URL, segment, termset);
		collector.ack(input);
	}

	private void save(String segment, Set<String> termset) {
		Jedis jedis = null;
		try {
	        jedis = (Jedis) getInstance();
	        String key = REDIS_SEGMENT_PREFIX + segment;
	        if (!jedis.exists(key) && !termset.isEmpty()) {
	            logger.debug("Saving new segment [segment={}] and [termset={}] to Redis", segment, termset);
	        	jedis.sadd(key, termset.toArray(new String[termset.size()]));
	        }
		} finally{
			returnInstance(jedis);
		}

	}

	private void updateSegmentIndex(Set<String> termset, String segment, String url) {
		if (segmentindex.containsKey(url)) {
			segmentindex.get(url).put(segment, termset);
		} else {
			URLSegments urlsegments = new URLSegments();
			urlsegments.put(segment, termset);
			segmentindex.put(url, urlsegments);
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@Override
	public void onMessage(String channel, String message) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			AckResult result = mapper.readValue(message, AckResult.class);
			IntersectionResult intersection = initIntersectionResult(result);
			logIntersectionResult(intersection, result.URL);
			saveIntersectionResult(intersection, result.URL);
			intersectionAction.run();
			logger.info("Message [{}] intersected", message);
		} catch (IOException e) {
			logger.info("Message [{}] failed", message, e);
		}
	}

    private IntersectionResult initIntersectionResult(AckResult result) {
        String encodedURL = encoder.encodeToString(result.URL.getBytes(StandardCharsets.UTF_8));
		URLSegments segments = segmentindex.get(encodedURL);
		Map<String, Collection<String>> MLDTermindex = segments.getMLDTerms(result);
		Map<String, Collection<String>> MLDPSTermindex = segments.getMLDPSTerms(result);
		Map<String, Collection<String>> REMTermindex = segments.getREMTerms(result);
		Map<String, Collection<String>> RDTermindex = segments.getRDTerms(result);
		segments.removeIf(termEntry -> REMTermindex.containsKey(termEntry.getKey()));
		segments.removeIf(termEntry -> RDTermindex.containsKey(termEntry.getKey()));
        OkHttpClient client = buildClient();
		IntersectionResult intersection = new IntersectionResult(RDTermindex,REMTermindex,MLDTermindex,MLDPSTermindex, new WrappedRequest(), result.URL, client);
		intersection.init();
		return intersection;
	}

    private OkHttpClient buildClient() {
        OkHttpClient client
            = new OkHttpClient
                .Builder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

	private void logIntersectionResult(IntersectionResult intersection, String URL) {
		logger.info("[JRR={}, "
					+ "JRA={}, "
					+ "JAA={}, "
					+ "JAR={}, "
					+ "JARRD={}, "
					+ "JARREM={}, "
					+ "CARDREM={}, "
					+ "RATIOAREM={}, "
					+ "RATIORREM={}, "
					+ "MLDRES={}, "
					+ "MLDPSRES={}, "
					+ "RANKING={}, "
					+ "URL={}]",
					intersection.JRR(),
					intersection.JRA(),
					intersection.JAA(),
					intersection.JAR(),
					intersection.JARRD(),
					intersection.JARREM(),
					intersection.CARDREM(),
					intersection.RATIOAREM(),
					intersection.RATIORREM(),
					intersection.MLDRES(),
					intersection.MLDPSRES(),
					intersection.RANKING(),
					URL);
	}

    private void saveIntersectionResult(IntersectionResult intersection, String URL) {
        List<String> lines = Arrays.asList(new String[] {
                String.format(
                "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%d,"
                + "%f,"
                + "%f,"
                + "%d,"
                + "%d,"
                + "%d,"
                + "%s",
                intersection.JRR(),
                intersection.JRA(),
                intersection.JAA(),
                intersection.JAR(),
                intersection.JARRD(),
                intersection.JARREM(),
                intersection.CARDREM(),
                intersection.RATIOAREM(),
                intersection.RATIORREM(),
                intersection.MLDRES(),
                intersection.MLDPSRES(),
                intersection.RANKING(),
                URL)});
        try {
            Files.write(resultDataFile.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            logger.error("Writing the result failed", e);
        }
    }

}