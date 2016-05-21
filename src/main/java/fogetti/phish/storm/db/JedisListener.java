package fogetti.phish.storm.db;

import java.util.List;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class JedisListener implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(JedisListener.class);

	private final JedisCallback callback;
	private final String channel;
	private final JedisCommandsInstanceContainer container;
	
	public JedisListener(JedisPoolConfig config, String channel, JedisCallback callback) {
		this.channel = channel;
		this.callback = callback;
		this.container = JedisCommandsContainerBuilder.build(config);
	}

	@Override
	public void run() {
	    logger.info("Subscribing");
        try (Jedis jedis = (Jedis) container.getInstance()) {
    		while (!Thread.currentThread().isInterrupted()) {
    			try  {
    				List<String> messages = jedis.blpop(0, new String[]{channel});
    				logger.info("Jedis message arrived {}", messages.get(1));
                    callback.onMessage(channel, messages.get(1));
                } catch (Exception e) {
                    logger.error("Sending intersection callback message failed", e);
                }
    		}
        }
	}

}