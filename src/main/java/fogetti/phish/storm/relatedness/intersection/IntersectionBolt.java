package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.util.Set;

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
	private final BloomFilter bloomfilter;
	
	// TODO: add a map where key is an element of either RDurl or REMurl
	// TODO: and the value is a class holding TERM, (RELrem and ASrem) or (RELrd and ASrd) for each key

	public IntersectionBolt(BloomFilter bloomfilter) {
		this.bloomfilter = bloomfilter;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		@SuppressWarnings("unchecked")
		Set<String> termSet = (Set<String>) input.getValue(0);
		String url = input.getStringByField("url");
		logger.info("Computing intersections for [url={}] and [termset={}]", url, termSet);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@Override
	public void onMessage(String channel, String message) {
		try {
			// TODO: take RDurl and REMurl
			// TODO: calculate ASrem
			// TODO: calculate RELrem
			// TODO: calculate ASrd
			// TODO: calculate RELrd
			ObjectMapper mapper = new ObjectMapper();
			AckResult result = mapper.readValue(message, AckResult.class);
			bloomfilter.run();
			logger.info("Message [{}] intersected", message);
		} catch (IOException e) {
			logger.info("Message [{}] failed", message, e);
		}
	}
}