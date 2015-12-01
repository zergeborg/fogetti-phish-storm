package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import fogetti.phish.storm.db.Persistency;
import fogetti.phish.storm.db.RedisPersistency;
import fogetti.phish.storm.relatedness.suffix.PublicSuffixMatcher;

/**
 * Implementation of the following real time phishing classifier:
 * <a href="https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf">
 * https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf
 * </a>
 * @author gergely.nagy
 *
 */
public class URLSpout extends BaseRichSpout {

	private static final long serialVersionUID = -6424905468176142975L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);

	private static class SplitResult {
		String first;
		String result;
	}

	private double N = 1024908267229.;
	private double itervalues = 0.;
	private SpoutOutputCollector collector;
	private List<String> urllist;
	private Iterator<String> iterator;
	private PublicSuffixMatcher matcher;
	private Map<String, List<String>> memomap = new HashMap<>();
	private Map<String, Long> lookup = new HashMap<>();
	private Map<String, AckResult> ackIndex = new HashMap<>();
	private String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
	private String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
	private transient Persistency db;

	public URLSpout() {
	}

	public URLSpout(Persistency db) {
		this.db = db;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.db = db();
		this.urllist = readURLListFromFile();
		this.matcher = readPublicSuffixListFromFile();
		this.lookup = readCountFromFile();
		this.iterator = urllist.iterator();
	}

	private Persistency db() {
		RedisPersistency db = new RedisPersistency(6379, 2000);
		new Thread(db, "publisherThread").start();
		return db;
	}

	private List<String> readURLListFromFile() {
		return Arrays.asList("http://sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run","https://www.smbctb.co.jp/JPGCB/JPS/portal/SignonLocaleSwitch.do?locale=en_JP");
	}

	private PublicSuffixMatcher readPublicSuffixListFromFile() {
		String location = System.getProperty("ps-location", psDataFile);
		PublicSuffixMatcher matcher = new PublicSuffixMatcher(location);
		logger.info("Reading public suffix data from [{}] ...", location);
		matcher.load();
		return matcher;
	}

	private Map<String, Long> readCountFromFile() {
		HashMap<String, Long> map = new HashMap<>();
		String location = System.getProperty("count-location", countDataFile);
		logger.info("Reading n-gram count data from [{}] ...", location);
		load(map, location);
		return map;
	}

	private void load(HashMap<String, Long> map, String location) {
		try(Scanner scanner = new Scanner(new FileReader(location));) {
			scan(map, scanner);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void scan(HashMap<String, Long> map, Scanner scanner) {
		while (scanner.hasNextLine()) {
			String[] columns = scanner.nextLine().split("\\t");
			Long value = Long.valueOf(columns[1]);
			itervalues += value;
			map.put(columns[0], value);
		}
	}

	@Override
	public void nextTuple() {
		if (iterator.hasNext()) {
			String URLWithScheme = iterator.next();
			logger.debug("Calculating relatedness for [{}]", URLWithScheme);
			String URL = URLWithScheme.split("//")[1];
			AckResult ackRes = new AckResult();
			ackRes.URL = URL;
			ackIndex.put(URL, ackRes);
			calculateRDurl(URL, ackRes);
			calculateREMurl(URL, ackRes);
		}
	}

	void calculateRDurl(String URL, AckResult ackRes) {
		String mld = URL.split("/")[0];
		String ps = matcher.findPublicSuffix(mld);
		logger.trace("URL [{}] has the following public suffix [{}]", URL, ps);
		ackRes.pushRD(mld);
		logger.trace("Emitting [{}]", mld);
		collector.emit(new Values(mld, URL), mld+"~"+URL);
		String mldNoPs = StringUtils.substringBeforeLast(mld, "." + ps);
		ackRes.pushRD(mldNoPs);
		logger.trace("Emitting [{}]", mldNoPs);
		collector.emit(new Values(mldNoPs, URL), mldNoPs+"~"+URL);
	}

	void calculateREMurl(String URL, AckResult ackRes) {
		String rem = StringUtils.substringAfter(URL, "/");
		slashes(rem, URL, ackRes);
	}

	private void slashes(String rem, String URL, AckResult ackRes) {
		String[] slash = rem.split("/");
		for (String sl : slash) {
			questions(sl, URL, ackRes);
		}
	}

	private void questions(String sl, String URL, AckResult ackRes) {
		String[] questions = sl.split("\\?");
		for (String qu : questions) {
			dots(qu, URL, ackRes);
		}
	}

	private void dots(String qu, String URL, AckResult ackRes) {
		String[] dots = qu.split("\\.");
		for (String dot : dots) {
			equals(dot, URL, ackRes);
		}
	}

	private void equals(String dot, String URL, AckResult ackRes) {
		String[] equals = dot.split("=");
		for (String eq : equals) {
			underscores(eq, URL, ackRes);
		}
	}

	private void underscores(String eq, String URL, AckResult ackRes) {
		String[] underscores = eq.split("_");
		for (String un : underscores) {
			dashes(un, URL, ackRes);
		}
	}

	private void dashes(String un, String URL, AckResult ackRes) {
		String[] dashes = un.split("-");
		for (String dash : dashes) {
			List<String> segments = segment(dash);
			ackRes.pushAllREM(segments);
			segments(segments, URL);
		}
	}

	private void segments(List<String> segments, String URL) {
		for (String segment : segments) {
			logger.trace("Emitting [{}]", segment);
			collector.emit(new Values(segment, URL), segment+"~"+URL);
		}
	}

	public List<String> segment(String text) {
		if (StringUtils.isEmpty(text))
			return Collections.emptyList();
		if (memomap.containsKey(text)) {
			return memomap.get(text);
		} else {
			List<String> result = findResult(text);
			memomap.put(text, result);
			return result;
		}
	}

	private List<String> findResult(String text) {
		List<SplitResult> splitRes = splits(text);
		List<List<String>> candidates = candidates(splitRes);
		List<String> result =
				candidates.stream().max(
						(List<String> o1, List<String> o2) -> pwords(o1).compareTo(pwords(o2))).get();
		return result;
	}

	private List<SplitResult> splits(String text) {
		List<SplitResult> res = new ArrayList<>();
		int min = Math.min(text.length(), 20);
		for (int i = 0; i < min; i++) {
			SplitResult split = new SplitResult();
			split.first = StringUtils.substring(text, 0, i+1);
			split.result = StringUtils.substring(text, i+1);
			res.add(split);
		}
		return res;
	}

	private List<List<String>> candidates(List<SplitResult> splitRes) {
		List<List<String>> candidates = new ArrayList<>();
		for (SplitResult splitResult : splitRes) {
			List<String> list = new ArrayList<>();
			list.add(splitResult.first);
			list.addAll(segment(splitResult.result));
			candidates.add(list);
		}
		return candidates;
	}

	private Double pwords(List<String> words) {
		return words.stream().map((w) -> pw(w)).reduce(1.0, (a,b) -> a * b);
	}

	private double pw(String w) {
		if (lookup.containsKey(w)) {
			return lookup.get(w) / itervalues;
		}
		return 10./(N * Math.pow(10, w.length()));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "url"));
	}
	
    @Override
    public void ack(Object msgId) {
		String m = (String)msgId;
		String[] split = m.split("~");
		String suffix = split[1];
		ack(suffix);
    }

    public void ack(String suffix) {
    	try {
        	AckResult result = ackIndex.get(suffix);
        	if (result == null) {
        		return;
        	}
    		if (!result.RDempty()) {
    			String rd = result.popRD();
    			result.addRD(rd);
    		}
    		if (!result.REMempty()) {
    			String rem = result.popREM();
    			result.addREM(rem);
    		}
    		if (result.RDempty() && result.REMempty()) {
    			ObjectMapper mapper = new ObjectMapper();
    			String msg = mapper.writeValueAsString(result);
    			db.publish("phish", msg);
    			ackIndex.remove(suffix);
    		}
		} catch (JsonProcessingException e) {
			logger.error("Could not send acknowledgment to the intersection bolt", e);
		}
    }
    
    @Override
    public void fail(Object msgId) {
    	String msg = (String)msgId;
    	logger.debug("Message [{}] failed", msg);
    }
}
