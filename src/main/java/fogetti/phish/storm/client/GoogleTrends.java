package fogetti.phish.storm.client;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleTrends {

    private static final Logger logger = LoggerFactory.getLogger(GoogleTrends.class);

    private final HttpHost httpHost;
    private final String keyword;
    private final IRequest request;
    private final Integer connectTimeout;
    private final Integer socketTimeout;

    private GoogleTrends(IRequest request, HttpHost httpHost, String keyword, Integer connectTimeout, Integer socketTimeout) {
        if (request == null || httpHost == null || keyword == null || connectTimeout == null || socketTimeout == null) {
            throw new NullPointerException();
        }
        this.request = request;
        this.httpHost = httpHost;
        this.keyword = keyword;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    public Set<String> topSearches() throws IOException {
        Set<String> result = new HashSet<>();
        String query
        = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&geo=US&cid=TOP_QUERIES_0_0", keyword);
        String html = request.Get(query)
                .viaProxy(httpHost)
                .connectTimeout(connectTimeout)
                .socketTimeout(socketTimeout)
                .execute()
                .returnContent()
                .asString();
        Document doc = Jsoup.parse(html);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                result.add(tds.get(0).text());
            }
        }
        return result;
    }

    public Set<String> risingSearches() throws IOException {
        Set<String> result = new HashSet<>();
        String query
        = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&geo=US&cid=RISING_QUERIES_0_0", keyword);
        String html = request.Get(query)
                .viaProxy(httpHost)
                .connectTimeout(connectTimeout)
                .socketTimeout(socketTimeout)
                .execute()
                .returnContent()
                .asString();
        Document doc = Jsoup.parse(html);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                result.add(tds.get(0).text());
            }
        }
        return result;
    }

    public static class Builder {

        private final HttpHost httpHost;
        private final String keyword;
        private final IRequest request;
        private Integer connectTimeout = 60000;
        private Integer socketTimeout = 60000;

        public Builder(IRequest request, HttpHost httpHost, String keyword) {
            this.request = request;
            this.httpHost = httpHost;
            this.keyword = keyword;
        }

        public Builder setConnectTimeout(Integer connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        
        public Builder setSocketTimeout(Integer socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }
        
        public GoogleTrends build() {
            return new GoogleTrends(request, httpHost, keyword, connectTimeout, socketTimeout);
        }        
    }

}