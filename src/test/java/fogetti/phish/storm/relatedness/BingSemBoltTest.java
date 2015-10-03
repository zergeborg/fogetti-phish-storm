package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.wsdl.bing.webmaster.ArrayOfKeyword;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApi;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage;
import fogetti.phish.storm.wsdl.bing.webmaster.Keyword;

public class BingSemBoltTest {

	private String paypal;
	private String country;
	private String language;
	private IWebmasterApi api;
	private BingSemBolt bolt;
	private XMLGregorianCalendar startDate;
	private XMLGregorianCalendar endDate;
	private ArrayOfKeyword keywordArray;

	@Before
	public void setup() throws Exception {
		paypal = "paypal";
		country = "hu";
		language = "hu-HU";
		api = mock(IWebmasterApi.class);
		bolt = new BingSemBolt(api);
		DatatypeFactory factory = DatatypeFactory.newInstance();
		GregorianCalendar start = new GregorianCalendar();
		start.set(Calendar.DAY_OF_YEAR, -60);
		startDate = factory.newXMLGregorianCalendar(start);
		GregorianCalendar end = new GregorianCalendar();
		endDate = factory.newXMLGregorianCalendar(end);
		keywordArray = mock(ArrayOfKeyword.class);
		Keyword keyword1 = new Keyword();
		JAXBElement<String> paypalRelated1 = mock(JAXBElement.class);
		when(paypalRelated1.getValue()).thenReturn("paypal related1");
		keyword1.setQuery(paypalRelated1);
		Keyword keyword2 = new Keyword();
		JAXBElement<String> paypalRelated2 = mock(JAXBElement.class);
		when(paypalRelated2.getValue()).thenReturn("paypal related2");
		keyword2.setQuery(paypalRelated2);
		List<Keyword> keywords = Arrays.asList(keyword1, keyword2);
		when(keywordArray.getKeyword()).thenReturn(keywords);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void requestFails() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getString(0)).thenReturn(paypal);

		// When we send a request
		when(api.getRelatedKeywords(paypal, country, language, startDate , endDate))
			.thenThrow(IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage.class);
		bolt.execute(keyword, startDate, endDate);

		// Then it fails
		verify(keyword, atLeast(1)).getString(0);
		verify(api, atLeast(1)).getRelatedKeywords(paypal, country, language, startDate , endDate);
	}
	
	@Test
	public void requestSucceeds() throws Exception {
		// Given we want to get related words for a keyword
		Tuple keyword = mock(Tuple.class);
		when(keyword.getString(0)).thenReturn(paypal);

		// When we send a request
		when(api.getRelatedKeywords(paypal, country, language, startDate , endDate)).thenReturn(keywordArray);
		OutputCollector collector = mock(OutputCollector.class);
		bolt.prepare(null, null, collector);
		bolt.execute(keyword, startDate, endDate);

		// Then it succeeds
		verify(keyword, atLeast(1)).getString(0);
		verify(api, atLeast(1)).getRelatedKeywords(paypal, country, language, startDate , endDate);
		verify(collector, atLeast(1)).emit((Tuple)anyObject(), anyObject());
		verify(collector, atLeast(1)).ack(anyObject());
	}
	
}
