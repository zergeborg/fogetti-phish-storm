package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.db.JedisCallback;
import fogetti.phish.storm.relatedness.AckResult;

public class IntersectionBolt extends BaseBasicBolt implements JedisCallback {

	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private static final Map<String, URLSegments> segmentindex = new ConcurrentHashMap<>();
	private final IntersectionAction bloomfilter;
	
	public IntersectionBolt(IntersectionAction bloomfilter) {
		this.bloomfilter = bloomfilter;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		@SuppressWarnings("unchecked")
		Set<String> termset = (Set<String>) input.getValue(0);
		String segment = input.getStringByField("segment");
		String url = input.getStringByField("url");
		updateSegmentIndex(termset, segment, url);
		logger.info("Segment index updated with [url={}], [segment={}] and [termset={}]", url, segment, termset);
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
			bloomfilter.run();
			logger.info("Message [{}] intersected", message);
		} catch (IOException e) {
			logger.info("Message [{}] failed", message, e);
		}
	}

	private IntersectionResult initIntersectionResult(AckResult result) {
		URLSegments segments = segmentindex.get(result.URL);
		Map<String, Collection<String>> REMTermindex = segments.getREMTerms(result);
		Map<String, Collection<String>> RDTermindex = segments.getRDTerms(result);
		segments.removeIf(termEntry -> REMTermindex.containsKey(termEntry.getKey()));
		segments.removeIf(termEntry -> RDTermindex.containsKey(termEntry.getKey()));
		IntersectionResult intersection = new IntersectionResult(RDTermindex,REMTermindex);
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