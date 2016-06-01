package fogetti.phish.storm.io;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class URLListLoaderTest {

    private String urlDataFile;

    private TreeSet<String> readURLListFromFile() {
        TreeSet<String> urls = new TreeSet<>();
        loadUrls(urls);
        return urls;
    }

    private void loadUrls(Set<String> urls) {
        try(Scanner scanner = new Scanner(Paths.get(urlDataFile))) {
            while (scanner.hasNextLine()) {
                urls.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @Ignore
    @Test
    public void load() throws Exception {
        // Given
        urlDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/training-2016-06-01/random-urls.csv";

        // When
        TreeSet<String> urllist = readURLListFromFile();
        
        // Then
        assertEquals("The returned URL list size was different", 78706, urllist.size());
    }

}