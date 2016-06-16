package fogetti.phish.storm.relatedness.intersection;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Terms;
import redis.clients.jedis.Jedis;

public class IntersectionBolt extends AbstractRedisBolt {

    private static final String REDIS_INTERSECTION_PREFIX = "intersect:";
	private static final long serialVersionUID = -2553128795688882389L;
	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
    private ObjectMapper mapper;
    private Decoder decoder;
    private Encoder encoder;
    private final int METRICS_WINDOW = 10;
    private transient CountMetric intersectionSegmentSaved;
    private transient CountMetric intersectionIndexKeyUpdated;
    private transient CountMetric intersectionIndexKeyCreated;

	public IntersectionBolt(JedisPoolConfig config) {
		super(config);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		this.decoder = Base64.getDecoder();
		this.encoder = Base64.getEncoder();
		this.mapper = new ObjectMapper();
        intersectionSegmentSaved = new CountMetric();
        context.registerMetric("int-segment-saved",
                               intersectionSegmentSaved,
                               METRICS_WINDOW);
        intersectionIndexKeyUpdated = new CountMetric();
        context.registerMetric("int-index-key-updated",
                               intersectionIndexKeyUpdated,
                               METRICS_WINDOW);
        intersectionIndexKeyCreated = new CountMetric();
        context.registerMetric("int-index-key-created",
                               intersectionIndexKeyCreated,
                               METRICS_WINDOW);
	}

	@Override
	public void execute(Tuple input) {
		Terms terms = (Terms) input.getValue(0);
		String segment = input.getStringByField("word");
		save(input, segment, terms);
		updateSegmentIndex(input, terms, segment, getEncodedShortURL(input));
		collector.ack(input);
	}

    private String getEncodedShortURL(Tuple input) {
        String encodedURL = input.getStringByField("url");
		byte[] decoded = decoder.decode(encodedURL);
		String longURL = new String(decoded, StandardCharsets.UTF_8);
		String URL = StringUtils.substringBeforeLast(longURL, "#");
		String encodedShortURL = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
        return encodedShortURL;
    }

	private void save(Tuple input, String segment, Terms termset) {
        try (Jedis jedis = (Jedis) getInstance()) {
	        String key = REDIS_SEGMENT_PREFIX + segment;
            logger.debug("Saving new segment [segment={}] and [termset={}] to Redis", segment, termset);
            String termString = mapper.writeValueAsString(termset);
            jedis.set(key, termString);
            intersectionSegmentSaved.incr();
		} catch (JsonProcessingException e) {
		    logger.error("Could not save the segment into Redis", e);
		    collector.fail(input);
		}

	}

	private void updateSegmentIndex(Tuple input, Terms terms, String segment, String url) {
	    String key = REDIS_INTERSECTION_PREFIX + url;
	    try (Jedis jedis = (Jedis) getInstance()) {
	        if (jedis.exists(key)) {
	            Map<String, String> rawSegments = jedis.hgetAll(key);
	            URLSegments urlsegments = URLSegments.fromStringMap(rawSegments);
	            urlsegments.put(segment, terms);
                jedis.hmset(key, urlsegments.toStringMap());
                intersectionIndexKeyUpdated.incr();
	        } else {
	            URLSegments urlsegments = new URLSegments();
	            urlsegments.put(segment, terms);
	            jedis.hmset(key, urlsegments.toStringMap());
	            intersectionIndexKeyCreated.incr();
	        }
	        logger.debug("Segment index updated with [url={}], [segment={}] and [termset={}]", url, segment, terms);
        } catch (IOException e) {
            logger.error("Could not update the segment index", e);
            collector.fail(input);
        }
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

}