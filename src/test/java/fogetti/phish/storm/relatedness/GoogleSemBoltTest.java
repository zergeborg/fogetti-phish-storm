package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Scanner;

import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.junit.Ignore;
import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class GoogleSemBoltTest extends GoogleBoltTest {

	private static class SpyingGoogleSemBolt extends GoogleSemBolt {
		
		private static final long serialVersionUID = 3798709600022221909L;
		
		private GoogleTrendsClient client;

		public SpyingGoogleSemBolt(GoogleTrendsClient client) {
			super(client);
			this.client = client;
		}
		
		@Override
		GoogleTrendsClient getClient() {
		    return client;
		}
		
	}
	
	@Override
	protected String section() {
		return "Top searches";
	}

	@Test
	public void canCreate() throws Exception {
		// Given we want to query Google Related data
		GoogleTrendsClient client = mock(GoogleTrendsClient.class);

		// When we want to construct a Bolt ofr this
		GoogleSemBolt bolt = new GoogleSemBolt(client);

		// Then it succeeds
	}
	
	@Test
	public void requestSent() throws Exception {
		// Given we want to query Google Related data
		GoogleTrendsClient client = mock(GoogleTrendsClient.class);
		GoogleSemBolt bolt = new GoogleSemBolt(client);

		// When the bolt receives a new tuple
		Tuple input = mock(Tuple.class);
		when(input.getString(0)).thenReturn("paypal");
		bolt.execute(input);

		// Then it sends a new query to Google
		verify(input, atLeast(1)).getString(0);
		verify(client, atLeast(1)).execute(anyObject());
	}
	
	@Test
	public void topSearches() throws Exception {
		// Given we want to query Google Related data
		GoogleTrendsClient client = mock(GoogleTrendsClient.class);
		GoogleSemBolt bolt = new SpyingGoogleSemBolt(client);
		OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
		OutputCollector spy = spy(collector);
		bolt.prepare(null, null, spy);

		// When the bolt receives a new tuple
		Tuple input = mock(Tuple.class);
		when(input.getString(0)).thenReturn("paypal");
		when(client.execute(anyObject())).thenReturn(readSearchResult());
		bolt.execute(input);

		// Then it sends top searches to the intersection bolt
		verify(input, atLeast(1)).getString(0);
		verify(client, atLeast(1)).execute(anyObject());
		HashSet<String> tops = readSearchesFromFile();
		Values topSearches = new Values(tops);
		verify(spy, atLeast(1)).emit(input, topSearches);
		verify(spy, atLeast(1)).ack(input);
	}

	@Ignore
	@Test
	public void integration() throws Exception {
		// Given we want to query Google Related data
		Scanner console = new Scanner(System.in);
		String uname = "";
		if (console.hasNext())
			uname = console.next();
		String pword = "";
		if (console.hasNext())
			pword = console.next();
		console.close();
		GoogleSemBolt bolt = new GoogleSemBolt(uname, pword);

		// When the bolt receives a new tuple
		Tuple input = mock(Tuple.class);
		when(input.getString(0)).thenReturn("paypal");
		bolt.execute(input);

		// Then it sends a new query to Google
		verify(input, atLeast(1)).getString(0);
	}
}
