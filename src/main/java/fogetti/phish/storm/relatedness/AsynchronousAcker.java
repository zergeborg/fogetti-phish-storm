package fogetti.phish.storm.relatedness;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.db.PublishMessage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

public class AsynchronousAcker implements Runnable, IAcker {

    private static final Logger logger = LoggerFactory.getLogger(AsynchronousAcker.class);
    private final JedisCommandsInstanceContainer container;
    private final ObjectMapper mapper = new ObjectMapper();
    private final BlockingQueue<String> msgQ = new ArrayBlockingQueue<>(2000);
    private final Decoder decoder = Base64.getDecoder();
    private final Encoder encoder = Base64.getEncoder();
    private final RetryQueue retry;
    private final CountMetric ackedPublished;
    private final CountMetric ackedSaved;
    private final CountMetric ackedSkipped;
    private final CountMetric ackedRetried;
    
    private static class RetryQueue implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(RetryQueue.class);
        private final AsynchronousAcker acker;
        private final BlockingQueue<String> retryQ = new ArrayBlockingQueue<>(2000);
        
        public RetryQueue(AsynchronousAcker acker) {
            this.acker = acker;
        }
        
        private void enqueue(String msgId) {
            try {
                retryQ.put(msgId);
            } catch (InterruptedException e) {
                logger.warn("Could not enqueue [{}] because the process got interupted", msgId);
                Thread.currentThread().interrupt();
            }
        }
        
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String msgId = retryQ.take();
                    acker.enqueue(msgId);
                } catch (InterruptedException e) {
                    logger.error("Retrying message failed", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        
    }

    public AsynchronousAcker(
            SpoutOutputCollector collector,
            JedisPoolConfig config,
            CountMetric ackedPublished,
            CountMetric ackedSaved,
            CountMetric ackedSkipped,
            CountMetric ackedRetried) {
        this.ackedPublished = ackedPublished;
        this.ackedSaved = ackedSaved;
        this.ackedSkipped = ackedSkipped;
        this.ackedRetried = ackedRetried;
        this.container = JedisCommandsContainerBuilder.build(config);
        this.retry = new RetryQueue(this);
        new Thread(this.retry, "retryThread").start();
    }

    @Override
    public void enqueue(String msgId) {
//        try {
//            if (!saved(msgId)) {
//                msgQ.put(msgId);
//            } else {
//                logger.warn("Message already saved [msgId={}]. Skipping", msgId);
//                ackedSkipped.incr();
//            }
//        } catch (InterruptedException e) {
//            logger.warn("Could not enqueue [{}] because the process got interupted", msgId);
//            Thread.currentThread().interrupt();
//        }
    }

    private boolean saved(String msgId) {
        String encodedURL = getEncodedURL(msgId);
        String key = "saved:" + encodedURL;
        try (Jedis jedis = (Jedis) getInstance()) {
            List<String> messages = jedis.lrange(key, 0L, 0L);
            if (messages != null && !messages.isEmpty()) return true;
        }
        return false;
    }

    private String getEncodedURL(String msgId) {
        byte[] decodedURL = decoder.decode(msgId);
        String longURL = new String(decodedURL, StandardCharsets.UTF_8);
        String URL = StringUtils.substringBeforeLast(longURL, "#");
        byte[] message = URL.getBytes(StandardCharsets.UTF_8);
        String encodedURL = encoder.encodeToString(message);
        return encodedURL;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (Jedis jedis = (Jedis) getInstance()) {
                String msgId = msgQ.take();
                logger.info("Acking enqueued message [{}]", msgId);
                AckResult result = null;
                try {
                    List<String> messages = jedis.blpop(1, new String[]{"acked:"+msgId});
                    if (messages != null) {
                        result = mapper.readValue(messages.get(1), AckResult.class);
                    } else {
                        logger.warn("Could not look up AckResult related to {}. Requeueing.", msgId);
                        retry.enqueue(msgId);
                        ackedRetried.incr();
                        continue;
                    }
                    if (result != null) {
                        String msg = mapper.writeValueAsString(result);
                        publish("phish", msg, jedis);
                        save(msgId, jedis);
                    }
                } catch (IOException e) {
                    logger.warn("Could not look up AckResult related to {}. Requeueing.", msgId);
                    retry.enqueue(msgId);
                    ackedRetried.incr();
                }
            } catch (InterruptedException e) {
                logger.error("Acking asynchronously failed", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void publish(String channel, String msg, Jedis jedis) {
        PublishMessage message = new PublishMessage(channel, msg);
        logger.info("Publishing [Message={}]", message.msg);
        jedis.rpush(message.channel, message.msg);
        ackedPublished.incr();
    }

    private void save(String msgId, Jedis jedis) {
        String encodedURL = getEncodedURL(msgId);
        logger.info("Saving [msgId={}]", encodedURL);
        jedis.rpush("saved:"+encodedURL, encodedURL);
        ackedSaved.incr();
    }

    /**
     * Borrow JedisCommands instance from container.<p></p>
     * JedisCommands is an interface which contains single key operations.
     * @return implementation of JedisCommands
     * @see JedisCommandsInstanceContainer#getInstance()
     */
    private JedisCommands getInstance() {
        return this.container.getInstance();
    }

}