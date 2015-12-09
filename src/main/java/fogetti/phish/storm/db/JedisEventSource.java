package fogetti.phish.storm.db;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisEventSource implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(JedisEventSource.class);
	private final JedisPool jedispool;
	private final BlockingQueue<PublishMessage> publishq;

	public JedisEventSource(String host, int port, int timeout, String password, BlockingQueue<PublishMessage> publishq) {
		this.publishq = publishq;
		this.jedispool = configureRedis(host, port, timeout, password);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try (Jedis jedis = jedispool.getResource()) {
				PublishMessage message = publishq.take();
				logger.info("Publishing [Message={}]", message.msg);
				jedis.publish(message.channel, message.msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.info("Iterruption signal captured - Exiting Redis persistency");
			} catch (Exception e) {
				logger.error("Publishing to Redis failed", e);
			}
		}
	}
	
	private JedisPool configureRedis(String host, int port, int timeout, String password) {
		InetAddress bindAddress = null;
		try {
			bindAddress = InetAddress.getByName(host);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		String ipAddress = (bindAddress == null) ? "127.0.0.1" : bindAddress.getHostAddress();
		return new JedisPool(new JedisPoolConfig(), ipAddress, port, timeout, password);
	}

}