package fogetti.phish.storm.relatedness;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class IntersectionBolt extends BaseBasicBolt {

	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		@SuppressWarnings("unchecked")
		Set<String> termSet = (Set<String>) input.getValue(0);
		logger.trace("Computing intersections for {}", termSet);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}
