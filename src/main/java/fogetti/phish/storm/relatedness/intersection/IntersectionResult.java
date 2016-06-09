package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.client.IRequest;
import fogetti.phish.storm.client.Term;
import fogetti.phish.storm.client.Terms;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;

public class IntersectionResult {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionResult.class);
	/** 25∗20∗3 = 25 md segments * 20 terms from search engine * 3 words in terms */
	private static final int EXPECTED_ELEMENTS = 1500;
	
	private final Map<String, Terms> RDTermindex;
	private final Map<String, Terms> REMTermindex;
	private final Map<String, Terms> MLDTermindex;
	private final Map<String, Terms> MLDPSTermindex;
	private final BloomFilter<String> ASrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> RELrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> ASrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> RELrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
    private final IRequest alexa;
    private final String resultUrl;
    private final OkHttpClient client;
    private Integer RANKING;

	public IntersectionResult(
		Map<String, Terms> RDTermindex,
		Map<String, Terms> REMTermindex,
		Map<String, Terms> MLDTermindex,
		Map<String, Terms> MLDPSTermindex,
		IRequest alexa,
		String resultUrl,
		OkHttpClient client) {
		this.RDTermindex = RDTermindex;
		this.REMTermindex = REMTermindex;
		this.MLDTermindex = MLDTermindex;
		this.MLDPSTermindex = MLDPSTermindex;
        this.alexa = alexa;
        this.resultUrl = resultUrl;
        this.client = client;
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
        // Step#5: calculate RANKING
		initRanking();
	}

    private void initASrem() {
		REMTermindex.entrySet().stream().forEach(v -> {
			v.getValue()
			    .terms
				.stream()
				.forEach(t -> {
				    if (t.contains(v.getKey())) {
    				    List<String> asrem =
    				            t
    				            .words
    				            .stream()
    				            .filter(p ->  !v.getKey().equals(p))
    				            .collect(Collectors.toList());
    					ASrem.addAll(asrem);
				    }
			});
		});
		logger.debug("[ASrem={}]", ASrem);
	}

	private void initRELrem() {
		REMTermindex.values().stream().forEach(v -> {
			v.terms.forEach(t -> {
				RELrem.addAll(t.words);
			});
		});
		logger.debug("[RELrem={}]", RELrem);
	}

	private void initASrd() {
		RDTermindex.entrySet().stream().forEach(v -> {
			v.getValue()
			    .terms
				.stream()
                .forEach(t -> {
                    if (t.contains(v.getKey())) {
                        List<String> asrem =
                                t
                                .words
                                .stream()
                                .filter(p ->  !v.getKey().equals(p))
                                .collect(Collectors.toList());
                        ASrd.addAll(asrem);
                    }
			});
		});
		logger.debug("[ASrd={}]", ASrd);
	}

	private void initRELrd() {
		RDTermindex.values().stream().forEach(v -> {
			v.terms.forEach(t -> {
				RELrd.addAll(t.words);
			});
		});
		logger.debug("[RELrd={}]", RELrd);
	}

    private void initRanking() {
        try {
            Response response = client.newCall(alexa.Get("http://data.alexa.com/data?cli=10&url="+resultUrl)).execute();
            String xml = response.body().string();
            Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
            for (Element e : doc.select("POPULARITY")) {
                String text = e.attr("TEXT");
                RANKING = Integer.valueOf(text);
            }
        } catch (IOException e) {
            logger.error("Alexa ranking lookup failed", e);
        }
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
	
    public Integer CARDRD() {
        return RDTermindex.keySet().size();
    }
    
	public Double RATIOAREM() {
		Integer cardrem = CARDREM();
		if (cardrem == 0) {
		    return 0.0D;
		}
        return ASrem.getEstimatedPopulation() / cardrem;
	}

	public Double RATIORREM() {
        Integer cardrem = CARDREM();
        if (cardrem == 0) {
            return 0.0D;
        }
		return RELrem.getEstimatedPopulation() / cardrem;
	}

	Double RATIOARD() {
        Integer cardrd = CARDRD();
        if (cardrd == 0) {
            return 0.0D;
        }
		return ASrd.getEstimatedPopulation() / cardrd;
	}
	
	Double RATIORRD() {
        Integer cardrd = CARDRD();
        if (cardrd == 0) {
            return 0.0D;
        }
		return RELrd.getEstimatedPopulation() / cardrd;
	}

	public Integer MLDRES() {
	    int size = 0;
	    for (Terms t : MLDTermindex.values()) {
	        for (Term term : t.terms) {
                if (!term.words.isEmpty()) size++;
            }
        }
		return size == 0 ? 0 : 1;
	}

	public Integer MLDPSRES() {
        int size = 0;
        for (Terms t : MLDPSTermindex.values()) {
            for (Term term : t.terms) {
                if (!term.words.isEmpty()) size++;
            }
        }
        return size == 0 ? 0 : 1;
	}
	
	public Integer RANKING() {
		return RANKING;
	}

	private Double jaccardIndex(BloomFilter<String> first,
		BloomFilter<String> second,
		BloomFilter<String> third,
		BloomFilter<String> fourth) {
		BloomFilter<String> firstclone = first.clone();
		BloomFilter<String> secondclone = second.clone();
		BloomFilter<String> thirdclone = third.clone();
		BloomFilter<String> fourthclone = fourth.clone();
		if (firstclone.intersect(secondclone) && thirdclone.union(fourthclone)) {
			long firstPopulation = Math.round(firstclone.getEstimatedPopulation());
			long thirdPopulation = Math.round(thirdclone.getEstimatedPopulation());
			if (firstPopulation == 0 && thirdPopulation == 0) {
			    return 1.0D;
			}
			if (thirdPopulation == 0) {
			    return 0.0D;
			}
			return (double) firstPopulation / thirdPopulation;
		}
		return null;
	}

}
