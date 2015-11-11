package fogetti.phish.storm.relatedness;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.freaknet.gtrends.api.GoogleAuthenticator;
import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.freaknet.gtrends.api.GoogleTrendsCsvParser;
import org.freaknet.gtrends.api.GoogleTrendsRequest;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class GoogleSemBolt extends BaseRichBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(GoogleSemBolt.class);

	private OutputCollector collector;
	private GoogleTrendsClient client;
	private final String uname;
	private final String pword;

	public GoogleSemBolt(String uname, String pword) {
		this.uname = uname;
		this.pword = pword;
	}
	
	public GoogleSemBolt(GoogleTrendsClient client) {
		this.uname = "";
		this.pword = "";
		this.client = client;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.client = getClient();
	}

	GoogleTrendsClient getClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
	    GoogleAuthenticator authenticator = new GoogleAuthenticator(uname, pword, httpClient);
	    return new GoogleTrendsClient(authenticator, httpClient);
	}

	@Override
	public void execute(Tuple input) {
		String url = input.getStringByField("url");
		try {
			GoogleTrendsRequest request = new GoogleTrendsRequest(url);
			String csvresult = client.execute(request);
			collector.emit(input, new Values(calculateSearches(csvresult)));
			collector.ack(input);
			logger.trace("Result [{}]", csvresult);
		} catch (GoogleTrendsClientException e) {
			logger.error("Google Trend request failed", e);
		} catch (GoogleTrendsRequestException e) {
			logger.error("Google Trend request failed", e);
		} catch (Exception e) {
			logger.error("Google Trend request failed", e);
		}
	}

	private Set<String> calculateSearches(String searchresult) throws Exception {
		HashSet<String> result = new HashSet<>();
		GoogleTrendsCsvParser parser = new GoogleTrendsCsvParser(searchresult);
		String topsearches = parser.getSectionAsString("Top searches", false);
		result.addAll(split(topsearches));
		String risingsearches = parser.getSectionAsString("Rising searches", false);
		result.addAll(split(risingsearches));
		return result;
	}

	protected HashSet<String> split(String searches) {
		HashSet<String> result = new HashSet<>();
		if (StringUtils.isEmpty(searches)) {
			return result;
		}
		String[] lines = searches.split("\\r?\\n");
		for (String line : lines) {
			String terms = line.split(",")[0];
			for (String term : terms.split("\\s")) {
				result.add(term);
			}
		}
		return result;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("googletrends"));
	}

}
