package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fogetti.phish.storm.client.IRequest;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;

public class IntersectionResult {

	private static final Logger logger = LoggerFactory.getLogger(IntersectionResult.class);
	/** 25∗20∗3 = 25 md segments * 20 terms from search engine * 3 words in terms */
	private static final int EXPECTED_ELEMENTS = 1500;
	
	private final Map<String, Collection<String>> RDTermindex;
	private final Map<String, Collection<String>> REMTermindex;
	private final Map<String, Collection<String>> MLDTermindex;
	private final Map<String, Collection<String>> MLDPSTermindex;
	private final BloomFilter<String> ASrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> RELrem = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> ASrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
	private final BloomFilter<String> RELrd = new FilterBuilder(EXPECTED_ELEMENTS, 0.0001).<String>buildBloomFilter();
    private final IRequest alexa;
    private final String resultUrl;
    private final OkHttpClient client;
    private Integer RANKING;

	public IntersectionResult(
		Map<String, Collection<String>> RDTermindex,
		Map<String, Collection<String>> REMTermindex,
		Map<String, Collection<String>> MLDTermindex,
		Map<String, Collection<String>> MLDPSTermindex,
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
				.stream()
				.filter(p -> p!=null && p.contains(v.getKey()))
				.forEach(t -> {
					String[] split = t.split("\\s+");
					for (String spl : split) {
						if (!spl.trim().contains(v.getKey())) ASrem.add(spl);
					}
			});
		});
		logger.debug("[ASrem={}]", ASrem);
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
		logger.debug("[RELrem={}]", RELrem);
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
		logger.debug("[ASrd={}]", ASrd);
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
		if (firstclone.intersect(secondclone) && thirdclone.intersect(fourthclone)) {
			Double firstPopulation = firstclone.getEstimatedPopulation();
			Double thirdPopulation = thirdclone.getEstimatedPopulation();
			return firstPopulation / thirdPopulation;
		}
		return null;
	}

}
