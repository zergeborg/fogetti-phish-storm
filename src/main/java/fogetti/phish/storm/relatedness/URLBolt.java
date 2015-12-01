package fogetti.phish.storm.relatedness;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class URLBolt extends BaseRichBolt {

	private static final long serialVersionUID = 7435926978740146015L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	private OutputCollector collector;

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		String segment = tuple.getStringByField("word");
		String url = tuple.getStringByField("url");
		logger.trace("Received [{}]", segment);
		collector.emit(tuple, new Values(segment, url));
		collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("segment", "url"));
	}

}
