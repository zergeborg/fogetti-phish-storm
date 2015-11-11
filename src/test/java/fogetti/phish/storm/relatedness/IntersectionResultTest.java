package fogetti.phish.storm.relatedness;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntersectionResultTest {

	@Test
	public void reflexive() throws Exception {
		// Given
		IntersectionResult result1 = new IntersectionResult("testId");
		IntersectionResult result2 = new IntersectionResult("testId");

		// When
		assertEquals("The two intersection results were not equal", result1, result2);

		// Then
		assertEquals("The two intersection results were not equal", result2, result1);
	}
	
	@Test
	public void symmetric() throws Exception {
		// Given
		IntersectionResult result = new IntersectionResult("testId");

		// When
		// ...

		// Then
		assertEquals("The intersection result was not equal with itself", result, result);
	}

	@Test
	public void transitive() throws Exception {
		// Given
		IntersectionResult result1 = new IntersectionResult("testId");
		IntersectionResult result2 = new IntersectionResult("testId");
		IntersectionResult result3 = new IntersectionResult("testId");

		// When
		assertEquals("The two intersection results were not equal", result1, result2);
		assertEquals("The two intersection results were not equal", result2, result3);

		// Then
		assertEquals("The two intersection results were not equal", result1, result3);
	}

	@Test
	public void consistent() throws Exception {
		// Given
		IntersectionResult result1 = new IntersectionResult("testId");
		IntersectionResult result2 = new IntersectionResult("testId");

		// When
		assertEquals("The two intersection results were not equal", result1, result2);

		// Then
		assertEquals("The two intersection results were not equal", result1, result2);
	}
}