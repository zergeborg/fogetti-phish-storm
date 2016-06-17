package fogetti.phish.storm.relatedness;

import java.io.File;

import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.storm.redis.common.config.JedisPoolConfig;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpWebConnection;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebConnection;

import fogetti.phish.storm.client.IRequest;

public class ClientBuildingGoogleSemBolt extends GoogleSemBolt {

    private static final long serialVersionUID = -8280832599330302218L;

    public ClientBuildingGoogleSemBolt(JedisPoolConfig config, File proxies, IRequest request) {
        super(config, proxies);
    }

    @Override
    public WebClient buildClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        WebConnection webCon = new NoRetryWebConnection(webClient);
        webClient.setWebConnection(webCon);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setRefreshHandler(new ThreadedRefreshHandler());
        webClient.waitForBackgroundJavaScript(10000);
        webClient.waitForBackgroundJavaScriptStartingBefore(10000);
        webClient.getOptions().setTimeout((int)timeout);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        return webClient;
    }
    
    private static class NoRetryWebConnection extends HttpWebConnection {

        public NoRetryWebConnection(WebClient webClient) {
            super(webClient);
        }
        
        @Override
        protected HttpClientBuilder getHttpClientBuilder() {
            HttpClientBuilder builder = super.getHttpClientBuilder();
            builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
            return builder;
        }
        
        @Override
        protected HttpClientBuilder createHttpClient() {
            HttpClientBuilder builder = super.createHttpClient();
            builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
            return builder;
        }
        
    }

}
