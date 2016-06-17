package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.metric.api.IReducer;
import org.apache.storm.metric.api.ReducedMetric;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.apache.storm.redis.common.container.JedisContainer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Implementation of the following real time phishing classifier:
 * <a href="https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf">
 * https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf
 * </a>
 * @author gergely.nagy
 *
 */
public class URLSpout extends BaseRichSpout {

    public static final String SUCCESS_STREAM = "success";

    private static final long serialVersionUID = -6424905468176142975L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	
	private SpoutOutputCollector collector;
	private TreeSet<String> urllist;
	private String urlDataFile;
	private JedisPoolConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private String[] schemes = {"http","https"};
    private UrlValidator urlValidator;
    private final int METRICS_WINDOW = 10;
    private transient CountMetric ackedPublished;
    private transient CountMetric ackedSaved;
    private transient CountMetric ackedSkipped;
    private transient CountMetric ackedRetried;
    private transient CountMetric spoutAcked;
    private transient CountMetric spoutSkipped;
    private transient CountMetric spoutFailed;
    private transient CountMetric spoutEmitted;
    private transient ReducedMetric spoutListSize;
    private JedisContainer container;
    private ObjectMapper mapper;

	public URLSpout(String urlDataFile, JedisPoolConfig config) {
		this.urlDataFile = urlDataFile;
		this.config = config;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.urllist = readURLListFromFile();
        this.encoder = Base64.getEncoder();
        this.decoder = Base64.getDecoder();
        this.urlValidator = new UrlValidator(schemes);
        this.container = (JedisContainer)JedisCommandsContainerBuilder.build(config);
        this.mapper = new ObjectMapper();
        ackedPublished = new CountMetric();
        context.registerMetric("acked-published",
                               ackedPublished,
                               METRICS_WINDOW);
        ackedSaved = new CountMetric();
        context.registerMetric("acked-saved",
                               ackedSaved,
                               METRICS_WINDOW);
        ackedSkipped = new CountMetric();
        context.registerMetric("acked-skipped",
                               ackedSkipped,
                               METRICS_WINDOW);
        ackedRetried = new CountMetric();
        context.registerMetric("acked-retried",
                               ackedRetried,
                               METRICS_WINDOW);
        spoutAcked = new CountMetric();
        context.registerMetric("spout-acked",
                               spoutAcked,
                               METRICS_WINDOW);
        spoutSkipped = new CountMetric();
        context.registerMetric("spout-skipped",
                               spoutSkipped,
                               METRICS_WINDOW);
        spoutFailed = new CountMetric();
        context.registerMetric("spout-failed",
                               spoutFailed,
                               METRICS_WINDOW);
        spoutEmitted = new CountMetric();
        context.registerMetric("spout-emitted",
                               spoutEmitted,
                               METRICS_WINDOW);
        spoutListSize = new ReducedMetric(new URLListSizeReducer());
        context.registerMetric("spout-list-size",
                               spoutListSize,
                               METRICS_WINDOW);
	}

	private TreeSet<String> readURLListFromFile() {
	    TreeSet<String> urls = new TreeSet<>();
		loadUrls(urls);
		return urls;
	}

