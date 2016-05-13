package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.relatedness.suffix.PublicSuffixMatcher;
import redis.clients.jedis.Jedis;

public class MatcherBolt extends AbstractRedisBolt {

    private static final long serialVersionUID = -3604538816426992025L;
    private static final Logger logger = LoggerFactory.getLogger(MatcherBolt.class);
    private OutputCollector collector;
    private double N = 1024908267229.;
    private double itervalues = 0.;
    private Map<String, List<String>> memomap = new HashMap<>();
    private Map<String, Long> lookup = new HashMap<>();
    private String countDataFile;
    private String psDataFile;
    private AckResult ack = new AckResult();
    private ObjectMapper mapper = new ObjectMapper();

    private static class SplitResult {
        String first;
        String result;
    }

    public MatcherBolt(String countDataFile, String psDataFile, JedisPoolConfig poolConfig) {
        super(poolConfig);
        this.countDataFile = countDataFile;
        this.psDataFile = psDataFile;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        super.prepare(stormConf, context, collector);
        this.collector = collector;
        this.lookup = readCountFromFile();
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
    public void execute(Tuple input) {
        String schemedUrl = input.getStringByField("url");
        logger.debug("Calculating relatedness for [{}]", schemedUrl);
        String URL = schemedUrl.split("//")[1];
        ack.URL = URL;
        calculateRDurl(URL);
        calculateREMurl(URL);
        if (saveResult(schemedUrl)) {
            emit(input, URL);
        } else {
            collector.fail(input);
        }
    }

    void calculateRDurl(String URL) {
        String mldps = URL.split("/")[0];
        ack.MLDPS = mldps;
        PublicSuffixMatcher matcher = readPublicSuffixListFromFile();
        String ps = matcher.findPublicSuffix(mldps);
        logger.trace("URL [{}] has the following public suffix [{}]", URL, ps);
        ack.addRD(mldps);
        String mld = StringUtils.substringBeforeLast(mldps, "." + ps);
        ack.MLD = mld;
        ack.addRD(mld);
    }

    private PublicSuffixMatcher readPublicSuffixListFromFile() {
        String location = System.getProperty("ps-location", psDataFile);
        PublicSuffixMatcher matcher = new PublicSuffixMatcher(location);
        logger.info("Reading public suffix data from [{}] ...", location);
        matcher.load();
        return matcher;
    }

    private boolean saveResult(String schemedUrl) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) getInstance();
            String message = jedis.lpop("acked:"+schemedUrl);
            if (message == null) {
                String result = mapper.writeValueAsString(ack);
                logger.info("Saving [AckResult={}]", result);
                jedis.rpush("acked:"+schemedUrl, result);
            }
        } catch (JsonProcessingException e) {
            logger.error("Could not save AckResult", e);
            return false;
        } finally {
            returnInstance(jedis);
        }
        return true;
    }

    private void emit(Tuple input, String URL) {
        for (String word : ack.getRDurl()) {
            collector.emit(input, new Values(word, URL));
        }
        for (String word : ack.getREMurl()) {
            collector.emit(input, new Values(word, URL));
        }
        collector.ack(input);
    }

    void calculateREMurl(String URL) {
        String rem = StringUtils.substringAfter(URL, "/");
        slashes(rem, URL);
    }

    private void slashes(String rem, String URL) {
        String[] slash = rem.split("/");
        for (String sl : slash) {
            questions(sl, URL);
        }
    }

    private void questions(String sl, String URL) {
        String[] questions = sl.split("\\?");
        for (String qu : questions) {
            dots(qu, URL);
        }
    }

    private void dots(String qu, String URL) {
        String[] dots = qu.split("\\.");
        for (String dot : dots) {
            equals(dot, URL);
        }
    }

    private void equals(String dot, String URL) {
        String[] equals = dot.split("=");
        for (String eq : equals) {
            underscores(eq, URL);
        }
    }

    private void underscores(String eq, String URL) {
        String[] underscores = eq.split("_");
        for (String un : underscores) {
            dashes(un, URL);
        }
    }

    private void dashes(String un, String URL) {
        String[] dashes = un.split("-");
        for (String dash : dashes) {
            List<String> segments = segment(dash);
            segments(segments, URL);
        }
    }

    private void segments(List<String> segments, String URL) {
        for (String segment : segments) {
            ack.addREM(segment);
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

}
