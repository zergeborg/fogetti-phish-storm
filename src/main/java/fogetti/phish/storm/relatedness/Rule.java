package fogetti.phish.storm.relatedness;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

public class Rule {
	public boolean exception = false;
	public List<String> labels = new ArrayList<>();

	public String match(Domain domain) {
		int ruleSize = labels.size();
		int domainSize = domain.labels.size();
		if (domainSize < ruleSize) return "";
		Stack<String> ps = new Stack<>();
		identical(ps, labels.listIterator(ruleSize), domain.labels.listIterator(domainSize));
		return result(ps);
	}

	public void identical(Stack<String> ps, ListIterator<String> rulesIterator, ListIterator<String> domainsIterator) {
		boolean identical = false;
		do {
			identical = checkIdentical(identical, ps, rulesIterator.previous(), domainsIterator.previous());
		} while (
			previousValid(identical, rulesIterator, domainsIterator)
		);
	}

	private boolean checkIdentical(boolean identical, Stack<String> ps, String prevRuleLabel,
			String prevDomainLabel) {
		if (prevRuleLabel.equals(prevDomainLabel))
			identical = true;
		if (prevRuleLabel.equals("*"))
			identical = true;
		if (identical)
			ps.push(prevDomainLabel);
		return identical;
	}
	
	public boolean previousValid(boolean identical, ListIterator<String> rulesIterator,
			ListIterator<String> domainsIterator) {
		return identical
				&& rulesIterator.hasPrevious()
				&& domainsIterator.hasPrevious();
	}

	public String result(Stack<String> ps) {
		return StringUtils.substringBeforeLast(
				ps.stream().reduce("", (a,b) -> b + "." + a), ".");
	}

	@Override
	public String toString() {
		return "Rule [labels=" + labels + "]";
	}
}