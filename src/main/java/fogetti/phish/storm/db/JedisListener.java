package fogetti.phish.storm.db;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisListener extends JedisPubSub implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(JedisListener.class);

	private JedisCallback callback;
	private String channel;
	private JedisCommandsInstanceContainer container;
	
	public JedisListener(JedisPoolConfig config, String channel, JedisCallback callback) {
		this.channel = channel;
		this.callback = callback;
		this.container = JedisCommandsContainerBuilder.build(config);
	}
	
	@Override
	public void onMessage(String channel, String message) {
		callback.onMessage(channel, message);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try (Jedis jedis = (Jedis) container.getInstance()) {
				logger.info("Subscribing");
				jedis.subscribe(this, channel);
			} catch (Exception e) {
				logger.error("Subscribing to Redis failed - {}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

}