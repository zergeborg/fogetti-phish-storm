package fogetti.phish.storm.relatedness;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class GoogleRisingBoltTest extends GoogleBoltTest {

	@Override
	protected String section() {
		return "Rising searches";
	}

	@Test
	public void topSearches() throws Exception {
		// Given we want to query Google Related data
		GoogleTrendsClient client = mock(GoogleTrendsClient.class);
		GoogleBolt bolt = new GoogleRisingBolt(client);
		OutputCollector collector = new OutputCollector(mock(OutputCollector.class));
		OutputCollector spy = spy(collector);
		bolt.prepare(null, null, spy);

		// When the bolt receives a new tuple
		Tuple input = mock(Tuple.class);
		when(input.getString(0)).thenReturn("paypal");
		when(client.execute(anyObject())).thenReturn(readSearchResult());
		bolt.execute(input);

		// Then it sends rising searches to the intersection bolt
		verify(input, atLeast(1)).getString(0);
		verify(client, atLeast(1)).execute(anyObject());
		HashSet<String> tops = readSearchesFromFile();
		Values risingSearches = new Values(tops);
		verify(spy, atLeast(1)).emit(input, risingSearches);
		verify(spy, atLeast(1)).ack(input);
	}

}
