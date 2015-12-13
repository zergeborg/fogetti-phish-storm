package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.wsdl.bing.webmaster.ArrayOfKeyword;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApi;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage;
import fogetti.phish.storm.wsdl.bing.webmaster.Keyword;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;

public class BingSemBoltTest {

	private String paypal;
	private String country;
	private String language;
	private IWebmasterApi api;
	private JedisPoolConfig config;
	private BingSemBolt bolt;
	private XMLGregorianCalendar startDate;
	private XMLGregorianCalendar endDate;
	private ArrayOfKeyword keywordArray;
	private JedisCommands jedis;
	
	private class TestDoubleBingSemBolt extends BingSemBolt {

		private static final long serialVersionUID = -4874909073941561461L;

		public TestDoubleBingSemBolt(JedisPoolConfig config) {
			super(config);
		}
		
		public TestDoubleBingSemBolt(IWebmasterApi api, JedisPoolConfig config) {
			super(api, config);
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
		country = "";
		language = "";
		api = mock(IWebmasterApi.class);
		config = mock(JedisPoolConfig.class);
		jedis = mock(JedisCommands.class);
		bolt = new TestDoubleBingSemBolt(api, config);
		DatatypeFactory factory = DatatypeFactory.newInstance();
		startDate(factory);
		endDate(factory);
		keywordArray = mock(ArrayOfKeyword.class);
		Keyword keyword1 = mockKeyword1();
		Keyword keyword2 = mockKeyword2();
		mockKeywords(keyword1, keyword2);
	}

	private void startDate(DatatypeFactory factory) {
		GregorianCalendar start = new GregorianCalendar();
		start.set(Calendar.DAY_OF_YEAR, -60);
		startDate = factory.newXMLGregorianCalendar(start);
	}

	private void endDate(DatatypeFactory factory) {
		GregorianCalendar end = new GregorianCalendar();
		endDate = factory.newXMLGregorianCalendar(end);
	}

	@SuppressWarnings("unchecked")
	private Keyword mockKeyword1() {
		Keyword keyword1 = new Keyword();
		JAXBElement<String> paypalRelated1 = mock(JAXBElement.class);
		when(paypalRelated1.getValue()).thenReturn("paypal related1");
		keyword1.setQuery(paypalRelated1);
		return keyword1;
	}

	@SuppressWarnings("unchecked")
	private Keyword mockKeyword2() {
		Keyword keyword2 = new Keyword();
		JAXBElement<String> paypalRelated2 = mock(JAXBElement.class);
		when(paypalRelated2.getValue()).thenReturn("paypal related2");
		keyword2.setQuery(paypalRelated2);
		return keyword2;
	}

	private void mockKeywords(Keyword keyword1, Keyword keyword2) {
		List<Keyword> keywords = Arrays.asList(keyword1, keyword2);
		when(keywordArray.getKeyword()).thenReturn(keywords);
	}
	
	@Test(expected = JedisException.class)
	public void redisRequestFails() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to redis
		when(jedis.smembers(anyString())).thenThrow(new JedisException("Error"));
		bolt.execute(keyword, startDate, endDate);

		// Then it fails
	}
	
	@Test
	public void cachedSegmentFound() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);
		Set<String> segments = new HashSet<>();
		segments.add("paypal payment");

		// When we send a request to redis
		when(jedis.smembers(anyString())).thenReturn(segments);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector, null);
		bolt.execute(keyword, startDate, endDate);

		// Then it returns a cached segment
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(collector).ack(keyword);
	}
	
	@Test
	public void cachedSegmentNotFound() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to redis which returns no cached segment
		when(api.getRelatedKeywords(paypal, country, language, startDate , endDate)).thenReturn(keywordArray);
		when(jedis.smembers(anyString())).thenReturn(null);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector, api);
		bolt.execute(keyword, startDate, endDate);

		// Then we send a request to bing
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(api, atLeast(1)).getRelatedKeywords(paypal, country, language, startDate , endDate);
		verify(collector, atLeast(1)).emit((Tuple)anyObject(), anyObject());
		verify(collector).ack(keyword);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void bingRequestFails() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to bing
		when(api.getRelatedKeywords(paypal, country, language, startDate , endDate))
			.thenThrow(IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage.class);
		bolt.execute(keyword, startDate, endDate);

		// Then it fails
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(api, atLeast(1)).getRelatedKeywords(paypal, country, language, startDate , endDate);
	}
	
	@Test
	public void bingRequestSucceeds() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to bing
		when(api.getRelatedKeywords(paypal, country, language, startDate , endDate)).thenReturn(keywordArray);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector, api);
		bolt.execute(keyword, startDate, endDate);

		// Then it succeeds
		verify(keyword, atLeast(1)).getStringByField("url");
		verify(api, atLeast(1)).getRelatedKeywords(paypal, country, language, startDate , endDate);
		verify(collector, atLeast(1)).emit((Tuple)anyObject(), anyObject());
		verify(collector, atLeast(1)).ack(anyObject());
	}
	
	@Ignore
	@Test
	public void integration() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getStringByField("segment")).thenReturn(paypal);

		// When we send a request to bing
		OutputCollector collector = mock(OutputCollector.class);
		BingSemBolt bolt = new TestDoubleBingSemBolt(config);
		bolt.prepare(null, null, collector);
		bolt.execute(keyword, startDate, endDate);

		// Then
	}
	
}
