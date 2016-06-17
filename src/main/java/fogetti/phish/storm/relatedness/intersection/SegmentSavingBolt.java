package fogetti.phish.storm.relatedness.intersection;

import static fogetti.phish.storm.integration.PhishTopologyBuilder.REDIS_SEGMENT_PREFIX;

import java.util.Map;

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

public class SegmentSavingBolt extends AbstractRedisBolt {

    private static final long serialVersionUID = 8029487832895180114L;
    private static final Logger logger = LoggerFactory.getLogger(SegmentSavingBolt.class);
    private ObjectMapper mapper;
    private final int METRICS_WINDOW = 10;
    private transient CountMetric intersectionSegmentSaved;

    public SegmentSavingBolt(JedisPoolConfig config) {
        super(config);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        super.prepare(stormConf, context, collector);
        this.mapper = new ObjectMapper();
        intersectionSegmentSaved = new CountMetric();
        context.registerMetric("int-segment-saved",
                               intersectionSegmentSaved,
                               METRICS_WINDOW);
    }
    
    @Override
    public void execute(Tuple input) {
        Terms terms = (Terms) input.getValue(0);
        String segment = input.getStringByField("word");
        save(input, segment, terms);
        collector.ack(input);
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

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

}
