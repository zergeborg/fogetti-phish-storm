package fogetti.phish.storm.client;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fogetti.phish.storm.exception.NotEnoughSearchVolumeException;
import fogetti.phish.storm.exception.QuotaLimitException;

public class GoogleTrends {

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
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=TOP_QUERIES_0_0", keyword);
        String html = buildHtml(query);
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            Set<Term> result = new HashSet<>();
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                if (!tds.isEmpty()) {
                    String text = tds.get(0).text();
                    if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
                }
            }
            return result;
        }
        return null;
    }

    public Set<Term> risingSearches() throws IOException {
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=RISING_QUERIES_0_0", keyword);
        String html = buildHtml(query);
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            Set<Term> result = new HashSet<>();
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                if (!tds.isEmpty()) {
                    String text = tds.get(0).text();
                    if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
                }
            }
            return result;
        }
        return null;
    }

    private String buildHtml(String query) throws IOException {
        webClient.getOptions().setProxyConfig(proxyConfig);
        Page page = webClient.getPage(query);

        if (page.isHtmlPage()) {
            final HtmlPage htmlPage = (HtmlPage) page;
            final String html = htmlPage.asXml();
            return html;
        } else if (page instanceof TextPage) {
            final TextPage textPage = (TextPage) page;
            final String html = textPage.getContent();
            return html;
        } else {
            return "";
        }
    }

    private void findError(Document doc) {
        Elements mainElem = doc.select(".trends-component-error");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                if (!tds.isEmpty()) {
                    String error = tds.get(0).text();
                    if (error.contains("Not enough search volume to show results")) throw new NotEnoughSearchVolumeException();
                }
            }
        }
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