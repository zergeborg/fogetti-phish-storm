package fogetti.phish.storm.relatedness;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AckResult implements Serializable {

	private static final long serialVersionUID = 3990008783759688342L;
    private static final Logger logger = LoggerFactory.getLogger(AckResult.class);
    private final ObjectMapper mapper = new ObjectMapper();
	public boolean allsent;
	public String MLD;
	public String MLDPS;
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
	
	public String pop() {
		if (!RDstack.isEmpty()) {
			String pop = RDstack.pop();
			addRD(pop);
			return pop;
		}
		if (!REMstack.isEmpty()) {
			String pop = REMstack.pop();
			addREM(pop);
			return pop;
		}
		return null;
	}

	public boolean addAllREM(Collection<String> s) {
		return REMurl.addAll(s);
	}
	
	private boolean RDempty() {
		return RDstack.empty();
	}
	
	private boolean REMempty() {
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

	public boolean finished() {
		return RDempty() && REMempty() && allsent;
	}
	
	public void setAllsent(boolean allsent) {
	    logger.info("All segments sent in {}", this);
		this.allsent = allsent;
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

	public Map<String, Collection<String>> getMLDTerms(Map<String, Collection<String>> termindex) {
		Map<String, Collection<String>> RDTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> MLD.equals(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return RDTermindex;
	}

	public Map<String, Collection<String>> getMLDPSTerms(Map<String, Collection<String>> termindex) {
		Map<String, Collection<String>> RDTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> MLDPS.equals(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return RDTermindex;
	}
	
	@Override
    public String toString() {
	    String msg = "";
        try {
            msg = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            msg = pw.toString();
        }
	    return msg;
	}
}