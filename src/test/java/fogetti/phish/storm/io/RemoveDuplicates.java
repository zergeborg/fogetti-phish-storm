package fogetti.phish.storm.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.lang3.StringUtils;

public class RemoveDuplicates {

    public static void main(String[] args) throws IOException {
        Map<String, CSVRecord> unique = new HashMap<>();
        Path path = Paths.get("/Users/fogetti/Work/backup/phish-result-2016-06-22/phishing-result.csv");
        CSVParser parser = CSVFormat
                .DEFAULT
                .withSkipHeaderRecord()
                .withQuoteMode(QuoteMode.ALL)
                .withQuote(null)
                .parse(
                        Files
                        .newBufferedReader(path, StandardCharsets.UTF_8));
        for (CSVRecord line : parser) {
            //String ranking = line.get(11);
            String URL = line.get(12);
            String strippedURL = StringUtils.removeEnd(StringUtils.removeStart(URL, "\""), "\"");
            //System.out.println(String.format("Adding ranking %s for URL %s to the unique set", ranking, strippedURL));
            unique.put(strippedURL, line);
        }
        System.out.println(String.format("Found %d unique URLs", unique.size()));
        List<String> lines = new ArrayList<>();
        for (CSVRecord line : unique.values()) {
            StringBuilder builder = new StringBuilder();
            String delim = "";
            for (String field : line) {
                builder.append(delim).append(field);
                delim = ",";
            }
            //System.out.println(builder.toString());
            lines.add(builder.toString());
        }
        Path result = Paths.get("/Users/fogetti/Work/backup/phish-result-2016-06-22/no-duplicate-phishing-result.csv");
        Files.write(result, lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
    
}