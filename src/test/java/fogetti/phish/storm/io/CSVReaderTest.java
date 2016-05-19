package fogetti.phish.storm.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CSVReaderTest {

    @Ignore
	@Test
	public void readCsvStream() throws Exception {
		// Given
		Path source = Paths.get("/Users/gergely.nagy/Downloads/App/PhishStorm/verified-online-2015-12-03.csv");
		Path target = Paths.get("/Users/gergely.nagy/Downloads/App/PhishStorm/result/phishing-urls.csv");
		
		// When
		CSVReader csvReader = new CSVReader(source, target);

		// Then
		csvReader.writeUrls();
	}
	
	@Test
    public void testName() throws Exception {
        // Given
        Path source = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-05-18/valid-and-phishing-urls.csv");
        Path target = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-05-18/random-urls.csv");
	    
        // When
        CSVReader csvReader = new CSVReader(source, target);

        // Then
        csvReader.writeRandomUrls(78824);
    }
	
	@Ignore
	@Test
	public void test() throws Exception {
		// Given

		// When

		// Then
		System.out.println(Arrays.toString(StringUtils.split("alma/", "/")));
	}

}