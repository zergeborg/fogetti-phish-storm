package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.storm.metric.api.CountMetric;
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
    private double N = 1024908267229.;
    private double itervalues = 0.;
    private Map<String, List<String>> memomap = new HashMap<>();
    private Map<String, Long> lookup = new HashMap<>();
    private String countDataFile;
    private String psDataFile;
    private AckResult ack;
    private ObjectMapper mapper;
    private Decoder decoder;
    private String[] schemes = {"http","https"};
    private UrlValidator urlValidator;
    private transient CountMetric matcherEmittedRDSegment;
    private transient CountMetric matcherEmittedREMSegment;
    private transient CountMetric matcherAckedCnt;
    private final int METRICS_WINDOW = 10;

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
        this.lookup = readCountFromFile();
        this.mapper = new ObjectMapper();
        this.decoder = Base64.getDecoder();
        this.urlValidator = new UrlValidator(schemes);
        matcherEmittedRDSegment = new CountMetric();
        context.registerMetric("match-emitted-rd-segment",
                               matcherEmittedRDSegment,
                               METRICS_WINDOW);
        matcherEmittedREMSegment = new CountMetric();
        context.registerMetric("match-emitted-rem-segment",
                               matcherEmittedREMSegment,
                               METRICS_WINDOW);
        matcherAckedCnt = new CountMetric();
        context.registerMetric("match-acked-cnt",
                               matcherAckedCnt,
                               METRICS_WINDOW);
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
        try {
            createNewAckResult();
            String encodedURL = input.getStringByField("url");
            byte[] decodedURL = decoder.decode(encodedURL);
            String schemedUrl = new String(decodedURL, StandardCharsets.UTF_8);
            if (urlValidator.isValid(schemedUrl)) {
                String shortURL = StringUtils.substringBeforeLast(schemedUrl, "#");
                ack.URL = shortURL;
                calculate(shortURL);
                if (saveResult(encodedURL)) {
                    emit(input, encodedURL);
                } else {
                    collector.fail(input);
                }
            } else {
                logger.warn("Invalid URL [{}]. Skipping emission", schemedUrl);
                collector.fail(input);
            }
        } catch(Exception e) {
            logger.error("Unexpected error", e);
            collector.fail(input);
        }
    }

    void createNewAckResult() {
        ack = new AckResult();
    }

    private void calculate(String schemedUrl) {
        logger.debug("Calculating relatedness for [{}]", schemedUrl);
        String[] urlParts = schemedUrl.split("//");
        if (urlParts.length > 1) {
            String URL = urlParts[1];
            calculateRDurl(URL);
            calculateREMurl(URL);
        } else {
            logger.warn("Invalid URL [{}]. Skipping calculation", schemedUrl);
        }
    }

    void calculateRDurl(String URL) {
        String mldps = URL.split("/")[0];
        PublicSuffixMatcher matcher = readPublicSuffixListFromFile();
        String ps = matcher.findPublicSuffix(mldps);
        logger.debug("URL [{}] has the following public suffix [{}]", URL, ps);
        String beforeLast = StringUtils.substringBeforeLast(mldps, "." + ps);
        String mld = beforeLast;
        if (beforeLast.contains(".")) mld = StringUtils.substringAfterLast(beforeLast, ".");
        ack.MLD = mld;
        ack.addRD(mld);
        ack.MLDPS = mld+"."+ps;
        ack.addRD(mld+"."+ps);
    }

    private PublicSuffixMatcher readPublicSuffixListFromFile() {
        String location = System.getProperty("ps-location", psDataFile);
        PublicSuffixMatcher matcher = new PublicSuffixMatcher(location);
        logger.info("Reading public suffix data from [{}] ...", location);
        matcher.load();
        return matcher;
    }

    private boolean saveResult(String encodedURL) {
        Jedis jedis = null;
        try {
            jedis = (Jedis) getInstance();
            List<String> message = jedis.lrange("acked:"+encodedURL, 0L, 0L);
            if (message == null || message.isEmpty()) {
                String result = mapper.writeValueAsString(ack);
                logger.info("Saving [AckResult={}]", result);
                jedis.rpush("acked:"+encodedURL, result);
            }
        } catch (JsonProcessingException e) {
            logger.error("Could not save AckResult", e);
            return false;
        } finally {
            returnInstance(jedis);
        }
        return true;
    }

    private void emit(Tuple input, String encodedURL) {
        for (String word : ack.getRDurl()) {
            logger.debug("Emitting [{}]", word);
            collector.emit(input, new Values(word, encodedURL));
            matcherEmittedRDSegment.incr();
        }
        for (String word : ack.getREMurl()) {
            logger.debug("Emitting [{}]", word);
            collector.emit(input, new Values(word, encodedURL));
            matcherEmittedREMSegment.incr();
        }
        logger.debug("Acking [{}]", input);
        collector.ack(input);
        matcherAckedCnt.incr();
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
            System.out.println("Searching result for: "+text);
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
