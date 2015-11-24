package fogetti.phish.storm.db;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPersistency implements Persistency, Runnable {

	private static final long serialVersionUID = 3896918057489260695L;

	private static final Logger logger = LoggerFactory.getLogger(RedisPersistency.class);
	
	private final JedisPool jedispool;
	private final BlockingQueue<PublishMessage> publishq = new ArrayBlockingQueue<>(1);
	
	private static class PublishMessage {
		String channel;
		String msg;
		
		public PublishMessage(String channel, String msg) {
			this.channel = channel;
			this.msg = msg;
			
		}
	}
	
	public RedisPersistency(int port, int timeout) {
		jedispool = configureRedis(port, timeout);
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try (Jedis jedis = jedispool.getResource()) {
				PublishMessage message = publishq.take();
				logger.info("publishing");
				jedis.publish(message.channel, message.msg);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.info("Iterruption signal captured - Exiting Redis persistency");
			} catch (Exception e) {
				logger.info(">>> OH NOES Pub, " + e.getMessage());
			}
		}
	}
	
	@Override
	public void publish(String channel, String msg) {
		try {
			publishq.put(new PublishMessage(channel, msg));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
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
