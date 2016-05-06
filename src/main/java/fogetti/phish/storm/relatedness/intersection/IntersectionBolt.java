package fogetti.phish.storm.relatedness.intersection;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.db.JedisCallback;
import fogetti.phish.storm.db.JedisListener;
import fogetti.phish.storm.relatedness.AckResult;
import redis.clients.jedis.JedisCommands;

public class IntersectionBolt extends AbstractRedisBolt implements JedisCallback {

	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private static final Map<String, URLSegments> segmentindex = new ConcurrentHashMap<>();
	private final IntersectionAction intersectionAction;
	private JedisPoolConfig config;
	
	public IntersectionBolt(IntersectionAction intersectionAction, JedisPoolConfig config) {
		super(config);
		this.intersectionAction = intersectionAction;
		this.config = config;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		JedisListener listener = new JedisListener(config, "phish", this);
		new Thread(listener, "subscriberThread").start();
	}

	@Override
	public void execute(Tuple input) {
		@SuppressWarnings("unchecked")
		Set<String> termset = (Set<String>) input.getValue(0);
		String segment = input.getStringByField("segment");
		save(segment, termset);
		String url = input.getStringByField("url");
		updateSegmentIndex(termset, segment, url);
		logger.debug("Segment index updated with [url={}], [segment={}] and [termset={}]", url, segment, termset);
		collector.ack(input);
	}

	private void save(String segment, Set<String> termset) {
		JedisCommands jedis = null;
		try {
	        jedis = getInstance();
	        if (!jedis.exists(segment) && !termset.isEmpty()) {
	            String key = REDIS_SEGMENT_PREFIX + segment;
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
			logIntersectionResult(intersection);
			intersectionAction.run();
			logger.info("Message [{}] intersected", message);
		} catch (IOException e) {
			logger.info("Message [{}] failed", message, e);
		}
	}

	private IntersectionResult initIntersectionResult(AckResult result) {
		URLSegments segments = segmentindex.get(result.URL);
		Map<String, Collection<String>> MLDTermindex = segments.getMLDTerms(result);
		Map<String, Collection<String>> MLDPSTermindex = segments.getMLDPSTerms(result);
		Map<String, Collection<String>> REMTermindex = segments.getREMTerms(result);
		Map<String, Collection<String>> RDTermindex = segments.getRDTerms(result);
		segments.removeIf(termEntry -> REMTermindex.containsKey(termEntry.getKey()));
		segments.removeIf(termEntry -> RDTermindex.containsKey(termEntry.getKey()));
		IntersectionResult intersection = new IntersectionResult(RDTermindex,REMTermindex,MLDTermindex,MLDPSTermindex, new WrappedRequest(), result.URL);
		intersection.init();
		return intersection;
	}

	private void logIntersectionResult(IntersectionResult intersection) {
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
					+ "RANKING={}]",
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
					intersection.RANKING());
	}

}