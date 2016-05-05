package fogetti.phish.storm.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Ignore;
import org.junit.Test;

public class GoogleTrendsClientTest {
    
    @Test
    public void requestMandatory() throws Exception {
        GoogleTrends.Builder builder = new GoogleTrends.Builder(null, new HttpHost("myproxy", 80), "keyword");
        try {
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void proxyMandatory() throws Exception {
        GoogleTrends.Builder builder = new GoogleTrends.Builder(mock(IRequest.class), null, "apache");
        try {
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void keywordMandatory() throws Exception {
        GoogleTrends.Builder builder = new GoogleTrends.Builder(mock(IRequest.class), new HttpHost("myproxy", 80), null);
        try {
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }

    @Test
    public void socketTimeout() throws Exception {
        // java.net.SocketTimeoutException: connect timed out
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new SocketTimeoutException()), new HttpHost("myproxy", 80), "apache");
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("The top searches should have failed with a socket timeout exception");
        } catch(SocketTimeoutException e) {
            // Nothing to see here folks
        }
        try {
            client.risingSearches();
            fail("The rising searches should have failed with a socket timeout exception");
        } catch(SocketTimeoutException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void requestTimeout() throws Exception {
        // org.apache.http.conn.ConnectTimeoutException: Connect
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new ConnectTimeoutException()), new HttpHost("myproxy", 80), "apache");
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("The top searches should have failed with a connect timeout exception");
        } catch(ConnectTimeoutException e) {
            // Nothing to see here folks
        }
        try {
            client.risingSearches();
            fail("The rising searches should have failed with a connect timeout exception");
        } catch(ConnectTimeoutException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void requestError() throws Exception {
        // java.net.ConnectException: Connection refused        
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new ConnectException()), new HttpHost("myproxy", 80), "apache");
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("The top searches should have failed with a connection error");
        } catch(ConnectException e) {
            // Nothing to see here folks
        }
        try {
            client.risingSearches();
            fail("The rising searches should have failed with a connection error");
        } catch(ConnectException e) {
            // Nothing to see here folks
        }
    }
    
    @Ignore
    @Test
    public void responseError() throws Exception {
    }

    @Test
    public void socketError() throws Exception {
        // java.net.SocketException: Connection reset
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new SocketException()), new HttpHost("myproxy", 80), "apache");
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("The top searches should have failed with a socket exception");
        } catch(SocketException e) {
            // Nothing to see here folks
        }
        try {
            client.risingSearches();
            fail("The rising searches should have failed with a socket exception");
        } catch(SocketException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void emptyTopSearches() throws Exception {
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ResponseRequest("empty-top-searches.html"), new HttpHost("myproxy", 80), "valami");
        GoogleTrends client = builder.build();
        Set<String> topSearches = client.topSearches();
        assertEquals("There were unexpected top searches", 0, topSearches.size());
    }

    @Test
    public void ordinaryTopSearches() throws Exception {
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ResponseRequest("ordinary-top-searches.html"), new HttpHost("myproxy", 80), "valami");
        GoogleTrends client = builder.build();
        Set<String> topSearches = client.topSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
    }
    
    @Test
    public void emptyRisingSearches() throws Exception {
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ResponseRequest("empty-rising-searches.html"), new HttpHost("myproxy", 80), "valami");
        GoogleTrends client = builder.build();
        Set<String> topSearches = client.risingSearches();
        assertEquals("There were unexpected top searches", 0, topSearches.size());
    }

    @Test
    public void ordinaryRisingSearches() throws Exception {
        GoogleTrends.Builder builder
        = new GoogleTrends.Builder(new ResponseRequest("ordinary-rising-searches.html"), new HttpHost("myproxy", 80), "valami");
        GoogleTrends client = builder.build();
        Set<String> topSearches = client.risingSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
    }
    
    private static class ErrorThrowingRequest implements IRequest {
        
        private final Throwable t;
        
        public ErrorThrowingRequest(Throwable t){ this.t = t; }
        
        @Override
        public Request Get(String query) throws IOException {
            Request request = mock(Request.class);
            when(request.viaProxy((HttpHost)anyObject())).thenReturn(request);
            when(request.connectTimeout(anyInt())).thenReturn(request);
            when(request.socketTimeout(anyInt())).thenReturn(request);
            when(request.execute()).thenThrow(t);
            return request;
        }

        @Override
        public Response execute() throws ClientProtocolException, IOException { return null; }
    }

}