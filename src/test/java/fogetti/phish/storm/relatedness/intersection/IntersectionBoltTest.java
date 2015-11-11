package fogetti.phish.storm.relatedness.intersection;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.tuple.Tuple;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;

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
		BasicOutputCollector collector = mock(BasicOutputCollector.class);
		// When the input is empty
		iBolt.execute(input, collector);

		// Then execute succeeds
		verify(input, atLeast(1)).getValue(0);
	}
}
