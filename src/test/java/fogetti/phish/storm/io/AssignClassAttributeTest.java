package fogetti.phish.storm.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AssignClassAttributeTest {

    @Test
    public void assignClassAttribute() throws Exception {
        // Given we have results without class attributes
        Path result = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-113/phishing-result.csv");
        Path classed = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-113/phishing-result-classified.csv");
        Path phish = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/phishing-urls.csv");
        Path valid = Paths.get("/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/valid-urls.csv");
        Set<String> pUrls = new HashSet<>(Files.readAllLines(phish));
        Set<String> vUrls = new HashSet<>(Files.readAllLines(valid));
        List<String> rList = Files.readAllLines(result);
        List<String> classified = new ArrayList<>();

        // When we compare the results with the original URLs
        for (String line : rList) {
            String link = StringUtils.substringAfter(line, "\"http:");
            if (!StringUtils.isBlank(link)) link = "http:" + StringUtils.removeEnd(link, "\"");
            else link = "https:" + StringUtils.removeEnd(StringUtils.substringAfter(line, "\"https:"), "\"");
            System.out.println("Checking: "+link);
            if (pUrls.contains(link)) classified.add(line+",no");
            else if (vUrls.contains(link)) classified.add(line+",yes");
            else {
                final String flink = link;
                pUrls.forEach(u -> {
                    if (u.startsWith(flink) && !classified.contains(line+",no")) classified.add(line+",no");
                });
                vUrls.forEach(u -> {
                    if (u.startsWith(flink) && !classified.contains(line+",no") && !classified.contains(line+",yes")) classified.add(line+",yes");
                });
                if (!classified.contains(line+",no") && !classified.contains(line+",yes")) classified.add(line+",bubu");
            }
        }
        
        // Then we get the class attributes appended
        Files.deleteIfExists(classed);
        Files.write(classed, classified);
    }
    
}