	private void loadUrls(Set<String> urls) {
		try(Scanner scanner = new Scanner(new FileReader(urlDataFile));) {
			while (scanner.hasNextLine()) {
				urls.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
		    logger.error("Could not load the URLs", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void nextTuple() {
	    spoutListSize.update(urllist.size());
		if (!urllist.isEmpty()) {
			String URLWithScheme = urllist.last();
			urllist.remove(URLWithScheme);
			if (!saved(URLWithScheme)) doNextTuple(URLWithScheme);
			else spoutSkipped.incr();
		}
	}

    private boolean saved(String longURL) {
        String encodedURL = getEncodedURL(longURL);
        String key = "saved:" + encodedURL;
        Jedis jedis = (Jedis) getInstance();
        try {
            List<String> messages = jedis.lrange(key, 0L, 0L);
            if (messages != null && !messages.isEmpty()) return true;
        } catch (JedisException e) {
            closeRedis();
            throw e;
        } finally {
            returnInstance(jedis);
        }
        return false;
    }

    private String getEncodedURL(String longURL) {
        byte[] message = longURL.getBytes(StandardCharsets.UTF_8);
        String encodedURL = encoder.encodeToString(message);
        return encodedURL;
    }

	private void doNextTuple(String URLWithScheme) {
	    String URL = URLWithScheme + "#" +System.currentTimeMillis();
        String encodedURL = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
        logger.info("Emitting [{}]", encodedURL);
		collector.emit(new Values(encodedURL), encodedURL);
		spoutEmitted.incr();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));
        declarer.declareStream(SUCCESS_STREAM, new Fields("url"));
	}

	@Override
	public void ack(Object encodedURL) {
	    logger.info("Acking [{}]", encodedURL.toString());
	    String url = encodedURL.toString();
        if (url.startsWith("result://")) {
	        spoutAcked.incr();
	        return;
	    }
	    try (Jedis jedis = (Jedis) getInstance()) {
	        publish(url, jedis);
	        save(url, jedis);
	        spoutAcked.incr();
	    } catch (IOException e) {
	        logger.error("Acking ["+url+"] failed", e);
        }
	}

	@Override
	public void fail(Object encodedURL) {
	    String url = encodedURL.toString();
	    try {
	        String resultRemovedUrl = StringUtils.removeStart(url, "result://");
	        String URL = getURL(resultRemovedUrl);
	        logger.debug("Message [msg={}] failed", URL);
	        if (urlValidator.isValid(URL)) {
	            logger.warn("Requeueing [msg={}]", URL);
	            urllist.add(URL);
	        } else {
	            logger.warn("Skipping invalid URL [msg={}]", URL);
	        }
	    } catch(IllegalArgumentException e) {
	        logger.error("["+encodedURL+"] failed", e);
	    }
		spoutFailed.incr();
	}

    private String getURL(String encodedURL) {
        byte[] decoded = decoder.decode(encodedURL);
        String longURL = new String(decoded, StandardCharsets.UTF_8);
        String URL = StringUtils.substringBeforeLast(longURL, "#");
        return URL;
    }

    private void publish(String msgId, Jedis jedis) throws IOException {
        AckResult result = null;
        while (result == null) {
            List<String> messages = jedis.blpop(1, new String[]{"acked:"+msgId});
            if (messages != null) {
                result = mapper.readValue(messages.get(1), AckResult.class);
            } else {
                logger.warn("Could not look up AckResult related to {}. Retrying.", msgId);
                ackedRetried.incr();
                continue;
            }
        }
        String msg = mapper.writeValueAsString(result);
        logger.info("Publishing [Message={}]", msg);
        collector.emit(SUCCESS_STREAM, new Values(msg), "result://"+msg);
        ackedPublished.incr();
    }

    private void save(String encodedURL, Jedis jedis) {
        String URL = getURL(encodedURL);
        String encoded = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
        logger.info("Saving [msgId={}]", encoded);
        jedis.rpush("saved:"+encoded, encoded);
        ackedSaved.incr();
    }

    /**     
     * Borrow JedisCommands instance from container.<p></p>     
     * JedisCommands is an interface which contains single key operations.      
     * @return implementation of JedisCommands      
     * @see JedisCommandsInstanceContainer#getInstance()        
     */     
    protected JedisCommands getInstance() {     
        return this.container.getInstance();        
    }

    /**
     * Return borrowed instance to container.
     * @param instance borrowed object
     */
    protected void returnInstance(JedisCommands instance) {
        this.container.returnInstance(instance);
    }
    
    /**
     * Close the redis container.
     */
    private void closeRedis() {
        this.container.close();
    }
    
    private class URLListSizeReducer implements IReducer<Integer> {
        
        private Integer size;

        @Override
        public Integer init() {
            return 0;
        }

        @Override
        public Integer reduce(Integer accumulator, Object input) {
            if(input instanceof Integer) {
                size = (Integer)input;
            }
            return size;
        }

        @Override
        public Object extractResult(Integer accumulator) {
            return accumulator;
        }
        
    }
}
