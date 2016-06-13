package fogetti.phish.storm.relatedness.suffix;

import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;

public class Rule {
	public boolean exception = false;
	private List<String> labels = new CopyOnWriteArrayList<>();

	public String findMatch(Domain domain) {
		int ruleSize = labels.size();
		int domainSize = domain.labels.size();
		if (domainSize < ruleSize) return "";
		Stack<String> ps = new Stack<>();
		identical(ps, labels.listIterator(ruleSize), domain.labels.listIterator(domainSize));
		return result(ps);
	}

    public boolean isMatch(Domain domain) {
        int ruleSize = labels.size();
        int domainSize = domain.labels.size();
        if (domainSize < ruleSize) return false;
        Stack<String> ps = new Stack<>();
        return identical(ps, labels.listIterator(ruleSize), domain.labels.listIterator(domainSize));
    }

	public boolean identical(Stack<String> ps, ListIterator<String> rulesIterator, ListIterator<String> domainsIterator) {
		boolean identical = false;
		do {
			identical = checkIdentical(ps, rulesIterator.previous(), domainsIterator.previous());
		} while (
			previousValid(identical, rulesIterator, domainsIterator)
		);
		return identical;
	}

	private boolean checkIdentical(Stack<String> ps, String prevRuleLabel,
			String prevDomainLabel) {
        boolean identical = false;
		if (prevRuleLabel.equals(prevDomainLabel))
			identical = true;
		else if (prevRuleLabel.equals("*"))
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

	public void addAllLabels(List<String> labels) {
		this.labels.addAll(labels);
	}

	public int labelSize() {
		return labels.size();
	}

	public void addLabel(String label) {
		labels.add(label);
	}

	public void removeLabel(int index) {
		labels.remove(index);
	}
}