package fogetti.phish.storm.io;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class URLListLoaderTest {

    private String urlDataFile;
    private Path source;
    private Path target;

    private TreeSet<String> readURLListFromFile() {
        TreeSet<String> urls = new TreeSet<>();
        loadUrls(urls);
        return urls;
    }

    private void loadUrls(Set<String> urls) {
        try {
            urls.addAll(Files.readAllLines(Paths.get(urlDataFile)));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private void writeUniqueUrls(Path ctlSource) {
        try {
            Set<String> result = new HashSet<>();
            System.out.println("Reading the source list");
            List<String> lines = Files.readAllLines(source);
            Map<String, String> map = lines
                    .stream()
                    .filter(u -> u.matches(".*http.*"))
                    .collect(
                            Collectors.toMap(
                                    p -> fetchLink(p),
                                    Function.identity(),
                                    (link1, link2) -> link1)
                            );
            System.out.println("Source size: "+map.size());
            System.out.println("Reading the control list");
            List<String> ctlLines = Files.readAllLines(ctlSource);
            Map<String, String> ctlmap = ctlLines
                    .stream()
                    .filter(u -> u.matches(".*http.*"))
                    .collect(Collectors.toMap(p -> fetchLink(p), Function.identity()));
            System.out.println("Control size: "+ctlmap.size());
            System.out.println("Reading done");
            result.addAll(ctlmap
                    .entrySet()
                    .stream()
                    .filter(e -> !map.containsKey(e.getKey()))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList()));
            System.out.println("Filtering done");
            System.out.println("Result size: "+result.size());
            System.out.println("Deleting the target file");
            Files.deleteIfExists(target);
            System.out.println("Writing results to file");
            Files.write(target, result);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }  

    private String fetchLink(String line) {
        String link = StringUtils.substringAfter(line, "\"http:");
        if (!StringUtils.isBlank(link)) link = "http:" + StringUtils.removePattern(link, "\".*$");
        else link = "https:" + StringUtils.removePattern(StringUtils.substringAfter(line, "\"https:"), "\".*$");
        return link;
    }
    
    @Ignore
    @Test
    public void load() throws Exception {
        // Given
        urlDataFile = "/Users/fogetti/Work/backup/alexa-result-2016-06-112/phishing-result.csv";

        // When
        TreeSet<String> urllist = readURLListFromFile();
        
        // Then
        assertEquals("The returned URL list size was different", 54434, urllist.size());
    }

    @Ignore
    @Test
    public void saveUnique() throws Exception {
        // Given
        urlDataFile = "/Users/fogetti/Work/backup/alexa-result-2016-06-114/alexa-result.csv";
        
        Set<String> urllist = readURLListFromFile();
        urllist = urllist.stream().filter(u -> u.matches(".*http.*")).collect(Collectors.toSet());

        // When
        Map<String, String> urlMap = new HashMap<>();
        for (String line : urllist) {
            String link = fetchLink(line);
            System.out.println(link);
            urlMap.put(link, line);
        }
        
        // Then
        String resultFile = "/Users/fogetti/Work/backup/alexa-result-2016-06-114/alexa-unique.csv";
        Path result = Paths.get(resultFile);
        Files.deleteIfExists(result);
        Files.write(result, urlMap.values(), UTF_8, WRITE, APPEND, CREATE);
    }

    @Ignore
    @Test
    public void combineUniqueRankings() throws Exception {
        // Given
        source = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-112/alexa-unique.csv");

        // When
        target = Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-112/phishing-result.csv");
        
        // Then
        writeUniqueUrls(Paths.get("/Users/fogetti/Work/backup/alexa-result-2016-06-112/alexa-control.csv"));
    }
    
    public static void main(String[] args) throws Exception {
        URLListLoaderTest test = new URLListLoaderTest();
        test.saveUnique();
    }

}