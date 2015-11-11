package fogetti.phish.storm.db;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fogetti.phish.storm.relatedness.URLSpout;
import fogetti.phish.storm.relatedness.intersection.BloomFilter;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;
import redis.embedded.RedisServer;

public class RedisPersistencyTest {

	private class SpyingRedisPersistency extends RedisPersistency {

		public SpyingRedisPersistency(int port, int timeout) {
			super(port, timeout);
		}
		
		@Override
		public void publish(String channel, String msg) {
			super.publish(channel, msg);
			dblatch.countDown();
		}
	}
	
	private RedisServer server;
	private CountDownLatch bloomLatch = new CountDownLatch(1);
	private BloomFilter bloomfilter = new BloomFilter() { @Override public void run() { bloomLatch.countDown(); } };
	private CountDownLatch dblatch = new CountDownLatch(1);

	@Before
	public void setup() throws Exception {
		server = new RedisServer();
		server.start();
	}
	
	@After
	public void teardown() throws Exception {
		server.stop();
	}

	private RedisPersistency startPublishing() throws Exception {
		RedisPersistency db = new SpyingRedisPersistency(6379, 2000);
		new Thread(db, "publisherThread").start();
		TimeUnit.MILLISECONDS.sleep(100);
		return db;
	}
	
	private JedisCallback startSubscribing(JedisCallback callback) throws Exception {
		JedisListener listener = new JedisListener(6379, 2000, "phish", callback);
		new Thread(listener, "subscriberThread").start();
		TimeUnit.MILLISECONDS.sleep(100);
		return callback;
	}

	@Test
	public void msgIdArrivedToIntersection() throws Exception {
		// Given we receive acknowledgment in our spout
		RedisPersistency db = startPublishing();
		JedisCallback callback = startSubscribing(new IntersectionBolt(bloomfilter));
		
		// When we send a message in Redis
		db.publish("phish", "This is a message");

		// Then the intersection bolt gets notified
		bloomLatch.await();
	}
	
	@Test
	public void ackNoQueueFound() throws Exception {
		// Given we want to use the Redis pub-sub feature for delivering ack results to the intersection bolt
		RedisPersistency db = startPublishing();
		JedisCallback callback = startSubscribing(mock(JedisCallback.class));
		URLSpout spout = new URLSpout(db);

		// When we publish to a non-existing Redis queue
		spout.ack("test-data");

		// Then it succeeds
		dblatch.await();
		verify(callback).onMessage(anyString(), anyString());
	}
}