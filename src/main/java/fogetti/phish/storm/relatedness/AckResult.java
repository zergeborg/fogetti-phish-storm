package fogetti.phish.storm.relatedness;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Terms;

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
	
	public boolean addRD(String s) {
		return RDurl.add(s);
	}

	public boolean addREM(String s) {
		return REMurl.add(s);
	}
	
	public boolean addAllREM(Collection<String> s) {
		return REMurl.addAll(s);
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
		return allsent;
	}
	
	public void setAllsent(boolean allsent) {
	    logger.info("All segments sent in {}", this);
		this.allsent = allsent;
	}

	public Set<String> getRDurl() {
        return RDurl;
    }

    public Set<String> getREMurl() {
        return REMurl;
    }

    public Map<String, Terms> getREMTerms(Map<String, Terms> termindex) {
		Map<String, Terms> REMTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> REMurl.contains(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return REMTermindex;
	}

	public Map<String, Terms> getRDTerms(Map<String, Terms> termindex) {
		Map<String, Terms> RDTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> RDurl.contains(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return RDTermindex;
	}

	public Map<String, Terms> getMLDTerms(Map<String, Terms> termindex) {
		Map<String, Terms> RDTermindex = 
			termindex
			.entrySet()
			.stream()
			.filter(termEntry -> MLD.equals(termEntry.getKey()))
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		return RDTermindex;
	}

	public Map<String, Terms> getMLDPSTerms(Map<String, Terms> termindex) {
		Map<String, Terms> RDTermindex = 
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