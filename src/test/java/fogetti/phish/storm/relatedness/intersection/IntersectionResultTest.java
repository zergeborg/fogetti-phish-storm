package fogetti.phish.storm.relatedness.intersection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.fluent.Request;
import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.client.ResponseRequest;

public class IntersectionResultTest {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionResultTest.class);
    private ResponseRequest request;
    private String url;
    
    @Before
    public void setUp() throws Exception {
        request = new ResponseRequest("alexa-result.xml");
        url = "alma.com";        
    }

	@Test
	public void ASRem_NoAssociatedWords() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		paypalAssociated.add(paypal);
		REMTermindex.put(paypal, paypalAssociated);
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		loginAssociated.add(login);
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with no associated words [{}]", result.RATIOAREM());
		assertEquals("RATIOAREM was incorrect", 0.000000D, result.RATIOAREM(), 0.000001D);
	}
	
	@Test
	public void ASRem_AssociatedWordsAreSame() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		paypalAssociated.add("paypal alma");
		paypalAssociated.add("paypal alma");
		REMTermindex.put(paypal, paypalAssociated);
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		loginAssociated.add("login korte");
		loginAssociated.add("login korte");
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with same associated words [{}]", result.RATIOAREM());
		assertTrue("RATIOAREM was incorrect", result.RATIOAREM() > 1D);
	}

	@Test
	public void ASRem_AssociatedWordsDifferent() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		paypalAssociated.add("paypal alma");
		paypalAssociated.add("paypal korte");
		REMTermindex.put(paypal, paypalAssociated);
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		loginAssociated.add("login barack");
		loginAssociated.add("login cseresznye");
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with different associated words [{}]", result.RATIOAREM());
		assertTrue("RATIOAREM was incorrect", result.RATIOAREM() > 1D);
	}
	
	@Test
	public void RELRem_RelatedWordsEmpty() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with no related words [{}]", result.RATIORREM());
		assertEquals("RATIORREM was incorrect", 0.000000D, result.RATIORREM(), 0.000001D);
	}
	
	@Test
	public void RELRem_RelatedWordsSame() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		paypalAssociated.add("same same same same");
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		loginAssociated.add("same same same same");
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with same related words [{}]", result.RATIORREM());
		assertTrue("RATIORREM was incorrect", result.RATIORREM() > 0D);
	}
	
	@Test
	public void RELRem_RelatedWordsDifferent() throws Exception {
		// Given
		Map<String, Collection<String>> REMTermindex = new HashMap<>();
		Set<String> paypalAssociated = new HashSet<>();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		paypalAssociated.add("one two three four");
		Set<String> loginAssociated = new HashSet<>();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		loginAssociated.add("five six seven eight");
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with different related words [{}]", result.RATIORREM());
		assertTrue("RATIORREM was incorrect", result.RATIORREM() > 1D);
	}
	
	@Test
	public void ASRd_NoAssociatedWords() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		googleMldAssociated.add(googlemld);
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(googlemldps);
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with no associated words [{}]", result.RATIOARD());
		assertEquals("RATIOARD was incorrect", 0.000000D, result.RATIOARD(), 0.000001D);
	}
	
	@Test
	public void ASRd_AssociatedWordsAreSame() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		googleMldAssociated.add("www.google alma alma");
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add("www.google.com korte korte");
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with same associated words [{}]", result.RATIOARD());
		assertTrue("RATIOARD was incorrect", result.RATIOARD() > 1D);
	}
	
	@Test
	public void ASRd_AssociatedWordsDifferent() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		googleMldAssociated.add("www.google alma korte");
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add("www.google.com barack cseresznye");
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with different associated words [{}]", result.RATIOARD());
		assertTrue("RATIOARD was incorrect", result.RATIOARD() > 1D);
	}
	
	@Test
	public void RELRd_RelatedWordsEmpty() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with no related words [{}]", result.RATIORRD());
		assertEquals("RATIORRD was incorrect", 0.000000D, result.RATIORRD(), 0.000001D);
	}
	
	@Test
	public void RELRd_RelatedWordsSame() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		googleMldAssociated.add("alma alma");
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add("korte korte");
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with same related words [{}]", result.RATIORRD());
		assertTrue("RATIORRD was incorrect", result.RATIORRD() > 1D);
	}
	
	@Test
	public void RELRd_RelatedWordsDifferent() throws Exception {
		// Given
		Map<String, Collection<String>> RDTermindex = new HashMap<>();
		Set<String> googleMldAssociated = new HashSet<>();
		String googlemld = "www.google";
		googleMldAssociated.add("one two");
		RDTermindex.put(googlemld, googleMldAssociated);
		Set<String> googleMldpsAssociated = new HashSet<>();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add("three four");
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with different related words [{}]", result.RATIORRD());
		assertTrue("RATIORRD was incorrect", result.RATIORRD() > 1D);
	}
	
	@Test
	public void REMTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with no related words [{}]", result.RATIORREM());
		assertEquals("RATIORREM was incorrect", Double.NaN, result.RATIORREM(), 0.000001D);
	}
	
	@Test
	public void MLDTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("MLDRES with no related words [{}]", result.MLDRES());
		assertEquals("MLDRES was incorrect", 0, (int)result.MLDRES());
	}

	@Test
	public void MLDPSTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

		// When
		result.init();

		// Then
		logger.info("MLDPSRES with no related words [{}]", result.MLDPSRES());
		assertEquals("MLDPSRES was incorrect", 0, (int)result.MLDPSRES());
	}
	
	@Test
    public void alexaSocketTimeout() throws Exception {
        // Given
        Request wrappedRequest = request.Get(url);
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

        // When
        when(wrappedRequest.execute()).thenThrow(new SocketTimeoutException());
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
	@Test
    public void alexaRequestTimeout() throws Exception {
        // Given
        Request wrappedRequest = request.Get(url);
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

        // When
        when(wrappedRequest.execute()).thenThrow(new ConnectTimeoutException());
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
	@Test
    public void alexaConnectionError() throws Exception {
        // Given
        Request wrappedRequest = request.Get(url);
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

        // When
        when(wrappedRequest.execute()).thenThrow(new ConnectException());
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
    @Ignore
	@Test
    public void alexaHTTPError() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

        // When
        result.init();

        // Then
    }
	
	@Test
    public void alexaSuccess() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url);

        // When
        result.init();

        // Then
        assertEquals("The resulted alexa ranking was wrong", 6845, (int) result.RANKING());
    }

}