package fogetti.phish.storm.relatedness;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class AckResult implements Serializable {

	private static final long serialVersionUID = 3990008783759688342L;
	public String URL;
	public Set<String> RDurl = new HashSet<>();
	public Set<String> REMurl = new HashSet<>();
	public Stack<String> RDstack = new Stack<>();
	public Stack<String> REMstack = new Stack<>();
	
	public boolean addRD(String s) {
		return RDurl.add(s);
	}

	public boolean addREM(String s) {
		return REMurl.add(s);
	}
	
	public String pushRD(String s) {
		return RDstack.push(s);
	}

	public String pushREM(String s) {
		return REMstack.push(s);
	}
	
	public void pushAllREM(Collection<String> c) {
		for (String s : c) {
			REMstack.push(s);
		}
	}
	
	public String popRD() {
		return RDstack.pop();
	}

	public String popREM() {
		return REMstack.pop();
	}
	
	public boolean addAllREM(Collection<String> s) {
		return REMurl.addAll(s);
	}
	
	public boolean RDempty() {
		return RDstack.empty();
	}
	
	public boolean REMempty() {
		return REMstack.empty();
	}
	
	public void clear() {
		RDurl.clear();
		REMurl.clear();
	}
	
	public int RDsize() {
		return RDurl.size();
	}

	public int REMsize() {
		return REMurl.size();
	}

	public boolean RDcontains(String s) {
		return RDurl.contains(s);
	}
	
	public boolean REMcontains(String s) {
		return REMurl.contains(s);		
	}

	public Map<String, Collection<String>> getREMTerms(Map<String, Collection<String>> termindex) {
		Map<String, Collection<String>> REMTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> REMurl.contains(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return REMTermindex;
	}

	public Map<String, Collection<String>> getRDTerms(Map<String, Collection<String>> termindex) {
		Map<String, Collection<String>> RDTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> RDurl.contains(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return RDTermindex;
	}
}