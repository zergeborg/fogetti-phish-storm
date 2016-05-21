package fogetti.phish.storm.relatedness.intersection;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.relatedness.AckResult;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class URLSegments implements Serializable {

    private static final long serialVersionUID = 6774105562350012171L;
    private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private static final Map<String, Terms> termindex = new ConcurrentHashMap<>();

	public void put(String segment, Terms terms) {
		if (termindex.containsKey(segment)) {
			termindex.get(segment).add(terms.terms);
		} else {
			termindex.put(segment, terms);
		}
	}

	public void removeIf(Predicate<? super Map.Entry<String, Terms>> filter) {
		termindex.entrySet().removeIf(filter);
	}
	
	public Map<String, Terms> getREMTerms(AckResult result) {
		Map<String, Terms> remTerms = result.getREMTerms(termindex);
		logger.info("REMTerms[{}]", remTerms.values());
		return remTerms;
	}

	public Map<String, Terms> getRDTerms(AckResult result) {
		Map<String, Terms> rdTerms = result.getRDTerms(termindex);
		logger.info("RDTerms[{}]", rdTerms.values());
		return rdTerms;
	}

	public Map<String, Terms> getMLDTerms(AckResult result) {
		Map<String, Terms> mldTerms = result.getMLDTerms(termindex);
		logger.info("MLDTerms[{}]", mldTerms.values());
		return mldTerms;
	}

	public Map<String, Terms> getMLDPSTerms(AckResult result) {
		Map<String, Terms> mldpsTerms = result.getMLDPSTerms(termindex);
		logger.info("MLDPSTerms[{}]", mldpsTerms.values());
		return mldpsTerms;
	}
}