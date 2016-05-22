package fogetti.phish.storm.client;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.exception.QuotaLimitException;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class GoogleTrends {

    private static final Logger logger = LoggerFactory.getLogger(GoogleTrends.class);

    private final String keyword;
    private final IRequest request;
    private final OkHttpClient client;

    private GoogleTrends(OkHttpClient client, IRequest request, String keyword) {
        if (client == null) {
            throw new NullPointerException();
        }
        this.client = client;
        this.request = request;
        this.keyword = keyword;
    }

    public Set<Term> topSearches() throws IOException {
        Set<Term> result = new HashSet<>();
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=TOP_QUERIES_0_0", keyword);
        Response response = client.newCall(request.Get(query)).execute();
        String html = response.body().string();
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                String text = tds.get(0).text();
                if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
            }
        }
        return result;
    }

    public Set<Term> risingSearches() throws IOException {
        Set<Term> result = new HashSet<>();
        String query
            = String.format("http://www.google.com/trends/fetchComponent?hl=en-US&q=%s&cid=RISING_QUERIES_0_0", keyword);
        Response response = client.newCall(request.Get(query)).execute();
        String html = response.body().string();
        Document doc = Jsoup.parse(html);
        findError(doc);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td > span:first-child");
                String text = tds.get(0).text();
                if (StringUtils.isNotBlank(text)) result.add(new Term(text.split("\\s+")));
            }
        }
        return result;
    }

    private void findError(Document doc) {
        Elements errorDiv = doc.select(".errorSubTitle");
        String error = errorDiv.get(0).text();
        if (error.contains("You have reached your quota limit")) throw new QuotaLimitException();
    }

    public static class Builder {

        private final Proxy proxy;
        private final String keyword;
        private final IRequest request;
        private Integer connectTimeout = 5000;
        private Integer socketTimeout = 5000;
        private OkHttpClient client = null;

        public Builder(IRequest request, Proxy proxy, String keyword) {
            if (request == null || proxy == null || keyword == null) {
                throw new NullPointerException();
            }
            this.request = request;
            this.proxy = proxy;
            this.keyword = keyword;
        }

        public Builder setHttpClient(OkHttpClient client) {
            this.client = client;
            return this;
        }
        
        public Builder setConnectTimeout(Integer connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        
        public Builder setSocketTimeout(Integer socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }
        
        private OkHttpClient buildClient() {
            if (client == null) {
                client = new OkHttpClient
                        .Builder()
                        .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                        .readTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                        .writeTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                        .proxy(proxy)
                        .build();

            }
            return client;
        }        

        public GoogleTrends build() {
            return new GoogleTrends(buildClient(), request, keyword);
        }
    }

}