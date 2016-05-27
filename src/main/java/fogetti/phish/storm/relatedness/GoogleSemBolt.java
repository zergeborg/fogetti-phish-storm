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
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.metric.api.CountMetric;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;

import fogetti.phish.storm.client.GoogleTrends;
import fogetti.phish.storm.client.GoogleTrends.Builder;
import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.exception.QuotaLimitException;
import redis.clients.jedis.Jedis;

public abstract class GoogleSemBolt extends AbstractRedisBolt {

	private static final long serialVersionUID = -190657410047851526L;
	private static final Logger logger = LoggerFactory.getLogger(GoogleSemBolt.class);
	private final int METRICS_WINDOW = 60;
	private transient CountMetric googleTrendSuccess;
	private transient CountMetric googleTrendFailure;
    private transient CountMetric googleTrendOverLimit;
    private transient CountMetric googleSegmentLookupCnt;
    private transient CountMetric googleSegmentLookupSuccess;
    private final File proxies;
    private List<String> proxyList;
    private ObjectMapper mapper;
    protected long timeout;
    
    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.INFO); 
    }

	public GoogleSemBolt(JedisPoolConfig config, File proxies) {
		super(config);
        this.proxies = proxies;
        this.proxyList = new ArrayList<>();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		timeout = (Long)stormConf.get("timeout");
		mapper = new ObjectMapper();
		try {
            proxyList = Files.readAllLines(proxies.toPath());
        } catch (IOException e) {
            logger.error("Preparing the Google SEM bolt failed", e);
        }
		googleTrendSuccess = new CountMetric();
		context.registerMetric("google-tr-success",
		                       googleTrendSuccess,
		                       METRICS_WINDOW);
		googleTrendFailure = new CountMetric();
		context.registerMetric("google-tr-failures",
		                       googleTrendFailure,
		                       METRICS_WINDOW);
		googleTrendOverLimit = new CountMetric();
        context.registerMetric("google-tr-over-limit",
                               googleTrendOverLimit,
                               METRICS_WINDOW);
        googleSegmentLookupCnt = new CountMetric();
        context.registerMetric("google-seg-lookup-count",
                               googleSegmentLookupCnt,
                               METRICS_WINDOW);
        googleSegmentLookupSuccess = new CountMetric();
        context.registerMetric("google-seg-lookup-success",
                               googleSegmentLookupSuccess,
                               METRICS_WINDOW);
	}
	
	public abstract WebClient buildClient();

	@Override
	public void execute(Tuple input) {
		String segment = input.getStringByField("word");
		String encodedURL = input.getStringByField("url");
		try (Jedis jedis = (Jedis) getInstance()) {
		    googleSegmentLookupCnt.incr();
			String segments = jedis.get(REDIS_SEGMENT_PREFIX + segment);
			Terms terms = null;
			if (segments != null) {
			    terms = mapper.readValue(segments, Terms.class);
			}
			if (terms == null || terms.terms == null) {
				logger.debug("Cached Google result not found for [segment={}]", segment);
				terms = calculateSearches(segment);
				googleTrendSuccess.incr();
			} else {
				logger.debug("Cached Google result found for [segment={}]", segment);
				googleSegmentLookupSuccess.incr();
			}
			collector.emit(input, new Values(terms, segment, encodedURL));
			logger.debug("Acking [{}]", input);
			collector.ack(input);
		} catch(QuotaLimitException e) {
            logger.error("Google Trend request failed", e);
            collector.fail(input);
            googleTrendOverLimit.incr();
        } catch (NullPointerException e) {
            logger.error("Google Trend request failed", e);
            collector.fail(input);
		} catch (IOException e) {
		    if (e.getMessage() == null) {
                logger.error("Google Trend request failed", e);
		    } else {
		        logger.error("Google Trend request failed [reason={}]", e.getMessage());
		    }
		    googleTrendFailure.incr();
            collector.fail(input);
		}
	}

	private Terms calculateSearches(String segment) throws IOException {
	    Terms result = new Terms();
		int nextPick = new Random().nextInt(proxyList.size());
		String nextProxy = proxyList.get(nextPick);
		String[] hostAndPort = nextProxy.split(":");
        ProxyConfig proxyConfig = new ProxyConfig(hostAndPort[0],Integer.parseInt(hostAndPort[1]));
        Builder builder = new GoogleTrends.Builder(proxyConfig, segment)
                .setHttpClient(buildClient())
                .setConnectTimeout((int)timeout);
		GoogleTrends client = builder.build();
		result.add(client.topSearches());
		result.add(client.risingSearches());
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
