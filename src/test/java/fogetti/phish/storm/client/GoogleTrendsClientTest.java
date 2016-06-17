package fogetti.phish.storm.client;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gargoylesoftware.htmlunit.ProxyConfig;

import fogetti.phish.storm.exception.NotEnoughSearchVolumeException;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, ResponseBody.class})
public class GoogleTrendsClientTest {
    
    @Test
    public void proxyMandatory() throws Exception {
        try {
            GoogleTrends.Builder builder = new GoogleTrends.Builder(null, "apache");
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }
    
    @Test
    public void keywordMandatory() throws Exception {
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        try {
            GoogleTrends.Builder builder = new GoogleTrends.Builder(proxyConfig, null);
            builder.build();
            fail("The null argument should have cause NPE");
        } catch (NullPointerException e) {
            // Nothing to see here folks
        }
    }

    @Test
    public void socketTimeout() throws Exception {
        // java.net.SocketTimeoutException: connect timed out
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "apache").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html", SocketTimeoutException.class));
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
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "apache").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html", SocketTimeoutException.class));
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
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "apache").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html", ConnectException.class));
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
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "apache").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html", SocketException.class));
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
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "valami").setHttpClient(WebClientUtil.getMockedWebClient("empty-top-searches.html"));
        GoogleTrends client = builder.build();
        try {
            client.topSearches();
            fail("Empty top searches should have thrown an exception");
        } catch (NotEnoughSearchVolumeException e) {
            //
        }
    }

    @Test
    public void ordinaryTopSearches() throws Exception {
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "valami").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.topSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
    }
    
    @Test
    public void emptyRisingSearches() throws Exception {
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
            = new GoogleTrends.Builder(proxyConfig, "valami").setHttpClient(WebClientUtil.getMockedWebClient("empty-rising-searches.html"));
        GoogleTrends client = builder.build();
        try {
            client.risingSearches();
            fail("Empty rising searches should have thrown an exception");
        } catch (NotEnoughSearchVolumeException e) {
            //
        }
    }

    @Test
    public void ordinaryRisingSearches() throws Exception {
        ProxyConfig proxyConfig = new ProxyConfig("myproxy",80);
        GoogleTrends.Builder builder
        = new GoogleTrends.Builder(proxyConfig, "valami").setHttpClient(WebClientUtil.getMockedWebClient("ordinary-rising-searches.html"));
        GoogleTrends client = builder.build();
        Set<Term> topSearches = client.risingSearches();
        assertNotEquals("There were no top searches", 0, topSearches.size());
    }
    
}