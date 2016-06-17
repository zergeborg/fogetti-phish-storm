package fogetti.phish.storm.relatedness.intersection;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Tuple;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Term;
import fogetti.phish.storm.client.Terms;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

@Ignore
public class IntersectionBoltTest {
	
	private String paypal;
	private Jedis jedis;
    private String resultDataFile;
	
	private class TestDoubleIntersectionBolt extends IntersectionBolt {

		private static final long serialVersionUID = 740985698253429447L;

		public TestDoubleIntersectionBolt(IntersectionAction intersectionAction, JedisPoolConfig config, String resultDataFile) {
			super(config);
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

    private List<Term> termsOf(String... string) {
        List<Term> paypalTerms = Arrays.asList(new Term[]{ new Term(string) });
        return paypalTerms;
    }
    
	@Test
	public void cachedSegmentFound() throws Exception {
		// Given we want to get related words for a keyword
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt bolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("word")).thenReturn(paypal);
        String URL = Base64.getEncoder().encodeToString("http://google.com".getBytes(StandardCharsets.UTF_8));
		when(keyword.getStringByField("url")).thenReturn(URL);
        Terms termset = new Terms();
        termset.add(termsOf("paypal payment".split("\\s+")));
		when(keyword.getValue(0)).thenReturn(termset);

		// When we send a request to redis
		when(jedis.exists(anyString())).thenReturn(true);
		ObjectMapper mapper = new ObjectMapper();
		String rawSegments = mapper.writeValueAsString(new URLSegments());
        when(jedis.get(anyString())).thenReturn(rawSegments);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, mock(TopologyContext.class), collector);
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
		Terms termset = new Terms();
		termset.add(termsOf("paypal payment".split("\\s+")));
		when(keyword.getValue(0)).thenReturn(termset);
		String URL = Base64.getEncoder().encodeToString("http://google.com".getBytes(StandardCharsets.UTF_8));
		when(keyword.getStringByField("url")).thenReturn(URL);

		// When we send a request to redis which returns no cached segment
		when(jedis.exists(anyString())).thenReturn(false);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, mock(TopologyContext.class), collector);
		bolt.execute(keyword);

		// Then we send a request to bing
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(jedis, atLeast(1)).set(anyString(), anyString());
		verify(collector).ack(keyword);
	}
	
	@Test
	public void receivesEmptySet() throws Exception {
		// Given we want to compute intersections
		JedisPoolConfig config = mock(JedisPoolConfig.class);
		IntersectionBolt iBolt = new TestDoubleIntersectionBolt(null, config, resultDataFile);

		Tuple input = mock(Tuple.class);
		when(input.getValue(0)).thenReturn(new Terms());
		when(input.getStringByField("word")).thenReturn("something~http://valami.com");
        String URL = Base64.getEncoder().encodeToString("http://google.com".getBytes(StandardCharsets.UTF_8));
		when(input.getStringByField("url")).thenReturn(URL);
		OutputCollector collector = mock(OutputCollector.class);
		// When the input is empty
		iBolt.prepare(null, mock(TopologyContext.class), collector);
		iBolt.execute(input);

		// Then execute succeeds
		verify(input, atLeast(1)).getValue(0);
	}
}
