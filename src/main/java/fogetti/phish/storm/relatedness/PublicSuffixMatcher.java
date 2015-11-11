package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.exception.URLMatchingFailedException;

public class PublicSuffixMatcher {

	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	
	private final Set<Rule> rules = new HashSet<>();
	private final String suffixDataLocation;
	private volatile boolean loaded = false;

	public PublicSuffixMatcher(String suffixDataLocation) {
		this.suffixDataLocation = suffixDataLocation;
	}

	public void load() {
		try(Scanner scanner = new Scanner(new FileReader(suffixDataLocation));) {
			scan(scanner);
		} catch (FileNotFoundException e) {
			logger.error("Could not load the public suffix file", e);
			throw new URLMatchingFailedException(e);
		}
		loaded = true;
	}

	void scan(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			Rule rule = new Rule();
			if (StringUtils.isEmpty(StringUtils.trim(nextLine)))
				continue;
			if (StringUtils.startsWith(StringUtils.trim(nextLine), "//"))
				continue;
			if (StringUtils.startsWith(StringUtils.trim(nextLine), "!")) {
				rule.exception = true;
				nextLine = StringUtils.substringAfter(nextLine, "!");
			}
			nextLine = StringUtils.strip(nextLine, ".");
			rule.labels.addAll(Arrays.asList(nextLine.split("\\.")));
			rules.add(rule);
		}
	}

	public String findPublicSuffix(String mld) {
		if (loaded) {
			Domain domain = new Domain();
			domain.labels.addAll(Arrays.asList(mld.split("\\.")));
			Find find = new Find(new ArrayList<>(), null, new Rule());
			findMatches(find, domain);
			Rule prevailing = findPrevailing(find);
			return prevailing.match(domain);
		} else {
			throw new URLMatchingFailedException();
		}
	}

	public void findMatches(Find find, Domain domain) {
		for (Rule rule : rules) {
			if (!StringUtils.isEmpty(rule.match(domain))) {
				find.matches.add(rule);
				find.max = (find.max.labels.size() < rule.labels.size()) ? rule : find.max;
				if (rule.exception)
					find.exception = rule;
			}
		}
	}

	public Rule findPrevailing(Find find) {
		Rule prevailing = null;
		if (find.matches.isEmpty()) {
			Rule asterisk = new Rule();
			asterisk.labels.add("*");
			prevailing = asterisk;
		} else {
			if (find.exception != null) {
				prevailing = find.exception;
				prevailing.labels.remove(0);
			} else {
				prevailing = find.max;
			}
		}
		return prevailing;
	}

	public boolean match(String mld) {
		if (loaded) {
			Domain domain = new Domain();
			domain.labels.addAll(Arrays.asList(mld.split("\\.")));
			for (Rule rule : rules) {
				if (!StringUtils.isEmpty(rule.match(domain)))
					return true;
			}
			return false;
		} else {
			throw new URLMatchingFailedException();
		}
	}

}
