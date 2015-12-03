package fogetti.phish.storm.relatedness.intersection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.relatedness.AckResult;

public class URLSegments {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionBolt.class);
	private static final Map<String, Collection<String>> termindex = new ConcurrentHashMap<>();

	public void put(String segment, Set<String> termset) {
		if (termindex.containsKey(segment)) {
			termindex.get(segment).addAll(termset);
		} else {
			termindex.put(segment, termset);
		}
	}

	public void removeIf(Predicate<? super Map.Entry<String, Collection<String>>> filter) {
		termindex.entrySet().removeIf(filter);
	}
	
	public Map<String, Collection<String>> getREMTerms(AckResult result) {
		Map<String, Collection<String>> remTerms = result.getREMTerms(termindex);
		logger.info("REMTerms[{}]", remTerms.values());
		return remTerms;
	}

	public Map<String, Collection<String>> getRDTerms(AckResult result) {
		Map<String, Collection<String>> rdTerms = result.getRDTerms(termindex);
		logger.info("RDTerms[{}]", rdTerms.values());
		return rdTerms;
	}

	public Map<String, Collection<String>> getMLDTerms(AckResult result) {
		Map<String, Collection<String>> mldTerms = result.getMLDTerms(termindex);
		logger.info("MLDTerms[{}]", mldTerms.values());
		return mldTerms;
	}

	public Map<String, Collection<String>> getMLDPSTerms(AckResult result) {
		Map<String, Collection<String>> mldpsTerms = result.getMLDPSTerms(termindex);
		logger.info("MLDPSTerms[{}]", mldpsTerms.values());
		return mldpsTerms;
	}
}