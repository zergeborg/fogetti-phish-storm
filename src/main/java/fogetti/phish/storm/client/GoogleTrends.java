package fogetti.phish.storm.client;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fogetti.phish.storm.exception.QuotaLimitException;

public class GoogleTrends {

    private static final Logger logger = LoggerFactory.getLogger(GoogleTrends.class);

    private final String keyword;
    private final WebClient webClient;
    private final ProxyConfig proxyConfig;


    private GoogleTrends(WebClient webClient, String keyword, ProxyConfig proxy) {
        if (webClient == null) {
            throw new NullPointerException();
        }
        this.webClient = webClient;
        this.keyword = keyword;
        this.proxyConfig = proxy;
    }

    public Set<Term> topSearches() throws IOException {
        Set<Term> result = new HashSet<>();
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=TOP_QUERIES_0_0", keyword);
        String html = buildHtml(query);
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                if (!tds.isEmpty()) {
                    String text = tds.get(0).text();
                    if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
                }
            }
        }
        return result;
    }

    public Set<Term> risingSearches() throws IOException {
        Set<Term> result = new HashSet<>();
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=RISING_QUERIES_0_0", keyword);
        String html = buildHtml(query);
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                if (!tds.isEmpty()) {
                    String text = tds.get(0).text();
                    if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
                }
            }
        }
        return result;
    }

    private String buildHtml(String query) throws IOException {
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(60000);
        webClient.getOptions().setProxyConfig(proxyConfig);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setRedirectEnabled(true);
        Page page = webClient.getPage(query);

        webClient.waitForBackgroundJavaScript(10000);
        webClient.waitForBackgroundJavaScriptStartingBefore(10000);

        if (page.isHtmlPage()) {
            final HtmlPage htmlPage = (HtmlPage) page;
            final String html = htmlPage.asXml();
            return html;
        } else {
            final TextPage textPage = (TextPage) page;
            final String html = textPage.getContent();
            return html;
        }
    }

    private void findError(Document doc) {
        Elements errorDiv = doc.select(".errorSubTitle");
        if (errorDiv.isEmpty()) return;
        String error = errorDiv.get(0).text();
        if (error.contains("You have reached your quota limit")) throw new QuotaLimitException();
    }

    public static class Builder {

        private final String keyword;
        private final ProxyConfig proxyConfig;
        private Integer connectTimeout = 5000;
        private WebClient webClient;

        public Builder(ProxyConfig proxyConfig, String keyword) {
            if (proxyConfig == null || keyword == null) {
                throw new NullPointerException();
            }
            this.proxyConfig = proxyConfig;
            this.keyword = keyword;
        }

        public Builder setHttpClient(WebClient client) {
            this.webClient = client;
            return this;
        }
        
        public Builder setConnectTimeout(Integer connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        
        private WebClient buildClient() {
            if (webClient == null) {
                webClient = new WebClient(BrowserVersion.FIREFOX_38);
                webClient.setAjaxController(new NicelyResynchronizingAjaxController());
                webClient.getOptions().setTimeout(connectTimeout);
                webClient.getOptions().setProxyConfig(proxyConfig);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
                webClient.getOptions().setCssEnabled(true);
                webClient.getOptions().setRedirectEnabled(true);
            }
            return webClient;
        }        

        public GoogleTrends build() {
            return new GoogleTrends(buildClient(), keyword, proxyConfig);
        }
    }

}