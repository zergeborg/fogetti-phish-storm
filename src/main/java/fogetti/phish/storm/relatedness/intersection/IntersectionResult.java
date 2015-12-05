package fogetti.phish.storm.relatedness.intersection;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;

public class IntersectionResult {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionResult.class);
	/** 25∗120∗4 = 25 md segments * 120 terms from search engine * 4 words in terms */
	private static final int EXPECTED_ELEMENTS = 12000;
	
	private Map<String, Collection<String>> RDTermindex;
	private Map<String, Collection<String>> REMTermindex;
	private Map<String, Collection<String>> MLDTermindex;
	private Map<String, Collection<String>> MLDPSTermindex;
	private BloomFilter<String> ASrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private BloomFilter<String> RELrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private BloomFilter<String> ASrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private BloomFilter<String> RELrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();

	public IntersectionResult(
		Map<String, Collection<String>> RDTermindex,
		Map<String, Collection<String>> REMTermindex,
		Map<String, Collection<String>> MLDTermindex,
		Map<String, Collection<String>> MLDPSTermindex) {
		this.RDTermindex = RDTermindex;
		this.REMTermindex = REMTermindex;
		this.MLDTermindex = MLDTermindex;
		this.MLDPSTermindex = MLDPSTermindex;
	}

	public void init() {
		// Step#1: calculate ASrem
		initASrem();
		// Step#2: calculate RELrem
		initRELrem();
		// Step#3: calculate ASrd
		initASrd();
		// Step#4: calculate RELrd
		initRELrd();
	}

	private void initASrem() {
		REMTermindex.entrySet().stream().forEach(v -> {
			v.getValue()
				.stream()
				.filter(p -> p!=null && p.contains(v.getKey()))
				.forEach(t -> {
					String[] split = t.split("\\s+");
					for (String spl : split) {
						if (!spl.trim().contains(v.getKey())) ASrem.add(spl);
					}
			});
		});
		logger.info("[ASrem={}]", ASrem);
	}

	private void initRELrem() {
		REMTermindex.values().stream().forEach(v -> {
			v.forEach(t -> {
				String[] split = t.split("\\s+");
				for (String spl : split) {
					RELrem.add(spl.trim());
				}
			});
		});
		logger.info("[RELrem={}]", RELrem);
	}

	private void initASrd() {
		RDTermindex.entrySet().stream().forEach(v -> {
			v.getValue()
				.stream()
				.filter(p -> p!=null && p.contains(v.getKey()))
				.forEach(t -> {
					String[] split = t.split("\\s+");
					for (String spl : split) {
						if (!spl.trim().contains(v.getKey())) ASrd.add(spl);
					}
			});
		});
		logger.info("[ASrd={}]", ASrd);
	}

	private void initRELrd() {
		RDTermindex.values().stream().forEach(v -> {
			v.forEach(t -> {
				String[] split = t.split("\\s+");
				for (String spl : split) {
					RELrd.add(spl.trim());
				}
			});
		});
		logger.info("[RELrd={}]", RELrd);
	}

	public Double JRR() {
		return jaccardIndex(RELrd, RELrem, RELrd, RELrem);
	}

	public Double JRA() {
		return jaccardIndex(RELrd, ASrem, RELrd, ASrem);
	}

	public Double JAA() {
		return jaccardIndex(ASrd, ASrem, ASrd, ASrem);
	}
	
	public Double JAR() {
		return jaccardIndex(ASrd, RELrem, ASrd, RELrem);
	}
	
	public Double JARRD() {
		return jaccardIndex(ASrd, RELrd, ASrd, RELrd);
	}
	
	public Double JARREM() {
		return jaccardIndex(ASrem, RELrem, ASrem, RELrem);
	}
	
	public Integer CARDREM() {
		return REMTermindex.keySet().size();
	}
	
	public Double RATIOAREM() {
		return ASrem.getEstimatedPopulation() / REMTermindex.keySet().size();
	}
	
	public Double RATIORREM() {
		return RELrem.getEstimatedPopulation() / REMTermindex.keySet().size();
	}

	Double RATIOARD() {
		return ASrd.getEstimatedPopulation() / RDTermindex.keySet().size();
	}
	
	Double RATIORRD() {
		return RELrd.getEstimatedPopulation() / RDTermindex.keySet().size();
	}

	public Integer MLDRES() {
		return MLDTermindex.size() == 0 ? 0 : 1;
	}
	
	public Integer MLDPSRES() {
		return MLDPSTermindex.size() == 0 ? 0 : 1;
	}
	
	public Integer RANKING() {
		// FIXME: we should call the Alexa ranking service
		return -1;
	}

	private Double jaccardIndex(BloomFilter<String> first,
		BloomFilter<String> second,
		BloomFilter<String> third,
		BloomFilter<String> fourth) {
		BloomFilter<String> firstclone = first.clone();
		BloomFilter<String> secondclone = second.clone();
		BloomFilter<String> thirdclone = third.clone();
		BloomFilter<String> fourthclone = fourth.clone();
		if (firstclone.intersect(secondclone) && thirdclone.intersect(fourthclone)) {
			Double firstPopulation = firstclone.getEstimatedPopulation().equals(-0.0) ? 0.0 : firstclone.getEstimatedPopulation();
			Double thirdPopulation = thirdclone.getEstimatedPopulation().equals(-0.0) ? 1.0 : thirdclone.getEstimatedPopulation();
			return firstPopulation / thirdPopulation;
		}
		return null;
	}

}
