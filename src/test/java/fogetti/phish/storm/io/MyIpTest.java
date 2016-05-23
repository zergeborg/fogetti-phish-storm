package fogetti.phish.storm.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

@Ignore
public class MyIpTest {

    private class CoookieJar implements CookieJar {

        private final Map<HttpUrl, List<Cookie>> jar = new HashMap<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            jar.put(url, cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return jar.get(url) == null ? new ArrayList<>() : jar.get(url);
        }

    }

    private class IpCallback implements Callback {
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                String html = response.body().string();
                Path path = Paths.get("/Users/fogetti/Work/my-ip.html");
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    writer.write(html);
                }
                Document doc = Jsoup.parse(html);
                Elements ipDiv = doc.select("span#ip");
                if (!ipDiv.isEmpty()) System.out.println(ipDiv.get(0).text());
            } finally {
                latch.countDown();
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            try {
                System.err.println(e.getMessage());
            } finally {
                latch.countDown();
            }
        }
    }

    private Builder builder;
    private List<String> proxyList;
    private CountDownLatch latch;
    private ConnectionSpec spec;
    private int timeout = 60000;

    @Before
    public void setup() throws Exception {
        builder = new OkHttpClient
                .Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS);
        Path proxies = Paths.get("/Users/fogetti/Work/proxy-check/[freeproxylists.com]elite_proxies_2016_05_22.txt");
        proxyList = Files.readAllLines(proxies);
        latch = new CountDownLatch(proxyList.size());
    }

    @Test
    public void myIp() throws Exception {
        // Given we use OkHttp with proxy
        for (String proxyStr : proxyList) {
            int nextPick = new Random().nextInt(proxyList.size());
            String nextProxy = proxyList.get(nextPick);
            System.out.println("Next proxy: " + nextProxy);
            String[] hostAndPort = nextProxy.split(":");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(hostAndPort[0],Integer.parseInt(hostAndPort[1])));
            OkHttpClient client = builder
                    .cookieJar(new CoookieJar())
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .proxy(proxy)
                    .build();

            // When we send request to Google My Ip
            String gtrends = "http://www.google.com/trends/fetchComponent?hl=en-US&q=storm&cid=TOP_QUERIES_0_0";
            String gtrends2 = "https://www.google.com/trends?q=storm";
            String duckduck = "https://duckduckgo.com/?q=%21gtrends+storm";
            client.newCall(
                    new Request
                    .Builder()
                    .url(gtrends)
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                    .build()
                    ).enqueue(new IpCallback());

            // Then it returns something different from the real IP
        }
        latch.await();
    }

    @Test
    public void trends() throws Exception {
        // Given we use OkHttp with proxy
        for (String proxyStr : proxyList) {
            int nextPick = new Random().nextInt(proxyList.size());
            String nextProxy = proxyList.get(nextPick);
            System.out.println("Next proxy: " + nextProxy);
            String[] hostAndPort = nextProxy.split(":");

            // When we send request to Google My Ip
            String gtrends = "http://www.google.com/trends/fetchComponent?hl=en-US&q=storm&cid=TOP_QUERIES_0_0";

            try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38)) {
                webClient.setAjaxController(new NicelyResynchronizingAjaxController());
                ProxyConfig proxyConfig = new ProxyConfig(hostAndPort[0],Integer.parseInt(hostAndPort[1]));
                webClient.getOptions().setTimeout(timeout);
                webClient.getOptions().setProxyConfig(proxyConfig);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
                webClient.getOptions().setCssEnabled(true);
                webClient.getOptions().setRedirectEnabled(true);
                final HtmlPage page = webClient.getPage(gtrends);

                webClient.waitForBackgroundJavaScript(10000);
                webClient.waitForBackgroundJavaScriptStartingBefore(10000);

                final String html = page.asXml();
                Path path = Paths.get("/Users/fogetti/Work/trends.html");
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    writer.write(html);
                }
            } catch(Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
    
    @Test
    public void addressString() throws Exception {
        // Given
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("202.167.248.186",80));

        // When
        System.out.println(proxy.address());
        
        // Then
    }

}