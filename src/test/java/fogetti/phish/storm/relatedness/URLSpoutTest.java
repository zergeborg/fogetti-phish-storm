package fogetti.phish.storm.relatedness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import backtype.storm.spout.SpoutOutputCollector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

public class URLSpoutTest {

	private ClassLoader loader = this.getClass().getClassLoader();
	private String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
	private String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
	private String urlDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/test/resources/same-url-list.txt";

	private class TestDoubleURLSpout extends URLSpout {

		private static final long serialVersionUID = -7748829151740848266L;

		public TestDoubleURLSpout(String countDataFile, String psDataFile, String urlDataFile, Map<String, AckResult> ackIndex) {
			super(countDataFile, psDataFile, urlDataFile, ackIndex, mock(JedisPoolConfig.class));
		}

		@Override
		protected JedisCommands getInstance() {
			return mock(Jedis.class);
		}

		@Override
		protected void returnInstance(JedisCommands instance) {
		}
	}

	@Before
	public void setup() throws Exception {
		countDataFile = new File(loader.getResource("1gram-count.txt").toURI()).getAbsolutePath();
		psDataFile = new File(loader.getResource("public-suffix-list.dat").toURI()).getAbsolutePath();
		urlDataFile = new File(loader.getResource("same-url-list.txt").toURI()).getAbsolutePath();
	}

	@Test
	public void segmentOne() throws Exception {
		// Given we want to segment a word before calculating relatedness
		URLSpout spout = new TestDoubleURLSpout(countDataFile, psDataFile, urlDataFile, null);
		spout.open(null, null, null);

		// When we submit "itwasabrightcolddayinaprilandtheclockswerestrikingthirteen"
		List<String> segment = spout.segment("itwasabrightcolddayinaprilandtheclockswerestrikingthirteen");

		// Then it returns [it, was, a, bright, cold, day, in, april, and, the, clocks, were, striking, thirteen]
		System.out.println(segment);
		List<String> expected = Arrays.asList(new String[]{"it", "was", "a", "bright", "cold", "day", "in", "april", "and", "the", "clocks", "were", "striking", "thirteen"});
		assertEquals("The segmentation was wrong", expected.toString(), segment.toString());
	}

	@Test
	public void segmentTwo() throws Exception {
		// Given we want to segment a word before calculating relatedness
		URLSpout spout = new TestDoubleURLSpout(countDataFile, psDataFile, urlDataFile, null);
		spout.open(null, null, null);

		// When we submit "inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort"
		List<String> segment = spout.segment("inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort");

		// Then it returns [in, a, hole, in, the, ground, there, lived, a, hobbit, not, a, nasty, dirty, wet, hole, filled, with, the, ends, of, worms, and, an, oozy, smell, nor, yet, a, dry, bare, sandy, hole, with, nothing, in, it, to, sitdown, on, or, to, eat, it, was, a, hobbit, hole, and, that, means, comfort]
		System.out.println(segment);
		List<String> expected = Arrays.asList(new String[]{"in", "a", "hole", "in", "the", "ground", "there", "lived", "a", "hobbit", "not", "a", "nasty", "dirty", "wet", "hole", "filled", "with", "the", "ends", "of", "worms", "and", "an", "oozy", "smell", "nor", "yet", "a", "dry", "bare", "sandy", "hole", "with", "nothing", "in", "it", "to", "sitdown", "on", "or", "to", "eat", "it", "was", "a", "hobbit", "hole", "and", "that", "means", "comfort"});
		assertEquals("The segmentation was wrong", expected.toString(), segment.toString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void nextTuple() throws Exception {
		// Given we want to measure relatedness in an URL
		Map<String, AckResult> ackIndex = mock(Map.class);
		URLSpout spout = new TestDoubleURLSpout(countDataFile, psDataFile, urlDataFile, ackIndex);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);

		// When we call nextTuple
		spout.nextTuple();

		// Then it calculates the public suffix of the given URL
		// and segments the words found in the URL
		verify(spy, atLeast(1)).emit(anyObject(), anyString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void sameURLsInAckIndex() throws Exception {
		// Given we want to measure relatedness in the same URL multiple times
		Map<String, AckResult> ackIndex = mock(Map.class);
		AckResult result = new AckResult();
		result.setAllsent(true);
		when(ackIndex.get(anyString())).thenReturn(result);
		URLSpout spout = new TestDoubleURLSpout(countDataFile, psDataFile, urlDataFile, ackIndex);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);
		String prefix = "prefix~";

		// When ack is getting called
		spout.nextTuple();
		spout.nextTuple();
		spout.nextTuple();
		String sezo1 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run~"+"1";
		spout.ack((Object)(prefix+sezo1));
		String sezo2 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run~"+"2";
		spout.ack((Object)(prefix+sezo2));
		String sezo3 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run~"+"3";
		spout.ack((Object)(prefix+sezo3));

		// Then it calculates the public suffix of the given URL multiple times
		verify(ackIndex, atLeast(3)).put(anyString(), anyObject());
		verify(ackIndex, atLeast(1)).get(sezo1);
		verify(ackIndex, atLeast(1)).get(sezo2);
		verify(ackIndex, atLeast(1)).get(sezo3);
		verify(ackIndex, atLeast(1)).remove(sezo1);
		verify(ackIndex, atLeast(1)).remove(sezo2);
		verify(ackIndex, atLeast(1)).remove(sezo3);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void differentURLsInAckIndex() throws Exception {
		// Given we want to measure relatedness in different URLs multiple times
		Map<String, AckResult> ackIndex = mock(Map.class);
		AckResult result = new AckResult();
		result.setAllsent(true);
		when(ackIndex.get(anyString())).thenReturn(result);
		URLSpout spout = new TestDoubleURLSpout(countDataFile, psDataFile, urlDataFile, ackIndex);
		SpoutOutputCollector spy = getSpy();
		spout.open(null, null, spy);
		String prefix = "prefix~";

		// When ack is getting called
		spout.nextTuple();
		spout.nextTuple();
		spout.nextTuple();
		String url1 = "sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run~"+"1";
		spout.ack((Object)(prefix+url1));
		String url2 = "www.smbctb.co.jp/JPGCB/JPS/portal/SignonLocaleSwitch.do?locale=en_JP~"+"1";
		spout.ack((Object)(prefix+url2));
		String url3 = "tracking.binarypromos.com/aff_c?offer_id=1923&aff_id=5052&aff_sub=3014860850&aff_sub5=header~"+"1";
		spout.ack((Object)(prefix+url3));

		// Then it calculates the public suffix of the given URL multiple times
		verify(ackIndex, atLeast(3)).put(anyString(), anyObject());
		verify(ackIndex, atLeast(1)).get(url1);
		verify(ackIndex, atLeast(1)).get(url2);
		verify(ackIndex, atLeast(1)).get(url3);
		verify(ackIndex, atLeast(1)).remove(url1);
		verify(ackIndex, atLeast(1)).remove(url2);
		verify(ackIndex, atLeast(1)).remove(url3);
	}

	@Ignore
	@Test
	public void redisConnectionFailed() throws Exception {
		// Given

		// When

		// Then
	}

	protected SpoutOutputCollector getSpy() {
		SpoutOutputCollector collector = new SpoutOutputCollector(mock(SpoutOutputCollector.class));
		SpoutOutputCollector spy = spy(collector);
		return spy;
	}
}
