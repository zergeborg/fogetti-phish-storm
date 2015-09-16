package fogetti.phish.storm.relatedness;

import java.util.HashSet;
import java.util.Map;

import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.freaknet.gtrends.api.GoogleTrendsCsvParser;
import org.freaknet.gtrends.api.GoogleTrendsRequest;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public abstract class GoogleBolt extends BaseRichBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	
	private final GoogleTrendsClient client;
	private OutputCollector _collector;

	public GoogleBolt(GoogleTrendsClient client) {
		this.client = client;
	}

	protected abstract String section();

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		String word = input.getString(0);
		try {
			GoogleTrendsRequest request = new GoogleTrendsRequest(word);
			String csvresult = client.execute(request);
			_collector.emit(input, new Values(calculateSearches(csvresult)));
			_collector.ack(input);
			logger.info("Result [{}]", csvresult);
		} catch (GoogleTrendsClientException e) {
			logger.error("Google Trend request failed", e);
		} catch (GoogleTrendsRequestException e) {
			logger.error("Google Trend request failed", e);
		} catch (Exception e) {
			logger.error("Google Trend request failed", e);
		}
	}

	private HashSet<String> calculateSearches(String searchresult) throws Exception {
		HashSet<String> result = new HashSet<>();
		GoogleTrendsCsvParser parser = new GoogleTrendsCsvParser(searchresult);
		String searches = parser.getSectionAsString(section(), false);
		String[] lines = searches.split("\\r?\\n");
		for (String line : lines) {
			String terms = line.split(",")[0];
			for (String term : terms.split("\\s")) {
				result.add(term);
			}
		}
		return result;
	}

}
