package fogetti.phish.storm.io;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AlexaRankingTest {

    private static class DataAndRank {
        public String data;
        public String rank;
        private DataAndRank(String data, String rank) {
            this.data = data;
            this.rank = rank;
        }
    }
    
    private Map<String, DataAndRank> fetchUrls(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Map<String, DataAndRank> map = lines
            .stream()
            .filter(u -> u.matches(".*http.*"))
            .collect(Collectors.toMap(
                    p -> fetchLink(p),
                    p -> fetchRank(p),
                    (link1, link2) -> link1)
                    );
        return map;
    }
    
    private String fetchLink(String line) {
        String link = StringUtils.substringAfter(line, "\"http:");
        if (!StringUtils.isBlank(link)) link = "http:" + StringUtils.removePattern(link, "\".*$");
        else link = "https:" + StringUtils.removePattern(StringUtils.substringAfter(line, "\"https:"), "\".*$");
        return link;
    }
    
    private DataAndRank fetchRank(String line) {
        String data = StringUtils.substringBeforeLast(StringUtils.substringBefore(line, ",\"http"), ",");
        String rank = StringUtils.substringAfterLast(StringUtils.substringBefore(line, ",\"http"), ",");
        return new DataAndRank(data, rank);
    }

    @Test
    public void addMissingRanking() throws Exception {
        // Given
        Path phishSource = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-113/phishing-source.csv");
        Path alexaSource = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-113/alexa-source.csv");
        Path phishResult = Paths.get("/Users/fogetti/Work/backup/phish-result-2016-06-10/phishing-result.csv");
        Path target = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-113/phishing-result.csv");
        
        // When
        Map<String, DataAndRank> alexamap = fetchUrls(phishSource);
        alexamap.putAll(fetchUrls(alexaSource));
        
        Map<String, DataAndRank> phishmap = fetchUrls(phishResult);
        phishmap
            .entrySet()
            .stream()
            .forEach(e -> {
                if (alexamap.containsKey(e.getKey())) {
                    DataAndRank dataAndRank = alexamap.get(e.getKey());
                    if ("null".equals(e.getValue().rank)) {
                        System.out.println(String.format("Changed rank [%s] to [%s]", e.getValue().rank, dataAndRank.rank));
                        e.getValue().rank = dataAndRank.rank;
                    }
                } else {
                    System.out.println(e.getKey() + " not found");
                }
            });

        // Then
        List<String> lines = phishmap
            .entrySet()
            .stream()
            .map(e -> e.getValue().data+","+e.getValue().rank+",\""+e.getKey()+"\"")
            .collect(Collectors.toList());
        Files.deleteIfExists(target);
        Files.write(target, lines, UTF_8, WRITE, APPEND, CREATE);
    }
    
}