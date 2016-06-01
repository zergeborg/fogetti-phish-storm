package fogetti.phish.storm.io;

import java.nio.file.Path;
import java.nio.file.Paths;

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

    @Ignore
	@Test
    public void randomURLs() throws Exception {
        // Given
        Path source = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/valid-and-phishing-urls.csv");
        Path target = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/random-urls.csv");
	    
        // When
        CSVReader csvReader = new CSVReader(source, target);

        // Then
        csvReader.writeRandomUrls(78706);
    }

    @Ignore
    @Test
    public void uniqueURLs() throws Exception {
        // Given
        Path cntrol = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/phishing-urls.csv");
        Path source = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/valid-urls.csv");
        Path target = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/valid-urls.csv");

        // When
        CSVReader csvReader = new CSVReader(source, target);

        // Then
        csvReader.writeUniqueUrls(cntrol);
    }
    
}