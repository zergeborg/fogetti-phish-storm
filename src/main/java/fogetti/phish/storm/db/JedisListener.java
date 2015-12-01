package fogetti.phish.storm.db;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class JedisListener extends JedisPubSub implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(JedisListener.class);

	private final JedisCallback callback;
	private final JedisPool jedispool;
	private final String channel;
	
	public JedisListener(int port, int timeout, String channel, JedisCallback callback) {
		this.channel = channel;
		this.callback = callback;
		this.jedispool = configureRedis(port, timeout);
	}
	
	@Override
	public void onMessage(String channel, String message) {
		callback.onMessage(channel, message);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try (Jedis jedis = jedispool.getResource()) {
				logger.info("Subscribing");
				jedis.subscribe(this, channel);
			} catch (Exception e) {
				logger.error("Subscribing to Redis failed - {}", e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

	private JedisPool configureRedis(int port, int timeout) {
		InetAddress bindAddress = null;
		try {
			bindAddress = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		String ipAddress = (bindAddress == null) ? "127.0.0.1" : bindAddress.getHostAddress();
		return new JedisPool(new JedisPoolConfig(), ipAddress, port, timeout);
	}

}