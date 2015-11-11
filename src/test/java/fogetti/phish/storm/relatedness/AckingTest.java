package fogetti.phish.storm.relatedness;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.embedded.RedisServer;

public class AckingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AckingTest.class);
	private CountDownLatch messageReceivedLatch = new CountDownLatch(1);
	private CountDownLatch publishLatch = new CountDownLatch(1);
	private int port = 6379;
    private RedisServer redisServer;
    private boolean bloomexecuted = false;
	private BloomFilter bloomfilter = new BloomFilter() {
		@Override
		public void run() {
			bloomexecuted = true;
			messageReceivedLatch.countDown();
		}
	};

	@Before
	public void setup() throws Exception {
	    setupRedis();
	    setupPublisher();
		setupSubscriber();
	}
	
	@After
	public void teardown() throws Exception {
		redisServer.stop();
	}

	private void setupRedis() throws IOException {
		redisServer = new RedisServer(port);
	    redisServer.start();
	}
	
	private void setupPublisher() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Connecting");
					Jedis jedis = new Jedis("localhost");
					logger.info("Waiting to publish");
					publishLatch.await();
					logger.info("Ready to publish, waiting one sec");
					Thread.sleep(1000);
					logger.info("publishing");
					jedis.publish("test", "This is a message");
					logger.info("published, closing publishing connection");
					jedis.quit();
					logger.info("publishing connection closed");
				} catch (Exception e) {
					logger.info(">>> OH NOES Pub, " + e.getMessage());
				}
			}
		}, "publisherThread").start();
	}

	private JedisPubSub setupSubscriber() {
		JedisPubSub jedisPubSub = new JedisListener(new IntersectionBolt(bloomfilter));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Connecting");
					Jedis jedis = new Jedis("localhost");
					logger.info("subscribing");
					jedis.subscribe(jedisPubSub, "test");
					logger.info("subscribe returned, closing down");
					jedis.quit();
				} catch (Exception e) {
					logger.info(">>> OH NOES Sub - " + e.getMessage());
				}
			}
		}, "subscriberThread").start();
		return jedisPubSub;
	}
	
	@Test
	public void msgIdArrivedToIntersection() throws Exception {
		// Given we receive acknowledgment in our spout
		
		// When we send a message in Redis
		publishLatch.countDown();

		// Then the intersection bolt gets notified
		messageReceivedLatch.await();
		assertTrue("The bloomfilter was not running at all", bloomexecuted);
	}
}
