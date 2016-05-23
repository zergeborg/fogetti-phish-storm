package fogetti.phish.storm.relatedness;

import java.io.File;

import org.apache.storm.redis.common.config.JedisPoolConfig;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

import fogetti.phish.storm.client.IRequest;

public class ClientBuildingGoogleSemBolt extends GoogleSemBolt {

    private static final long serialVersionUID = -8280832599330302218L;

    public ClientBuildingGoogleSemBolt(JedisPoolConfig config, File proxies, IRequest request) {
        super(config, proxies, request);
    }

    @Override
    public WebClient buildClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout((int)timeout);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        return webClient;
    }

}
