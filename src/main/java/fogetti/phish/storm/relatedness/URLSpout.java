package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
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
import fogetti.phish.storm.db.PublishMessage;
import fogetti.phish.storm.relatedness.suffix.PublicSuffixMatcher;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

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
	private ListIterator<String> iterator;
	private Map<String, List<String>> memomap = new HashMap<>();
	private Map<String, Long> lookup = new HashMap<>();
	private Map<String, AckResult> ackIndex;
	private String countDataFile;
	private String psDataFile;
	private String urlDataFile;
	private BigInteger counter = BigInteger.valueOf(0);
	private JedisPoolConfig config;
	private JedisCommandsInstanceContainer container;

	public URLSpout(String countDataFile, String psDataFile, String urlDataFile, Map<String, AckResult> ackIndex, JedisPoolConfig config) {
		this.countDataFile = countDataFile;
		this.psDataFile = psDataFile;
		this.urlDataFile = urlDataFile;
		this.ackIndex = ackIndex;
		this.config = config;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.urllist = readURLListFromFile();
		this.lookup = readCountFromFile();
		this.iterator = urllist.listIterator();
		this.container = JedisCommandsContainerBuilder.build(config);
	}

	private List<String> readURLListFromFile() {
		List<String> urls = new ArrayList<>();
		loadUrls(urls);
		return urls;
	}

	private void loadUrls(List<String> urls) {
		try(Scanner scanner = new Scanner(new FileReader(urlDataFile));) {
			while (scanner.hasNextLine()) {
				urls.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
		// Check the case when we read first seen elements
		if (iterator.hasNext()) {
			String URLWithScheme = iterator.next();
			doNextTuple(URLWithScheme);
		}
		// Check the case when we read failed elements
		else if (iterator.hasPrevious()) {
			String URLWithScheme = iterator.previous();
			doNextTuple(URLWithScheme);
		}
		counter = counter.add(BigInteger.valueOf(1));
	}

	private void doNextTuple(String URLWithScheme) {
		logger.debug("Calculating relatedness for [{}]", URLWithScheme);
		String URL = URLWithScheme.split("//")[1];
		AckResult ackRes = new AckResult();
		ackRes.URL = URL;
		ackIndex.put(URL+"~"+counter.toString(), ackRes);
		calculateRDurl(URL, ackRes);
		calculateREMurl(URL, ackRes);
		ackRes.setAllsent(true);
	}

	void calculateRDurl(String URL, AckResult ackRes) {
		String mldps = URL.split("/")[0];
		ackRes.MLDPS = mldps;
		PublicSuffixMatcher matcher = readPublicSuffixListFromFile();
		String ps = matcher.findPublicSuffix(mldps);
		logger.trace("URL [{}] has the following public suffix [{}]", URL, ps);
		ackRes.pushRD(mldps);
		emit(mldps, URL, "mldps"+mldps);
		String mld = StringUtils.substringBeforeLast(mldps, "." + ps);
		ackRes.MLD = mld;
		ackRes.pushRD(mld);
		emit(mld, URL, "mld"+mld);
	}

	private void emit(String word, String URL, String prefix) {
		logger.debug("Emitting [{}]", prefix);
		collector.emit(new Values(word, URL), prefix+"~"+URL+"~"+counter.toString());
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
			segments(segments, URL, ackRes);
		}
	}

	private void segments(List<String> segments, String URL, AckResult ackRes) {
		for (String segment : segments) {
			ackRes.pushREM(segment);
			emit(segment, URL, segment);
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
		String suffix = StringUtils.substringAfter(m, "~");
		ack(suffix);
	}

	public void ack(String suffix) {
		try {
			AckResult result = ackIndex.get(suffix);
			if (result != null) {
				String pop = result.pop();
				logger.info("Acking [pop={}]", pop);
				if (result.finished()) {
					ObjectMapper mapper = new ObjectMapper();
					String msg = mapper.writeValueAsString(result);
					publish("phish", msg);
					ackIndex.remove(suffix);
					return;
				}
			}
		} catch (JsonProcessingException e) {
			logger.error("Could not send acknowledgment to the intersection bolt", e);
		}
	}

	private void publish(String channel, String msg) {
		Jedis jedis = null;
		try {
			jedis = (Jedis) getInstance();
			PublishMessage message = new PublishMessage(channel, msg);
			logger.info("Publishing [Message={}]", message.msg);
			jedis.publish(message.channel, message.msg);
		} finally {
			returnInstance(jedis);
		}
	}

	@Override
	public void fail(Object msgId) {
		String msg = (String)msgId;
		String suffix = StringUtils.substringAfter(msg, "~");
		logger.debug("Message [msg={}] failed", msg);
		String prefix = "http://"+StringUtils.substringBefore(suffix, "~");
		logger.warn("Requeueing [msg={}]", prefix);
		iterator.add(prefix);
	}

	/**
	 * Borrow JedisCommands instance from container.<p></p>
	 * JedisCommands is an interface which contains single key operations.
	 * @return implementation of JedisCommands
	 * @see JedisCommandsInstanceContainer#getInstance()
	 */
	protected JedisCommands getInstance() {
		return this.container.getInstance();
	}

	/**
	 * Return borrowed instance to container.
	 * @param instance borrowed object
	 */
	protected void returnInstance(JedisCommands instance) {
		this.container.returnInstance(instance);
	}
}
