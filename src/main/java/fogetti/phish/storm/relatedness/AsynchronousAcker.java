package fogetti.phish.storm.relatedness;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

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
    private final BlockingQueue<String> msgQ = new PriorityBlockingQueue<>();
    private final RetryQueue retry;
    
    private static class RetryQueue implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(RetryQueue.class);
        private final AsynchronousAcker acker;
        private final BlockingQueue<String> retryQ = new PriorityBlockingQueue<>();
        
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

    public AsynchronousAcker(SpoutOutputCollector collector, JedisPoolConfig config) {
        this.container = JedisCommandsContainerBuilder.build(config);
        this.retry = new RetryQueue(this);
        new Thread(this.retry, "retryThread").start();
    }

    @Override
    public void enqueue(String msgId) {
        try {
            if (!saved(msgId)) {
                msgQ.put(msgId);
            }
        } catch (InterruptedException e) {
            logger.warn("Could not enqueue [{}] because the process got interupted", msgId);
            Thread.currentThread().interrupt();
        }
    }

    private boolean saved(String msgId) {
        String key = "saved:" + msgId;
        try (Jedis jedis = (Jedis) getInstance()) {
            List<String> messages = jedis.lrange(key, 0L, 0L);
            if (messages != null && !messages.isEmpty()) return true;
        }
        return false;
    }

    @Override
    public void run() {
        try (Jedis jedis = (Jedis) getInstance()) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String msgId = msgQ.take();
                    logger.info("Acking enqueued message [{}]", msgId);
                    AckResult result = null;
                    try {
                        List<String> messages = jedis.blpop(1, new String[]{"acked:"+msgId.toString()});
                        if (messages != null) {
                            result = mapper.readValue(messages.get(1), AckResult.class);
                        } else {
                            logger.warn("Could not look up AckResult related to {}. Requeueing.", msgId);
                            retry.enqueue(msgId);
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
                    }
                } catch (InterruptedException e) {
                    logger.error("Acking asynchronously failed", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void publish(String channel, String msg, Jedis jedis) {
        PublishMessage message = new PublishMessage(channel, msg);
        logger.info("Publishing [Message={}]", message.msg);
        jedis.rpush(message.channel, message.msg);
    }

    private void save(String msgId, Jedis jedis) {
        logger.info("Saving [msgId={}]", msgId);
        jedis.rpush("saved:"+msgId, msgId);
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

    /**
     * Return borrowed instance to container.
     * @param instance borrowed object
     */
    private void returnInstance(JedisCommands instance) {
        this.container.returnInstance(instance);
    }
}