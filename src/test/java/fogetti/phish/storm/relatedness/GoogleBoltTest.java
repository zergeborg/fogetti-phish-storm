package fogetti.phish.storm.relatedness;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleBoltTest {
	
	protected HashSet<String> readTopSearchesFromFile(String file) throws Exception {
		String searchresult = readSearchResultFrom(file);
		HashSet<String> result = new HashSet<>();
        Document doc = Jsoup.parse(searchresult);
        Elements mainElem = doc.select(".trends-table-data");
        if (mainElem.size() > 0) {
            Element table = mainElem.get(0);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                result.add(tds.get(0).text());
            }
        }
		return result;
	}

	protected String readSearchResultFrom(String file) throws URISyntaxException, IOException {
		URL url = this.getClass().getClassLoader().getResource(file);
		File txt = new File(url.toURI());
		String result = FileUtils.readFileToString(txt);
		return result;
	}

}
