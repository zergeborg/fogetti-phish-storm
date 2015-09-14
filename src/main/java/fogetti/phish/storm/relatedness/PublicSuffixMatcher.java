package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import fogetti.phish.storm.exception.URLMatchingFailedException;

public class PublicSuffixMatcher {

	private static class Rule {
		public boolean exception = false;
		public List<String> labels = new ArrayList<>();

		public String match(Domain domain) {
			if (domain.labels.size() < labels.size())
				return "";
			boolean identical = false;
			Stack<String> ps = new Stack<>();
			ListIterator<String> rulesIterator = labels.listIterator(labels.size());
			ListIterator<String> domainsIterator = domain.labels.listIterator(domain.labels.size());
			do {
				identical = checkIdentical(identical, ps, rulesIterator, domainsIterator);
			} while (previousValid(identical, rulesIterator, domainsIterator));
			return StringUtils.substringBeforeLast(ps.stream().reduce("", (a,b) -> b + "." + a), ".");
		}

		boolean previousValid(boolean identical, ListIterator<String> rulesIterator,
				ListIterator<String> domainsIterator) {
			return identical
					&& rulesIterator.hasPrevious()
					&& domainsIterator.hasPrevious();
		}

		private boolean checkIdentical(boolean identical, Stack<String> ps, ListIterator<String> rulesIterator,
				ListIterator<String> domainsIterator) {
			String prevRuleLabel = rulesIterator.previous();
			String prevDomainLabel = domainsIterator.previous();
			if (prevRuleLabel.equals(prevDomainLabel))
				identical = true;
			if (prevRuleLabel.equals("*"))
				identical = true;
			if (identical)
				ps.push(prevDomainLabel);
			return identical;
		}

		@Override
		public String toString() {
			return "Rule [labels=" + labels + "]";
		}
	}

	private static class Domain {
		public List<String> labels = new ArrayList<>();
	}

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
			e.printStackTrace();
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

	private static class Find {
		public List<Rule> matches;
		public Rule exception;
		public Rule max;

		public Find(List<Rule> matches, Rule exception, Rule max) {
			this.matches = matches;
			this.exception = exception;
			this.max = max;
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

	void findMatches(Find find, Domain domain) {
		for (Rule rule : rules) {
			if (!StringUtils.isEmpty(rule.match(domain))) {
				find.matches.add(rule);
				find.max = (find.max.labels.size() < rule.labels.size()) ? rule : find.max;
				if (rule.exception)
					find.exception = rule;
			}
		}
	}

	Rule findPrevailing(Find find) {
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
