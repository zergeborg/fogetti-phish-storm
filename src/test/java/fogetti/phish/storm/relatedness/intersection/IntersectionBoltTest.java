package fogetti.phish.storm.relatedness.intersection;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;

public class IntersectionBoltTest {
	
	private String paypal;
	private Jedis jedis;
    private String resultDataFile;
	
	private class TestDoubleIntersectionBolt extends IntersectionBolt {

		private static final long serialVersionUID = 740985698253429447L;

		public TestDoubleIntersectionBolt(IntersectionAction intersectionAction, JedisPoolConfig config, String resultDataFile) {
			super(intersectionAction, config, resultDataFile);
		}
		
	    @Override
		protected JedisCommands getInstance() {
	        return jedis;
	    }
		
	    @Override
		protected void returnInstance(JedisCommands instance) {
	    }
	}
	
	@Before
	public void setup() throws Exception {
		paypal = "paypal";
		jedis = mock(Jedis.class);
		String absolutePath = new File(this.getClass().getResource(".").toURI()).getAbsolutePath();
        resultDataFile = absolutePath + File.separator + "phishing-result.csv";
	}

	@Test(expected = JedisException.class)
	public void redisRequestFails() throws Exception {
		// Given we want to get related words for a keyword
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt bolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to redis
		when(jedis.exists(anyString())).thenThrow(new JedisException("Error"));
		bolt.execute(keyword);

		// Then it fails
	}

	@Test
	public void cachedSegmentFound() throws Exception {
		// Given we want to get related words for a keyword
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt bolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("word")).thenReturn(paypal);
		when(keyword.getStringByField("url")).thenReturn("http://google.com");
		Set<String> termset = new HashSet<>();
		termset.add("paypal payment");
		when(keyword.getValue(0)).thenReturn(termset);

		// When we send a request to redis
		when(jedis.exists(anyString())).thenReturn(true);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector);
		bolt.execute(keyword);

		// Then it returns a cached segment
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(collector).ack(keyword);
	}
	
	@Test
	public void cachedSegmentNotFound() throws Exception {
		// Given we want to get related words for a keyword
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt bolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("word")).thenReturn(paypal);
		HashSet<String> termset = new HashSet<String>();
		termset.add("paypal payment");
		when(keyword.getValue(0)).thenReturn(termset);
		when(keyword.getStringByField("url")).thenReturn("http://google.com");

		// When we send a request to redis which returns no cached segment
		when(jedis.exists(anyString())).thenReturn(false);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector);
		bolt.execute(keyword);

		// Then we send a request to bing
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(jedis).sadd(anyString(), anyObject());
		verify(collector).ack(keyword);
	}
	
	@Test
	public void receivesEmptySet() throws Exception {
		// Given we want to compute intersections
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt iBolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);

		Tuple input = mock(Tuple.class);
		when(input.getValue(0)).thenReturn(new HashSet<String>());
		when(input.getStringByField("word")).thenReturn("something~http://valami.com");
		when(input.getStringByField("url")).thenReturn("http://google.com");
		OutputCollector collector = mock(OutputCollector.class);
		// When the input is empty
		iBolt.prepare(null, null, collector);
		iBolt.execute(input);

		// Then execute succeeds
		verify(input, atLeast(1)).getValue(0);
	}
}
