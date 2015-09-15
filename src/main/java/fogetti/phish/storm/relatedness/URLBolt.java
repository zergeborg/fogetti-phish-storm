package fogetti.phish.storm.relatedness;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class URLBolt extends BaseRichBolt {

	private static final long serialVersionUID = 7435926978740146015L;
	OutputCollector _collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
		_collector.emit(tuple, new Values(tuple.getString(0)));
		_collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

}
