package fogetti.phish.storm.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, ResponseBody.class})
public class GoogleTrendsClientTest {
    
    @Test
    public void requestMandatory() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        try {
            GoogleTrends.Builder builder = new GoogleTrends.Builder(null, proxy, "keyword");
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void proxyMandatory() throws Exception {
        try {
            GoogleTrends.Builder builder = new GoogleTrends.Builder(mock(IRequest.class), null, "apache");
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void keywordMandatory() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        try {
            GoogleTrends.Builder builder = new GoogleTrends.Builder(mock(IRequest.class), proxy, null);
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }

    @Test
    public void socketTimeout() throws Exception {
        // java.net.SocketTimeoutException: connect timed out
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new SocketTimeoutException()), proxy, "apache").setHttpClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"));
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
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new SocketTimeoutException()), proxy, "apache").setHttpClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"));
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("The top searches should have failed with a connect timeout exception");
        } catch(SocketTimeoutException e) {
            // Nothing to see here folks
        }
        try {
            client.risingSearches();
            fail("The rising searches should have failed with a connect timeout exception");
        } catch(SocketTimeoutException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void requestError() throws Exception {
        // java.net.ConnectException: Connection refused        
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new ConnectException()), proxy, "apache").setHttpClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"));
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
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(new ErrorThrowingRequest(new SocketException()), proxy, "apache").setHttpClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"));
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
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(mock(IRequest.class), proxy, "valami").setHttpClient(OkClientUtil.getMockedClient("empty-top-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.topSearches();
        assertEquals("There were unexpected top searches", 0, topSearches.size());
    }

    @Test
    public void ordinaryTopSearches() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(mock(IRequest.class), proxy, "valami").setHttpClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.topSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
    }
    
    @Test
    public void emptyRisingSearches() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(mock(IRequest.class), proxy, "valami").setHttpClient(OkClientUtil.getMockedClient("empty-rising-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.risingSearches();
        assertEquals("There were unexpected top searches", 0, topSearches.size());
    }

    @Test
    public void ordinaryRisingSearches() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("myproxy", 80));
        GoogleTrends.Builder builder
        = new GoogleTrends.Builder(mock(IRequest.class), proxy, "valami").setHttpClient(OkClientUtil.getMockedClient("ordinary-rising-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.risingSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
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