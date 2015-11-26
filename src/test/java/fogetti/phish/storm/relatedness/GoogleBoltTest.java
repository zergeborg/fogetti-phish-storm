package fogetti.phish.storm.relatedness;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

import org.apache.storm.commons.io.FileUtils;
import org.freaknet.gtrends.api.GoogleTrendsCsvParser;

public class GoogleBoltTest {
	
	protected HashSet<String> readSearchesFromFile() throws Exception {
		String searchresult = readSearchResult();
		HashSet<String> result = new HashSet<>();
		GoogleTrendsCsvParser parser = new GoogleTrendsCsvParser(searchresult);
		String searches = parser.getSectionAsString("Top searches", false);
		String[] lines = searches.split("\\r?\\n");
		for (String line : lines) {
			result.add(line.split(",")[0]);
		}
		searches = parser.getSectionAsString("Rising searches", false);
		lines = searches.split("\\r?\\n");
		for (String line : lines) {
			result.add(line.split(",")[0]);
		}
		return result;
	}

	protected String readSearchResult() throws URISyntaxException, IOException {
		URL paypaltxturl = this.getClass().getClassLoader().getResource("google-trends-paypal.txt");
		File paypaltxt = new File(paypaltxturl.toURI());
		String searchresult = FileUtils.readFileToString(paypaltxt);
		return searchresult;
	}

}
