package fogetti.phish.storm.relatedness.intersection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.client.IRequest;
import fogetti.phish.storm.client.Term;
import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.client.WebClientUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, ResponseBody.class})
public class IntersectionResultTest {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionResultTest.class);
    private IRequest request;
    private String url;
    private OkHttpClient client;
    
    @Before
    public void setUp() throws Exception {
        request = mock(IRequest.class);
        url = "alma.com";
        client = WebClientUtil.getMockedClient("alexa-result.xml");
    }

    private List<Term> termsOf(String... string) {
        List<Term> paypalTerms = Arrays.asList(new Term[]{ new Term(string) });
        return paypalTerms;
    }
    
	@Test
	public void RATIOARem_NoAssociatedWords() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		paypalAssociated.add(termsOf(paypal));
		REMTermindex.put(paypal, paypalAssociated);
		Terms loginAssociated = new Terms();
		String login = "login";
        loginAssociated.add(termsOf(login));
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with no associated words [{}]", result.RATIOAREM());
		assertEquals("RATIOAREM was incorrect", 0.000000D, result.RATIOAREM(), 0.000001D);
	}
	
	@Test
	public void RATIOARem_AssociatedWordsAreSame() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		paypalAssociated.add(termsOf("paypal alma".split("\\s+")));
		paypalAssociated.add(termsOf("paypal alma".split("\\s+")));
		REMTermindex.put(paypal, paypalAssociated);
		Terms loginAssociated = new Terms();
		String login = "login";
		loginAssociated.add(termsOf("login korte".split("\\s+")));
		loginAssociated.add(termsOf("login korte".split("\\s+")));
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with same associated words [{}]", result.RATIOAREM());
		assertTrue("RATIOAREM was incorrect", result.RATIOAREM() > 1D);
	}

	@Test
	public void RATIOARem_AssociatedWordsDifferent() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		paypalAssociated.add(termsOf("paypal alma".split("\\s+")));
		paypalAssociated.add(termsOf("paypal korte".split("\\s+")));
		REMTermindex.put(paypal, paypalAssociated);
		Terms loginAssociated = new Terms();
		String login = "login";
		loginAssociated.add(termsOf("login barack".split("\\s+")));
		loginAssociated.add(termsOf("login cseresznye".split("\\s+")));
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOAREM with different associated words [{}]", result.RATIOAREM());
		assertTrue("RATIOAREM was incorrect", result.RATIOAREM() > 1D);
	}
	
	@Test
	public void RELRem_RelatedWordsEmpty() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		Terms loginAssociated = new Terms();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with no related words [{}]", result.RATIORREM());
		assertEquals("RATIORREM was incorrect", 0.000000D, result.RATIORREM(), 0.000001D);
	}
	
	@Test
	public void RELRem_RelatedWordsSame() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		paypalAssociated.add(termsOf("same same same same".split("\\s+")));
		Terms loginAssociated = new Terms();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		loginAssociated.add(termsOf("same same same same".split("\\s+")));
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with same related words [{}]", result.RATIORREM());
		assertTrue("RATIORREM was incorrect", result.RATIORREM() > 0D);
	}
	
	@Test
	public void RELRem_RelatedWordsDifferent() throws Exception {
		// Given
		Map<String, Terms> REMTermindex = new HashMap<>();
		Terms paypalAssociated = new Terms();
		String paypal = "paypal";
		REMTermindex.put(paypal, paypalAssociated);
		paypalAssociated.add(termsOf("one two three four".split("\\s+")));
		Terms loginAssociated = new Terms();
		String login = "login";
		REMTermindex.put(login, loginAssociated);
		loginAssociated.add(termsOf("five six seven eight".split("\\s+")));
		IntersectionResult result = new IntersectionResult(new HashMap<>(), REMTermindex, new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with different related words [{}]", result.RATIORREM());
		assertTrue("RATIORREM was incorrect", result.RATIORREM() > 1D);
	}
	
	@Test
	public void RATIOARd_NoAssociatedWords() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		googleMldAssociated.add(termsOf(googlemld));
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(termsOf(googlemldps));
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with no associated words [{}]", result.RATIOARD());
		assertEquals("RATIOARD was incorrect", 0.000000D, result.RATIOARD(), 0.000001D);
	}
	
	@Test
	public void RATIOARd_AssociatedWordsAreSame() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		googleMldAssociated.add(termsOf("alma alma".split("\\s+")));
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(termsOf("korte korte".split("\\s+")));
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with same associated words [{}]", result.RATIOARD());
		assertTrue("RATIOARD was incorrect", result.RATIOARD() < 1D);
	}

	@Test
	public void RATIOARd_AssociatedWordsDifferent() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		googleMldAssociated.add(termsOf("alma korte".split("\\s+")));
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(termsOf("barack cseresznye".split("\\s+")));
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIOARD with different associated words [{}]", result.RATIOARD());
		assertTrue("RATIOARD was incorrect", result.RATIOARD() < 1D);
	}
	
	@Test
	public void RELRd_RelatedWordsEmpty() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with no related words [{}]", result.RATIORRD());
		assertEquals("RATIORRD was incorrect", 0.000000D, result.RATIORRD(), 0.000001D);
	}
	
	@Test
	public void RELRd_RelatedWordsSame() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		googleMldAssociated.add(termsOf("alma alma".split("\\s+")));
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(termsOf("korte korte".split("\\s+")));
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with same related words [{}]", result.RATIORRD());
		assertTrue("RATIORRD was incorrect", result.RATIORRD() > 1D);
	}
	
	@Test
	public void RELRd_RelatedWordsDifferent() throws Exception {
		// Given
		Map<String, Terms> RDTermindex = new HashMap<>();
		Terms googleMldAssociated = new Terms();
		String googlemld = "www.google";
		googleMldAssociated.add(termsOf("one two".split("\\s+")));
		RDTermindex.put(googlemld, googleMldAssociated);
		Terms googleMldpsAssociated = new Terms();
		String googlemldps = "www.google.com";
		googleMldpsAssociated.add(termsOf("three four".split("\\s+")));
		RDTermindex.put(googlemldps, googleMldpsAssociated);
		IntersectionResult result = new IntersectionResult(RDTermindex, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORRD with different related words [{}]", result.RATIORRD());
		assertTrue("RATIORRD was incorrect", result.RATIORRD() > 1D);
	}
	
	@Test
	public void REMTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("RATIORREM with no related words [{}]", result.RATIORREM());
		assertEquals("RATIORREM was incorrect", 0.0, result.RATIORREM(), 0.000001D);
	}
	
	@Test
	public void MLDTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("MLDRES with no related words [{}]", result.MLDRES());
		assertEquals("MLDRES was incorrect", 0, (int)result.MLDRES());
	}

	@Test
	public void MLDPSTermIndex_Empty() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

		// When
		result.init();

		// Then
		logger.info("MLDPSRES with no related words [{}]", result.MLDPSRES());
		assertEquals("MLDPSRES was incorrect", 0, (int)result.MLDPSRES());
	}
	
	@Test
    public void alexaSocketTimeout() throws Exception {
        // Given
        IRequest request = new ErrorThrowingRequest(new SocketTimeoutException());
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
	@Test
    public void alexaRequestTimeout() throws Exception {
        // Given
        IRequest request = new ErrorThrowingRequest(new SocketTimeoutException());
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
	@Test
    public void alexaConnectionError() throws Exception {
        // Given
        IRequest request = new ErrorThrowingRequest(new ConnectException());
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertNull("The resulted alexa ranking was not null", result.RANKING());
    }
	
    @Ignore
	@Test
    public void alexaHTTPError() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
    }
	
	@Test
    public void alexaSuccess() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("The resulted alexa ranking was wrong", 6845, (int) result.RANKING());
    }

	@Test
    public void JRR_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("google.com")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blabber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRR was incorrect", 0.0, result.JRR(), 0.00001);
    }

    @Test
    public void JRR_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRR was incorrect", 1.0, result.JRR(), 0.00001);
    }

    @Test
    public void JRR_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("apple.com", new Terms(new Term("apple", "iphone")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("apple", new Terms(new Term("apple", "juice")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRR was incorrect", 0.3333333333333, result.JRR(), 0.00001);
    }

    @Test
    public void JRA_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("android")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blubber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRA was incorrect", 0.0, result.JRA(), 0.00001);
    }

    @Test
    public void JRA_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRA was incorrect", 1.0, result.JRA(), 0.00001);
    }

    @Test
    public void JRA_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("android", "google")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blabber", "android", "ios")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JRA was incorrect", 0.3333333333333, result.JRA(), 0.00001);
    }

    @Test
    public void JAA_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("android")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blubber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAA was incorrect", 1.0, result.JAA(), 0.00001);
    }

    @Test
    public void JAA_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAA was incorrect", 1.0, result.JAA(), 0.00001);
    }

    @Test
    public void JAA_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("google.com", "android", "google")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blabber", "android", "blubber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAA was incorrect", 0.3333333333333, result.JAA(), 0.00001);
    }

    @Test
    public void JAR_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("android")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("blubber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAR was incorrect", 0.0, result.JAR(), 0.00001);
    }

    @Test
    public void JAR_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAR was incorrect", 1.0, result.JAR(), 0.00001);
    }

    @Test
    public void JAR_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("google.com", "android", "google")));
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("blabber", new Terms(new Term("android", "blubber")));
        IntersectionResult result = new IntersectionResult(RD, REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JAR was incorrect", 0.3333333333333, result.JAR(), 0.00001);
    }

    @Test
    public void JARRD_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("google")));
        IntersectionResult result = new IntersectionResult(RD, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARRD was incorrect", 0.0, result.JARRD(), 0.00001);
    }

    @Test
    public void JARRD_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARRD was incorrect", 1.0, result.JARRD(), 0.00001);
    }

    @Test
    public void JARRD_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> RD = new HashMap<>();
        RD.put("google.com", new Terms(new Term("google.com", "google"), new Term("android")));
        IntersectionResult result = new IntersectionResult(RD, new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARRD was incorrect", 0.3333333333333, result.JARRD(), 0.00001);
    }

    @Test
    public void JARREM_emptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("apple", new Terms(new Term("coconut"),new Term("banana")));
        IntersectionResult result = new IntersectionResult(new HashMap<>(), REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARREM was incorrect", 0.0, result.JARREM(), 0.00001);
    }

    @Test
    public void JARREM_emptySets() throws Exception {
        // Given
        IntersectionResult result = new IntersectionResult(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARREM was incorrect", 1.0, result.JARREM(), 0.00001);
    }

    @Test
    public void JARREM_notEmptyIntersection() throws Exception {
        // Given
        HashMap<String, Terms> REM = new HashMap<>();
        REM.put("apple", new Terms(new Term("apple", "banana"), new Term("cherry")));
        IntersectionResult result = new IntersectionResult(new HashMap<>(), REM, new HashMap<>(), new HashMap<>(), request, url, client);

        // When
        result.init();

        // Then
        assertEquals("JARREM was incorrect", 0.3333333333333, result.JARREM(), 0.00001);
    }

    private static class ErrorThrowingRequest implements IRequest {
        
        private final IOException t;
        
        public ErrorThrowingRequest(IOException t){ this.t = t; }
        
        @Override
        public Request Get(String query) throws IOException {
            throw t;
        }

        @Override
        public Response execute() throws IOException { return null; }
    }

}