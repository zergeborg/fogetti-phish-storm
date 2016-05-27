package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Tuple;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;

import fogetti.phish.storm.client.Term;
import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.client.WebClientUtil;
import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.relatedness.SpyingGoogleSemBolt.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({Request.class, Response.class, ResponseBody.class})
public class GoogleSemBoltTest {

    private String paypal;
    private Jedis jedis;
    private Builder builder;
    private ObjectMapper mapper;
    private Terms segments;

    @Before
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        segments = new Terms();
        paypal = "paypal";
        jedis = mock(Jedis.class);
        URL proxyResource = this.getClass().getClassLoader().getResource("proxies.txt");
        File proxies = new File(proxyResource.toURI());
        JedisPoolConfig config = mock(JedisPoolConfig.class);
        builder = new SpyingGoogleSemBolt.Builder();
        builder.setConfig(config).setJedis(jedis).setProxies(proxies);
    }

    private List<Term> termsOf(String... string) {
        List<Term> paypalTerms = Arrays.asList(new Term[]{ new Term(string) });
        return paypalTerms;
    }
    
    @Test(expected = JedisException.class)
    public void redisRequestFails() throws Exception {
        // Given we want to get related words for a keyword
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("segment")).thenReturn(paypal);

        // When we send a request to redis
        when(jedis.get(anyString())).thenThrow(new JedisException("Error"));
        SpyingGoogleSemBolt bolt = builder.build();
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, mock(TopologyContext.class), mock(OutputCollector.class));
        bolt.execute(keyword);

        // Then it fails
    }

    @Test
    public void cachedSegmentFound() throws Exception {
        // Given we want to get related words for a keyword
        SpyingGoogleSemBolt bolt = builder.build();
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("segment")).thenReturn(paypal);
        segments.add(termsOf("paypal payment".split("\\s+")));
        String segString = mapper.writeValueAsString(segments);

        // When we send a request to redis
        when(jedis.get(anyString())).thenReturn(segString);
        OutputCollector collector = mock(OutputCollector.class);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, mock(TopologyContext.class), collector);
        bolt.execute(keyword);

        // Then it returns a cached segment
        verify(keyword, atLeast(1)).getStringByField("url");
        verify(collector).ack(keyword);
    }

    @Test
    public void cachedSegmentNotFound() throws Exception {
        // Given we want to get related words for a keyword
        SpyingGoogleSemBolt bolt = builder
                .setClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html"))
                .build();
        Tuple keyword = mock(Tuple.class);
        when(keyword.getStringByField("word")).thenReturn(paypal);

        // When we send a request to redis which returns no cached segment
        when(jedis.get(anyString())).thenReturn(null);
        OutputCollector collector = mock(OutputCollector.class);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, mock(TopologyContext.class), collector);
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
                .setClient(mock(WebClient.class))
                .build();
        Tuple input = mock(Tuple.class);
        when(input.getStringByField("word")).thenReturn(paypal);
        when(input.getStringByField("url")).thenReturn("url");
        when(jedis.get(anyString())).thenReturn(null);
        OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
        OutputCollector spy = spy(collector);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);

        // When the bolt sends a new query to Google
        bolt.prepare(config, mock(TopologyContext.class), spy);
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
                .setClient(WebClientUtil.getMockedWebClient("ordinary-top-searches.html"))
                .build();
        OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
        OutputCollector spy = spy(collector);
        Map<String, Object> config = new HashMap<>();
        config.put("timeout", 5000L);
        bolt.prepare(config, mock(TopologyContext.class), spy);

        // When the bolt sends a new query to Google and succeeds
        Tuple input = mock(Tuple.class);
        when(input.getStringByField("word")).thenReturn(paypal);
        when(input.getStringByField("url")).thenReturn("url");
        bolt.execute(input);

        // Then it sends top searches to the intersection bolt
        verify(input, atLeast(1)).getStringByField("word");
        verify(input, atLeast(1)).getStringByField("url");
        verify(spy, atLeast(1)).emit((Tuple)anyObject(), anyObject());
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

}