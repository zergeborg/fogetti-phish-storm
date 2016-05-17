package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import fogetti.phish.storm.client.IRequest;
import fogetti.phish.storm.client.OkClientUtil;
import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.relatedness.SpyingGoogleSemBolt.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, ResponseBody.class})
public class GoogleSemBoltTest extends GoogleBoltTest {

    private String paypal;
    private JedisCommands jedis;
    private IRequest request;
    private Builder builder;

    @Before
    public void setup() throws Exception {
        paypal = "paypal";
        jedis = mock(JedisCommands.class);
        URL proxyResource = this.getClass().getClassLoader().getResource("proxies.txt");
        File proxies = new File(proxyResource.toURI());
        JedisPoolConfig config = mock(JedisPoolConfig.class);
        request = mock(IRequest.class);
        builder = new SpyingGoogleSemBolt.Builder();
        builder.setConfig(config).setJedis(jedis).setProxies(proxies).setRequest(request);
    }

    @Test(expected = JedisException.class)
    public void redisRequestFails() throws Exception {
        // Given we want to get related words for a keyword
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("segment")).thenReturn(paypal);

        // When we send a request to redis
        when(jedis.smembers(anyString())).thenThrow(new JedisException("Error"));
        builder.build().execute(keyword);

        // Then it fails
    }

    @Test
    public void cachedSegmentFound() throws Exception {
        // Given we want to get related words for a keyword
        SpyingGoogleSemBolt bolt = builder.build();
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("segment")).thenReturn(paypal);
        Set<String> segments = new HashSet<>();
        segments.add("paypal payment");

        // When we send a request to redis
        when(jedis.smembers(anyString())).thenReturn(segments);
        OutputCollector collector = mock(OutputCollector.class);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, null, collector);
        bolt.execute(keyword);

        // Then it returns a cached segment
        verify(keyword, atLeast(1)).getStringByField("url");
        verify(collector).ack(keyword);
    }

    @Test
    public void cachedSegmentNotFound() throws Exception {
        // Given we want to get related words for a keyword
        SpyingGoogleSemBolt bolt = builder
                .setRequest(mock(IRequest.class))
                .setClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"))
                .build();
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("word")).thenReturn(paypal);

        // When we send a request to redis which returns no cached segment
        when(jedis.smembers(anyString())).thenReturn(null);
        OutputCollector collector = mock(OutputCollector.class);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, null, collector);
        bolt.execute(keyword);

        // Then we send a request to Google
        verify(keyword, atLeast(1)).getStringByField("url");
        verify(collector, atLeast(1)).emit((Tuple)anyObject(), anyObject());
        verify(collector).ack(keyword);
    }

    @Test
    public void googleRequestFailed() throws Exception {
        // Given we want to query Google Related data
        SpyingGoogleSemBolt bolt = builder
                .setRequest(new ErrorThrowingRequest(new SocketTimeoutException()))
                .setClient(mock(OkHttpClient.class))
                .build();

        // When the bolt sends a new query to Google
        Tuple input = mock(Tuple.class);
        when(input.getStringByField("word")).thenReturn(paypal);
        when(input.getStringByField("url")).thenReturn("url");
        OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
        OutputCollector spy = spy(collector);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, null, spy);
        bolt.execute(input);

        // Then it fails
        verify(input, atLeast(1)).getStringByField("word");
        verify(input, atLeast(1)).getStringByField("url");
        verify(spy, atLeast(1)).fail(input);
    }

    @Test
    public void googleRequestSucceeds() throws Exception {
        // Given we want to query Google Related data
        SpyingGoogleSemBolt bolt = builder
                .setRequest(mock(IRequest.class))
                .setClient(OkClientUtil.getMockedClient("ordinary-top-searches.html"))
                .build();
        OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
        OutputCollector spy = spy(collector);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, null, spy);

        // When the bolt sends a new query to Google and succeeds
        Tuple input = mock(Tuple.class);
        when(input.getStringByField("word")).thenReturn(paypal);
        when(input.getStringByField("url")).thenReturn("url");
        bolt.execute(input);

        // Then it sends top searches to the intersection bolt
        verify(input, atLeast(1)).getStringByField("word");
        verify(input, atLeast(1)).getStringByField("url");
        HashSet<String> tops = readTopSearchesFromFile("ordinary-top-searches.html");
        Values topSearches = new Values(tops, paypal, "url");
        verify(spy, atLeast(1)).emit(input, topSearches);
        verify(spy, atLeast(1)).ack(input);
    }

    @Ignore
    @Test
    public void integration() throws Exception {
        // Given we want to query Google Related data
        GoogleSemBolt bolt = new ClientBuildingGoogleSemBolt(null, null, new WrappedRequest());

        // When the bolt receives a new tuple
        Tuple input = mock(Tuple.class);
        when(input.getStringByField("segment")).thenReturn("paypal");
        when(input.getStringByField("url")).thenReturn("http://google.com");
        bolt.execute(input);

        // Then it sends a new query to Google
        verify(input, atLeast(1)).getString(0);
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