package fogetti.phish.storm.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import fogetti.phish.storm.exception.QuotaLimitException;

@Ignore
public class QuotaLimitTest {

    @Test(expected = QuotaLimitException.class)
    public void quotaLimitError() throws Exception {
        // Given
        Path source = Paths.get("/Users/fogetti/Work/output_1463903437982.html");

        // When
        String html = new String(Files.readAllBytes(source));
        Document doc = Jsoup.parse(html);

        // Then
        findError(doc);
    }
    
    private void findError(Document doc) {
        Elements errorDiv = doc.select(".errorSubTitle");
        String error = errorDiv.get(0).text();
        if (error.contains("You have reached your quota limit")) throw new QuotaLimitException();
    }
    
}