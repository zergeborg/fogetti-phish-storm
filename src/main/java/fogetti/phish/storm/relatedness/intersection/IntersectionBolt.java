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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.db.JedisCallback;
import fogetti.phish.storm.db.JedisListener;
import fogetti.phish.storm.relatedness.AckResult;
import okhttp3.OkHttpClient;
import redis.clients.jedis.Jedis;

public class IntersectionBolt extends AbstractRedisBolt implements JedisCallback {

    private static final String REDIS_INTERSECTION_PREFIX = "intersect:";
	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private final IntersectionAction intersectionAction;
	private final JedisPoolConfig config;
    private final File resultDataFile;
    private int connectTimeout = 5000;
    private int socketTimeout = 5000;
    private ObjectMapper mapper;
    private Decoder decoder;
    private Encoder encoder;
    private final int METRICS_WINDOW = 10;
    private transient CountMetric intersectionSegmentSaved;
    private transient CountMetric intersectionSegmentSkipped;
    private transient CountMetric intersectionIndexKeyUpdated;
    private transient CountMetric intersectionIndexKeyCreated;
    private transient CountMetric intersectionMsgLookupSuccess;
    private transient CountMetric intersectionMsgLookupFailure;
    private transient CountMetric intersectionActionPerformed;
    private transient CountMetric intersectionActionSkipped;
    private transient CountMetric intersectionActionLogged;
    private transient CountMetric intersectionActionSaved;

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
		this.mapper = new ObjectMapper();
        intersectionSegmentSaved = new CountMetric();
        context.registerMetric("int-segment-saved",
                               intersectionSegmentSaved,
                               METRICS_WINDOW);
        intersectionSegmentSkipped = new CountMetric();
        context.registerMetric("int-segment-skipped",
                               intersectionSegmentSkipped,
                               METRICS_WINDOW);
        intersectionIndexKeyUpdated = new CountMetric();
        context.registerMetric("int-index-key-updated",
                               intersectionIndexKeyUpdated,
                               METRICS_WINDOW);
        intersectionIndexKeyCreated = new CountMetric();
        context.registerMetric("int-index-key-created",
                               intersectionIndexKeyCreated,
                               METRICS_WINDOW);
        intersectionMsgLookupSuccess = new CountMetric();
        context.registerMetric("int-msg-lookup-success",
                               intersectionMsgLookupSuccess,
                               METRICS_WINDOW);
        intersectionMsgLookupFailure = new CountMetric();
        context.registerMetric("int-msg-lookup-failure",
                               intersectionMsgLookupFailure,
                               METRICS_WINDOW);
        intersectionActionPerformed = new CountMetric();
        context.registerMetric("int-action-performed",
                               intersectionActionPerformed,
                               METRICS_WINDOW);
        intersectionActionSkipped = new CountMetric();
        context.registerMetric("int-action-skipped",
                               intersectionActionSkipped,
                               METRICS_WINDOW);
        intersectionActionLogged = new CountMetric();
        context.registerMetric("int-action-logged",
                               intersectionActionLogged,
                               METRICS_WINDOW);
        intersectionActionSaved = new CountMetric();
        context.registerMetric("int-action-saved",
                               intersectionActionSaved,
                               METRICS_WINDOW);
	}

	@Override
	public void execute(Tuple input) {
		Terms terms = (Terms) input.getValue(0);
		String segment = input.getStringByField("word");
		save(input, segment, terms);
		updateSegmentIndex(input, terms, segment, getEncodedShortURL(input));
		collector.ack(input);
	}

    private String getEncodedShortURL(Tuple input) {
        String encodedURL = input.getStringByField("url");
		byte[] decoded = decoder.decode(encodedURL);
		String longURL = new String(decoded, StandardCharsets.UTF_8);
		String URL = StringUtils.substringBeforeLast(longURL, "#");
		String encodedShortURL = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
        return encodedShortURL;
    }

	private void save(Tuple input, String segment, Terms termset) {
        try (Jedis jedis = (Jedis) getInstance()) {
	        String key = REDIS_SEGMENT_PREFIX + segment;
	        if (!jedis.exists(key)) {
	            logger.debug("Saving new segment [segment={}] and [termset={}] to Redis", segment, termset);
	            String termString = mapper.writeValueAsString(termset);
	            jedis.set(key, termString);
	            intersectionSegmentSaved.incr();
	        } else {
                logger.debug("Skipping segment [segment={}] and [termset={}]", segment, termset);
	            intersectionSegmentSkipped.incr();
	        }
		} catch (JsonProcessingException e) {
		    logger.error("Could not save the segment into Redis", e);
		    collector.fail(input);
		}

	}

	private void updateSegmentIndex(Tuple input, Terms terms, String segment, String url) {
	    String key = REDIS_INTERSECTION_PREFIX + url;
	    try (Jedis jedis = (Jedis) getInstance()) {
	        if (jedis.exists(key)) {
	            Map<String, String> rawSegments = jedis.hgetAll(key);
	            URLSegments urlsegments = URLSegments.fromStringMap(rawSegments);
	            urlsegments.put(segment, terms);
                jedis.hmset(key, urlsegments.toStringMap());
                intersectionIndexKeyUpdated.incr();
	        } else {
	            URLSegments urlsegments = new URLSegments();
	            urlsegments.put(segment, terms);
	            jedis.hmset(key, urlsegments.toStringMap());
	            intersectionIndexKeyCreated.incr();
	        }
	        logger.debug("Segment index updated with [url={}], [segment={}] and [termset={}]", url, segment, terms);
        } catch (IOException e) {
            logger.error("Could not update the segment index", e);
            collector.fail(input);
        }
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@Override
	public void onMessage(String channel, String message) {
		try {
			AckResult result = mapper.readValue(message, AckResult.class);
	        URLSegments segments = findSegments(result);
			performIntersection(segments, result, message);
		} catch (Throwable e) {
			logger.info("Message [{}] failed", message, e);
		}
	}

    private URLSegments findSegments(AckResult result) {
        try (Jedis jedis = (Jedis) getInstance()) {
            String encodedURL = encoder.encodeToString(result.URL.getBytes(StandardCharsets.UTF_8));
            String key = REDIS_INTERSECTION_PREFIX + encodedURL;
            Map<String, String> rawSegments = jedis.hgetAll(key);
            URLSegments segments = URLSegments.fromStringMap(rawSegments);
            intersectionMsgLookupSuccess.incr();
            return segments;
        } catch (IOException e) {
            logger.error("Could not find saved segments", e);
            intersectionMsgLookupFailure.incr();
        }
        return null;
    }

    private void performIntersection(URLSegments segments, AckResult result, String message) {
		if (segments != null) {
	        Map<String, Terms> MLDTermindex = segments.getMLDTerms(result);
	        Map<String, Terms> MLDPSTermindex = segments.getMLDPSTerms(result);
	        Map<String, Terms> REMTermindex = segments.getREMTerms(result);
	        Map<String, Terms> RDTermindex = segments.getRDTerms(result);
	        segments.removeIf(termEntry -> REMTermindex.containsKey(termEntry.getKey()));
	        segments.removeIf(termEntry -> RDTermindex.containsKey(termEntry.getKey()));
	        OkHttpClient client = buildClient();
	        IntersectionResult intersection = new IntersectionResult(RDTermindex,REMTermindex,MLDTermindex,MLDPSTermindex, new WrappedRequest(), result.URL, client);
	        intersection.init();
	        logIntersectionResult(intersection, result.URL);
	        saveIntersectionResult(intersection, result.URL);
            intersectionAction.run();
            logger.info("Message [{}] intersected", message);
            intersectionActionPerformed.incr();
		} else {
		    logger.warn("There are no segments for [{}]. Skipping intersection", result.URL);
		    intersectionActionSkipped.incr();
		}
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
		intersectionActionLogged.incr();
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
            intersectionActionSaved.incr();
        } catch (IOException e) {
            logger.error("Writing the result failed", e);
        }
    }

}