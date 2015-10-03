package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

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

	double N = 1024908267229.;
	double _itervalues = 0.;
	SpoutOutputCollector _collector;
	List<String> _urllist;
	Iterator<String> _iterator;
	PublicSuffixMatcher _matcher;
	Map<String, List<String>> _memomap = new HashMap<>();
	Map<String, Long> _lookup = new HashMap<>();
	Set<String> RDurl = new HashSet<>();
	Set<String> REMurl = new HashSet<>();
	String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
	String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		_collector = collector;
		_urllist = readURLListFromFile();
		_matcher = readPublicSuffixListFromFile();
		_lookup = readCountFromFile();
		_iterator = _urllist.iterator();
	}

	private List<String> readURLListFromFile() {
		return Arrays.asList("http://sezopoztos.com/paypalitlogin/us/webscr.html?cmd=login-run");
	}

	private PublicSuffixMatcher readPublicSuffixListFromFile() {
		String location = System.getProperty("ps-location", psDataFile);
		PublicSuffixMatcher matcher = new PublicSuffixMatcher(location);
		logger.info("Reading public suffix data from [{}] ...", location);
		matcher.load();
		return matcher;
	}

	Map<String, Long> readCountFromFile() {
		HashMap<String, Long> map = new HashMap<>();
		String location = System.getProperty("count-location", countDataFile);
		logger.info("Reading n-gram count data from [{}] ...", location);
		try(Scanner scanner = new Scanner(new FileReader(location));) {
			scan(map, scanner);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return map;
	}

	void scan(HashMap<String, Long> map, Scanner scanner) {
		while (scanner.hasNextLine()) {
			String[] columns = scanner.nextLine().split("\\t");
			Long value = Long.valueOf(columns[1]);
			_itervalues += value;
			map.put(columns[0], value);
		}
	}

	@Override
	public void nextTuple() {
		if (_iterator.hasNext()) {
			String URLWithScheme = _iterator.next();
			logger.debug("Calculating relatedness for [{}]", URLWithScheme);
			String URL = URLWithScheme.split("//")[1];
			calculateRDurl(URL);
			calculateREMurl(URL);
		}
	}

	void calculateRDurl(String URL) {
		String mld = URL.split("/")[0];
		String ps = _matcher.findPublicSuffix(mld);
		logger.trace("URL [{}] has the following public suffix [{}]", URL, ps);
		RDurl.add(mld);
		logger.trace("Emitting [{}]", mld);
		_collector.emit(new Values(mld));
		String mldNoPs = StringUtils.substringBeforeLast(mld, "." + ps);
		RDurl.add(mldNoPs);
		logger.trace("Emitting [{}]", mldNoPs);
		_collector.emit(new Values(mldNoPs));
	}

	void calculateREMurl(String URL) {
		String rem = StringUtils.substringAfter(URL, "/");
		String[] slash = rem.split("/");
		for (String sl : slash) {
			String[] questions = sl.split("\\?");
			for (String qu : questions) {
				String[] dots = qu.split("\\.");
				for (String dot : dots) {
					String[] equals = dot.split("=");
					for (String eq : equals) {
						String[] underscores = eq.split("_");
						for (String un : underscores) {
							String[] dashes = un.split("-");
							for (String dash : dashes) {
								List<String> segments = segment(dash);
								REMurl.addAll(segments);
								for (String segment : segments) {
									logger.trace("Emitting [{}]", segment);
									_collector.emit(new Values(segment));
								}
							}
						}
					}
				}
			}
		}
	}

	List<String> segment(String text) {
		if (StringUtils.isEmpty(text))
			return Collections.emptyList();
		if (_memomap.containsKey(text)) {
			return _memomap.get(text);
		} else {
			List<String> result = findResult(text);
			_memomap.put(text, result);
			return result;
		}
	}

	List<String> findResult(String text) {
		List<SplitResult> splitRes = splits(text);
		List<List<String>> candidates = candidates(splitRes);
		List<String> result =
				candidates.stream().max(
						(List<String> o1, List<String> o2) -> pwords(o1).compareTo(pwords(o2))).get();
		return result;
	}

	List<SplitResult> splits(String text) {
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

	List<List<String>> candidates(List<SplitResult> splitRes) {
		List<List<String>> candidates = new ArrayList<>();
		for (SplitResult splitResult : splitRes) {
			List<String> list = new ArrayList<>();
			list.add(splitResult.first);
			list.addAll(segment(splitResult.result));
			candidates.add(list);
		}
		return candidates;
	}

	Double pwords(List<String> words) {
		return words.stream().map((w) -> pw(w)).reduce(1.0, (a,b) -> a * b);
	}

	double pw(String w) {
		if (_lookup.containsKey(w)) {
			return _lookup.get(w) / _itervalues;
		}
		return 10./(N * Math.pow(10, w.length()));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
	
}
