package fogetti.phish.storm.db;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.relatedness.AckResult;
import redis.embedded.RedisServer;

public class RedisPublishingTest {

	private class SpyingJedisCallback implements JedisCallback {
		@Override
		public void onMessage(String channel, String message) {
			resultLatch.countDown();
		}
	}

	private class SpyingRedisPublishing extends Publishing {
		private static final long serialVersionUID = -2600547115961199106L;
		public SpyingRedisPublishing(BlockingQueue<PublishMessage> publishq) {
			super(publishq);
		}
		@Override
		public void publish(String channel, String msg) {
			try {
				super.publish(channel, msg);
				TimeUnit.MILLISECONDS.sleep(200);
				publisherLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private RedisServer server;
	private CountDownLatch resultLatch = new CountDownLatch(1);
	private CountDownLatch publisherLatch = new CountDownLatch(1);
	private JedisCallback jedisCallback = new SpyingJedisCallback();
	private ObjectMapper mapper = new ObjectMapper();
	private AckResult globalAckresult = new AckResult();
	private String stringackresult;

	@Before
	public void setup() throws Exception {
		server = new RedisServer();
		server.start();
		globalAckresult.URL = "http://url.com";
		globalAckresult.MLD = "http://url";
		globalAckresult.MLDPS = "http://url.com";
		stringackresult = mapper.writeValueAsString(globalAckresult);
	}
	
	@After
	public void teardown() throws Exception {
		server.stop();
	}

	private Publishing startPublishingThread(BlockingQueue<PublishMessage> publishq) throws Exception {
		Publishing db = new SpyingRedisPublishing(publishq);
		JedisEventSource source = new JedisEventSource(6379, 2000, publishq);
		new Thread(source, "publisherThread").start();
		return db;
	}
	
	private JedisCallback startSubscribingThread(JedisCallback callback) throws Exception {
		JedisListener listener = new JedisListener(6379, 2000, "phish", callback);
		new Thread(listener, "subscriberThread").start();
		TimeUnit.MILLISECONDS.sleep(100);
		return callback;
	}

	@Test
	public void publishSucceeds() throws Exception {
		// Given we receive acknowledgment in our spout
		Publishing publisher = startPublishingThread(new ArrayBlockingQueue<>(1));
		startSubscribingThread(jedisCallback);

		// When we send a message in Redis
		publisher.publish("phish", stringackresult);

		// Then the Jedis callback gets notified
		resultLatch.await();
	}
	
	@Test
	public void publishToNonExistingQueueFails() throws Exception {
		// Given we want to use the Redis pub-sub feature for delivering ack results to the intersection bolt
		Publishing publisher = startPublishingThread(new ArrayBlockingQueue<>(1));
		startSubscribingThread(jedisCallback);

		// When we publish to a non-existing Redis queue
		publisher.publish("non-existing", stringackresult);

		// Then it succeeds
		publisherLatch.await();
		assertFalse("The supposedly failed publishing succeeded", resultLatch.await(5, TimeUnit.SECONDS));
	}
}