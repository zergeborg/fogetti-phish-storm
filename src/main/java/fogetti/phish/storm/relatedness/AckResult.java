package fogetti.phish.storm.relatedness;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AckResult implements Serializable {

	private static final long serialVersionUID = 3990008783759688342L;
	public Set<String> RDurl = new HashSet<>();
	public Set<String> REMurl = new HashSet<>();

}