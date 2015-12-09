package fogetti.phish.storm.io;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CSVReaderTest {

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
	public void test() throws Exception {
		// Given

		// When

		// Then
		System.out.println(Arrays.toString(StringUtils.split("alma/", "/")));
	}

}