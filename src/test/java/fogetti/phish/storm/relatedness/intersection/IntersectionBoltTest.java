package fogetti.phish.storm.relatedness.intersection;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Test;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.tuple.Tuple;

public class IntersectionBoltTest {

	@Test
	public void canCreate() throws Exception {
		// Given we want to create an IntersectionBolt

		// When we call the constructor
		IntersectionBolt iBolt = new IntersectionBolt(null);

		// Then it succeeds
	}
	
	@Test
	public void receivesEmptySet() throws Exception {
		// Given we want to compute intersections
		IntersectionBolt iBolt = new IntersectionBolt(null);

		Tuple input = mock(Tuple.class);
		when(input.getValue(0)).thenReturn(new HashSet<String>());
		when(input.getStringByField("segment")).thenReturn("something~http://valami.com");
		when(input.getStringByField("url")).thenReturn("http://google.com");
		BasicOutputCollector collector = mock(BasicOutputCollector.class);
		// When the input is empty
		iBolt.execute(input, collector);

		// Then execute succeeds
		verify(input, atLeast(1)).getValue(0);
	}
}
