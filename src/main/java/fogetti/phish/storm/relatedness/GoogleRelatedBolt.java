package fogetti.phish.storm.relatedness;

import java.util.Map;

import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.freaknet.gtrends.api.GoogleTrendsRequest;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class GoogleRelatedBolt extends BaseRichBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	
	private final GoogleTrendsClient client;

	public GoogleRelatedBolt(GoogleTrendsClient client) {
		this.client = client;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
	}

	@Override
	public void execute(Tuple input) {
		String word = input.getString(0);
		try {
			GoogleTrendsRequest request = new GoogleTrendsRequest(word);
			String csvresult = client.execute(request);
			logger.info("Result [{}]", csvresult);
		} catch (GoogleTrendsClientException e) {
			logger.error("Google Trend request failed", e);
		} catch (GoogleTrendsRequestException e) {
			logger.error("Google Trend request failed", e);
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}
