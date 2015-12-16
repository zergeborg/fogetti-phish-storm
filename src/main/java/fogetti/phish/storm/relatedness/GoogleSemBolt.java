package fogetti.phish.storm.relatedness;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.freaknet.gtrends.api.GoogleAuthenticator;
import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.freaknet.gtrends.api.GoogleTrendsCsvParser;
import org.freaknet.gtrends.api.GoogleTrendsRequest;
import org.freaknet.gtrends.api.exceptions.GoogleAuthenticatorException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.JedisCommands;

public class GoogleSemBolt extends AbstractRedisBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(GoogleSemBolt.class);

	private GoogleTrendsClient client;
	private final String uname;
	private final String pword;

	public GoogleSemBolt(String uname, String pword, JedisPoolConfig config) {
		super(config);
		this.uname = uname;
		this.pword = pword;
	}

	public GoogleSemBolt(GoogleTrendsClient client, JedisPoolConfig config) {
		super(config);
		this.uname = "";
		this.pword = "";
		this.client = client;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		this.client = getClient();
	}

	GoogleTrendsClient getClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		GoogleAuthenticator authenticator = new GoogleAuthenticator(uname, pword, httpClient);
		return new GoogleTrendsClient(authenticator, httpClient);
	}

	@Override
	public void execute(Tuple input) {
		String segment = input.getStringByField("segment");
		String url = input.getStringByField("url");
		JedisCommands jedisCommand = null;
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
			GoogleTrendsRequest request = new GoogleTrendsRequest(segment);

			jedisCommand = getInstance();
			Set<String> lookupValue = jedisCommand.smembers(segment);
			if (lookupValue == null || lookupValue.isEmpty()) {
				logger.debug("Cached Google result not found for [segment={}]", segment);
				String csvresult = client.execute(request);
				lookupValue = calculateSearches(csvresult);
				logger.trace("Result [{}]", csvresult);
			} else {
				logger.debug("Cached Google result found for [segment={}]", segment);
			}
			collector.emit(input, new Values(new HashSet<>(lookupValue), segment, url));
			collector.ack(input);
		} catch (GoogleTrendsClientException e) {
			if (e.getCause() instanceof GoogleAuthenticatorException) {
				logger.error("Google Trend request failed. Could not login");				
			} else {
				logger.error("Google Trend request failed", e);
			}
		} catch (GoogleTrendsRequestException e) {
			logger.error("Google Trend request failed", e);
		} catch (ConfigurationException e) {
			logger.error("Google Trend request failed", e);
		} catch (InterruptedException e) {
			logger.warn("Interrupted while sleeping");
			Thread.currentThread().interrupt();
		} finally {
			returnInstance(jedisCommand);
		}
	}

	private Set<String> calculateSearches(String searchresult) throws ConfigurationException {
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
			result.add(line.split(",")[0]);
		}
		return result;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("googletrends", "segment", "url"));
	}

}
