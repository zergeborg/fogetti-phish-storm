package fogetti.phish.storm.relatedness;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.apache.storm.spout.SpoutOutputCollector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.db.PublishMessage;
import fogetti.phish.storm.exception.AckingFailedException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

public class AsynchronousAcker implements Runnable, IAcker {

    private final JedisCommandsInstanceContainer container;
    private final ObjectMapper mapper = new ObjectMapper();
    private final SpoutOutputCollector collector;
    private final BlockingQueue<String> msgQ = new PriorityBlockingQueue<>();

    public AsynchronousAcker(SpoutOutputCollector collector, JedisPoolConfig config) {
        this.collector = collector;
        this.container = JedisCommandsContainerBuilder.build(config);
    }
    
    @Override
    public void enqueue(String msgId) {
        try {
            msgQ.put(msgId);
        } catch (InterruptedException e) {
            URLSpout.logger.error("Enqueueing message failed", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (Jedis jedis = (Jedis) getInstance()) {
                String msgId = msgQ.take();
                URLSpout.logger.info("Acking enqueued message [{}]", msgId);
                AckResult result = null;
                try {
                    List<String> messages = jedis.blpop(0, new String[]{"acked:"+msgId.toString()});
                    if (messages != null) {
                        result = mapper.readValue(messages.get(1), AckResult.class);
                    } else {
                        collector.reportError(new AckingFailedException(String.format("Acking [%s] has failed", msgId)));
                    }
                } catch (IOException e) {
                    URLSpout.logger.error("Could not look up AckResult related to "+msgId.toString(), e);
                    collector.reportError(e);
                }
                try {
                    String msg = mapper.writeValueAsString(result);
                    publish("phish", msg);
                } catch (JsonProcessingException e) {
                    URLSpout.logger.error("Could not send acknowledgment to the intersection bolt", e);
                    collector.reportError(e);
                }
            } catch (InterruptedException e) {
                URLSpout.logger.error("Subscribing to Redis failed", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void publish(String channel, String msg) {
        try (Jedis jedis = (Jedis) getInstance()) {
            PublishMessage message = new PublishMessage(channel, msg);
            URLSpout.logger.info("Publishing [Message={}]", message.msg);
            jedis.rpush(message.channel, message.msg);
        }
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