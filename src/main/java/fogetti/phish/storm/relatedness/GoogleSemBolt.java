package fogetti.phish.storm.relatedness;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import fogetti.phish.storm.client.GoogleTrends;
import fogetti.phish.storm.client.GoogleTrends.Builder;
import fogetti.phish.storm.client.IRequest;
import redis.clients.jedis.JedisCommands;

public class GoogleSemBolt extends AbstractRedisBolt {

    public static final String RETRY_STREAM = "retry";
    public static final String SUCCESS_STREAM = "success";
	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(GoogleSemBolt.class);
    private final File proxies;
    private final IRequest request;
    private List<String> proxyList;
    private long timeout;

	public GoogleSemBolt(JedisPoolConfig config, File proxies, IRequest request) {
		super(config);
        this.proxies = proxies;
        this.request = request;
        this.proxyList = new ArrayList<>();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		timeout = (Long)stormConf.get("timeout");
		try {
            proxyList = Files.readAllLines(proxies.toPath());
        } catch (IOException e) {
            logger.error("Preparing the Google SEM bolt failed", e);
        }
	}

	@Override
	public void execute(Tuple input) {
		String segment = input.getStringByField("segment");
		String url = input.getStringByField("url");
		JedisCommands jedisCommand = null;
		try {
			TimeUnit.MILLISECONDS.sleep(1000);

			jedisCommand = getInstance();
			String key = REDIS_SEGMENT_PREFIX + segment;
			Set<String> lookupValue = jedisCommand.smembers(key);
			if (lookupValue == null || lookupValue.isEmpty()) {
				logger.debug("Cached Google result not found for [segment={}]", segment);
				lookupValue = calculateSearches(segment);
			} else {
				logger.debug("Cached Google result found for [segment={}]", segment);
			}
			collector.emit(SUCCESS_STREAM, input, new Values(new HashSet<>(lookupValue), segment, url));
			collector.ack(input);
        } catch (NullPointerException e) {
            logger.error("Google Trend request failed", e);
            collector.fail(input);
		} catch (InterruptedException e) {
			logger.warn("Interrupted while sleeping");
			collector.fail(input);
			Thread.currentThread().interrupt();
		} catch (IOException e) {
            logger.error("Google Trend request failed", e.getMessage());
            collector.emit(RETRY_STREAM, input, new Values(new HashSet<>(), segment, url));
        } finally {
			returnInstance(jedisCommand);
		}
	}

	private Set<String> calculateSearches(String segment) throws IOException {
		HashSet<String> result = new HashSet<>();
		int nextPick = new Random().nextInt(proxyList.size());
		String nextProxy = proxyList.get(nextPick);
		String[] hostAndPort = nextProxy.split(":");
		HttpHost httpHost = new HttpHost(hostAndPort[0],Integer.parseInt(hostAndPort[1]));
        Builder builder = new GoogleTrends.Builder(request, httpHost, segment);
        builder = builder.setConnectTimeout((int)timeout);
        builder = builder.setSocketTimeout((int)timeout);
		GoogleTrends client = builder.build();
		result.addAll(client.topSearches());
		result.addAll(client.risingSearches());
        logger.debug("Google Trend request succeeded");
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
		// declarer.declare(new Fields("googletrends", "segment", "url"));
		declarer.declareStream(SUCCESS_STREAM, new Fields("googletrends", "segment", "url"));
		declarer.declareStream(RETRY_STREAM, new Fields("googletrends", "segment", "url"));
	}
}
