package fogetti.phish.storm.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
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
    
    @Ignore
    @Test
    public void requestTimeout() throws Exception {
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
    }
    
    private static class ResponseRequest implements IRequest {
        
        private final String contentStr;
        
        public ResponseRequest(String file) throws IOException, URISyntaxException {
            URL resource = GoogleTrendsClientTest.class.getResource(file);
            this.contentStr = new String(Files.readAllBytes(Paths.get(new File(resource.toURI()).getAbsolutePath())));
        }
        
        @Override
        public Request Get(String query) throws IOException {
            Request request = mock(Request.class);
            Response response = mock(Response.class);
            Content content = mock(Content.class);
            when(request.viaProxy((HttpHost)anyObject())).thenReturn(request);
            when(request.connectTimeout(anyInt())).thenReturn(request);
            when(request.socketTimeout(anyInt())).thenReturn(request);
            when(request.execute()).thenReturn(response);
            when(response.returnContent()).thenReturn(content);
            when(content.asString()).thenReturn(contentStr);
            return request;
        }        
    }

}