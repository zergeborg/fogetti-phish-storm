package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.spout.SpoutOutputCollector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

public class URLSpoutTest {

	private ClassLoader loader = this.getClass().getClassLoader();
	private String urlDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/test/resources/same-url-list.txt";
	private ObjectMapper mapper = new ObjectMapper();
    private Jedis jedis = mock(Jedis.class);
    private List<String> ackList;
    private String result;
    private JedisPoolConfig config;
    private String publishMsg = "{\"allsent\":true,\"MLD\":null,\"MLDPS\":null,\"URL\":null,\"RDurl\":[],\"REMurl\":[],\"RDstack\":[],\"REMstack\":[],\"rdurl\":[],\"remurl\":[]}";

	private class TestDoubleURLSpout extends URLSpout {

		private static final long serialVersionUID = -7748829151740848266L;

		public TestDoubleURLSpout(String urlDataFile, JedisPoolConfig poolConfig) {
			super(urlDataFile, poolConfig);
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
        config = mock(JedisPoolConfig.class);
		urlDataFile = new File(loader.getResource("same-url-list.txt").toURI()).getAbsolutePath();
		ackList = new ArrayList<>();
        AckResult ack = new AckResult();
        ack.setAllsent(true);
        result = mapper.writeValueAsString(ack);
        when(jedis.blpop(anyInt(), (String[])anyObject())).thenReturn(ackList);
	}

	@Test
	public void nextTuple() throws Exception {
		// Given we want to measure relatedness in an URL
        URLSpout spout = new TestDoubleURLSpout(urlDataFile, config);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);

		// When we call nextTuple
		spout.nextTuple();

		// Then it calculates the public suffix of the given URL
		// and segments the words found in the URL
		verify(spy, atLeast(1)).emit(anyObject(), anyString());
	}

	@Test
	public void sameURLsInAckIndex() throws Exception {
        URLSpout spout = new TestDoubleURLSpout(urlDataFile, config);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);
		String sezo1 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run";
		String sezo2 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run";
		String sezo3 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run";
		ackList.add(sezo1);
		ackList.add(result);

		// When ack is getting called
		spout.nextTuple();
		spout.nextTuple();
		spout.nextTuple();
		spout.ack(sezo1);
		spout.ack(sezo2);
		spout.ack(sezo3);

		// Then it calculates the public suffix of the given URL multiple times
		verify(jedis, atLeast(3)).rpush("phish", publishMsg);
	}

	@Test
	public void differentURLsInAckIndex() throws Exception {
		// Given we want to measure relatedness in different URLs multiple times
		URLSpout spout = new TestDoubleURLSpout(urlDataFile, config);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);
		String url1 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run";
		String url2 = "www.smbctb.co.jp/JPGCB/JPS/portal/SignonLocaleSwitch.do?locale=en_JP";
		String url3 = "tracking.binarypromos.com/aff_c?offer_id=1923&aff_id=5052&aff_sub=3014860850&aff_sub5=header";
        ackList.add(url1);
        ackList.add(result);

		// When ack is getting called
		spout.nextTuple();
		spout.nextTuple();
		spout.nextTuple();
		spout.ack(url1);
		spout.ack(url2);
		spout.ack(url3);

		// Then it calculates the public suffix of the given URL multiple times
        verify(jedis, atLeast(3)).rpush("phish", publishMsg);
	}

	protected SpoutOutputCollector getSpy() {
		SpoutOutputCollector collector = new SpoutOutputCollector(mock(SpoutOutputCollector.class));
		SpoutOutputCollector spy = spy(collector);
		return spy;
	}
}
