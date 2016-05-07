package fogetti.phish.storm.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class CSVReader {

	private static final String SEPARATOR = ",";

	private final IPAddressValidator validator = new IPAddressValidator();
	private final Path source;
	private final Path target;

	public CSVReader(Path source, Path target) {
		this.source = source;
		this.target = target;
	}

	public void writeUrls() {
		try (BufferedReader reader = Files.newBufferedReader(source)) {
			try (BufferedWriter writer = Files.newBufferedWriter(target)) {
				List<String> lines = reader.lines().collect(Collectors.toList());
				lines.stream()
					.skip(1)
					.map(line -> Arrays.asList(line.split(SEPARATOR)))	
					.map(list -> {String url=list.get(1).trim(); return url;})
					.forEach(urlWithScheme -> {
						try {
							String url = urlWithScheme.split("//")[1];
							String[] split = StringUtils.split(url, "/");
							if (split.length > 1) {
								String mldps = split[0];
								if (!validator.matches(mldps)) {
									writer.write(urlWithScheme+"\n");
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

    public void writeRandomUrls(int seed) {
        Random rnd = new Random();
        try (BufferedReader reader = Files.newBufferedReader(source)) {
            try (BufferedWriter writer = Files.newBufferedWriter(target)) {
                List<String> result = new ArrayList<>();
                List<String> lines = new ArrayList<>(reader.lines().collect(Collectors.toList()));
                while(result.size() < seed){
                    int randPos = rnd.nextInt(lines.size());
                    result.add(lines.get(randPos));
                }
                for (String res : result) {
                    writer.write(res+"\n");
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }  
}