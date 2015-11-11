package fogetti.phish.storm.relatedness.suffix;

import java.util.List;

public class Find {
	public List<Rule> matches;
	public Rule exception;
	public Rule max;

	public Find(List<Rule> matches, Rule exception, Rule max) {
		this.matches = matches;
		this.exception = exception;
		this.max = max;
	}
}