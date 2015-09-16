package fogetti.phish.storm.relatedness;

import org.freaknet.gtrends.api.GoogleTrendsClient;

import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;

public class GoogleRisingBolt extends GoogleBolt {

	private static final long serialVersionUID = -7853035759259306137L;

	public GoogleRisingBolt(GoogleTrendsClient client) {
		super(client);
	}

	@Override
	protected String section() {
		return "Rising searches";
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("risingsearches"));
	}

}
