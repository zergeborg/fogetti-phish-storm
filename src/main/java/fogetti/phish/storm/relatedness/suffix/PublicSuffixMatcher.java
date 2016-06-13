package fogetti.phish.storm.relatedness.suffix;

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
import fogetti.phish.storm.relatedness.URLSpout;

/**
 *         <h4>
            Definitions
        </h4>
        <ul>
            <li>
                The Public Suffix List consists of a series of lines, separated by <code>\n</code>.
            </li>
            <li>
                Each line is only read up to the first whitespace; entire lines can also be commented using <code>//</code>.
            </li>
            <li>
                Each line which is not entirely whitespace or begins with a comment contains a rule.
            </li>
            <li>
                A rule may begin with a "!" (exclamation mark). If it does, it is labelled as a "exception rule" and then treated as if the exclamation mark is not present.
            </li>
            <li>
                A domain or rule can be split into a list of labels using the separator "." (dot). The separator is not part of any of the labels. Empty labels are not permitted, meaning that leading and trailing dots are ignored.
            </li>
            <li>
                A domain is said to match a rule if and only if all of the following conditions are met:
                <ul>
                    <li>When the domain and rule are split into corresponding labels, that the domain contains as many or more labels than the rule.</li>
                    <li>Beginning with the right-most labels of both the domain and the rule, and continuing for all labels in the rule, one finds that for every pair, either they are identical, or that the label from the rule is "*".</li>
                </ul>
            </li>
        </ul>
        <h4>
            Algorithm
        </h4>
        <ol>
            <li>
                Match domain against all rules and take note of the matching ones.
            </li>
            <li>
                If no rules match, the prevailing rule is "*".
            </li>
            <li>
                If more than one rule matches, the prevailing rule is the one which is an exception rule.
            </li>
            <li>
                If there is no matching exception rule, the prevailing rule is the one with the most labels.
            </li>
            <li>
                If the prevailing rule is a exception rule, modify it by removing the leftmost label.
            </li>
            <li>
                The public suffix is the set of labels from the domain which match the labels of the prevailing rule, using the matching algorithm above.
            </li>
            <li>
                The registered or registrable domain is the public suffix plus one additional label.
            </li>
        </ol>
 * @author fogetti
 *
 */
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
			rule.addAllLabels(Arrays.asList(nextLine.split("\\.")));
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
			return prevailing.findMatch(domain);
		} else {
			throw new URLMatchingFailedException();
		}
	}

	public void findMatches(Find find, Domain domain) {
		for (Rule rule : rules) {
			if (rule.isMatch(domain)) {
				find.matches.add(rule);
				find.max = (find.max.labelSize() < rule.labelSize()) ? rule : find.max;
				if (rule.exception && find.exception == null)
					find.exception = rule;
			}
		}
	}

	public Rule findPrevailing(Find find) {
		Rule prevailing = null;
		if (find.matches.isEmpty()) {
			Rule asterisk = new Rule();
			asterisk.addLabel("*");
			prevailing = asterisk;
		} else {
			if (find.exception != null) {
				prevailing = find.exception;
				prevailing.removeLabel(0);
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
				if (rule.isMatch(domain))
					return true;
			}
			return false;
		} else {
			throw new URLMatchingFailedException();
		}
	}

}
