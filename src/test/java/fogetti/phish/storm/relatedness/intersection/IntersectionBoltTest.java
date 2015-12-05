package fogetti.phish.storm.relatedness.intersection;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Test;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;

public class IntersectionBoltTest {

	@Test
	public void receivesEmptySet() throws Exception {
		// Given we want to compute intersections
		IntersectionBolt iBolt = new IntersectionBolt(null);

		Tuple input = mock(Tuple.class);
		when(input.getValue(0)).thenReturn(new HashSet<String>());
		when(input.getStringByField("segment")).thenReturn("something~http://valami.com");
		when(input.getStringByField("url")).thenReturn("http://google.com");
		OutputCollector collector = mock(OutputCollector.class);
		// When the input is empty
		iBolt.prepare(null, null, collector);
		iBolt.execute(input);

		// Then execute succeeds
		verify(input, atLeast(1)).getValue(0);
	}
}
