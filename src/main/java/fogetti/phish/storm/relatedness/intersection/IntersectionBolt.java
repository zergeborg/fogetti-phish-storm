package fogetti.phish.storm.relatedness.intersection;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.db.JedisCallback;

public class IntersectionBolt extends BaseBasicBolt implements JedisCallback {

	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private final BloomFilter bloomfilter;
	
	public IntersectionBolt(BloomFilter bloomfilter) {
		this.bloomfilter = bloomfilter;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		@SuppressWarnings("unchecked")
		Set<String> termSet = (Set<String>) input.getValue(0);
		logger.trace("Computing intersections for {}", termSet);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@Override
	public void onMessage(String channel, String message) {
		bloomfilter.run();
	}
}