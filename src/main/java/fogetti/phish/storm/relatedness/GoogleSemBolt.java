package fogetti.phish.storm.relatedness;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.client.GoogleTrends;
import fogetti.phish.storm.client.GoogleTrends.Builder;
import fogetti.phish.storm.client.IRequest;
import okhttp3.OkHttpClient;
import redis.clients.jedis.JedisCommands;

public abstract class GoogleSemBolt extends AbstractRedisBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(GoogleSemBolt.class);
    private final File proxies;
    private final IRequest request;
    private List<String> proxyList;
    protected long timeout;

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
	
	public abstract OkHttpClient buildClient();

	@Override
	public void execute(Tuple input) {
		String segment = input.getStringByField("word");
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
			collector.emit(input, new Values(new HashSet<>(lookupValue), segment, url));
			collector.ack(input);
        } catch (NullPointerException e) {
            logger.error("Google Trend request failed", e);
            collector.fail(input);
		} catch (InterruptedException e) {
			logger.warn("Interrupted while sleeping");
			collector.fail(input);
			Thread.currentThread().interrupt();
		} catch (IOException e) {
            logger.error("Google Trend request failed", e);
            collector.fail(input);
        } finally {
			returnInstance(jedisCommand);
		}
	}

	private Set<String> calculateSearches(String segment) throws IOException {
		HashSet<String> result = new HashSet<>();
		int nextPick = new Random().nextInt(proxyList.size());
		String nextProxy = proxyList.get(nextPick);
		String[] hostAndPort = nextProxy.split(":");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostAndPort[0],Integer.parseInt(hostAndPort[1])));
        Builder builder = new GoogleTrends.Builder(request, proxy, segment)
                .setHttpClient(buildClient())
                .setConnectTimeout((int)timeout)
                .setSocketTimeout((int)timeout);
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
	    declarer.declare(new Fields("googletrends", "word", "url"));
	}
}
