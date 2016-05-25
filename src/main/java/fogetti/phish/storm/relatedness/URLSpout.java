package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the following real time phishing classifier:
 * <a href="https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf">
 * https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf
 * </a>
 * @author gergely.nagy
 *
 */
public abstract class URLSpout extends BaseRichSpout {

	private static final long serialVersionUID = -6424905468176142975L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);
	private SpoutOutputCollector collector;
	private List<String> urllist;
	private String urlDataFile;
	private JedisPoolConfig config;
    private IAcker acker;
    private Encoder encoder;
    private Decoder decoder;
    private String[] schemes = {"http","https"};
    private UrlValidator urlValidator;
    private final int METRICS_WINDOW = 60;
    private transient CountMetric ackedPublished;
    private transient CountMetric ackedSaved;
    private transient CountMetric ackedSkipped;
    private transient CountMetric ackedRetried;

	public URLSpout(String urlDataFile, JedisPoolConfig config) {
		this.urlDataFile = urlDataFile;
		this.config = config;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.urllist = readURLListFromFile();
        this.encoder = Base64.getEncoder();
        this.decoder = Base64.getDecoder();
        this.urlValidator = new UrlValidator(schemes);
        ackedPublished = new CountMetric();
        context.registerMetric("acked-published",
                               ackedPublished,
                               METRICS_WINDOW);
        ackedSaved = new CountMetric();
        context.registerMetric("akced-saved",
                               ackedSaved,
                               METRICS_WINDOW);
        ackedSkipped = new CountMetric();
        context.registerMetric("acked-skipped",
                               ackedSkipped,
                               METRICS_WINDOW);
        ackedRetried = new CountMetric();
        context.registerMetric("acked-retried",
                               ackedRetried,
                               METRICS_WINDOW);
        this.acker = buildAcker(collector, config, ackedPublished, ackedSaved, ackedSkipped, ackedRetried);
	}

    public abstract IAcker buildAcker(SpoutOutputCollector collector, JedisPoolConfig config, CountMetric ackedPublished, CountMetric ackedSaved, CountMetric ackedSkipped, CountMetric ackedRetried);

	private List<String> readURLListFromFile() {
		List<String> urls = new CopyOnWriteArrayList<>();
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

	@Override
	public void nextTuple() {
		// Check the case when we read first seen elements
		if (!urllist.isEmpty()) {
			String URLWithScheme = urllist.remove(0);
			doNextTuple(URLWithScheme);
		}
	}

	private void doNextTuple(String URLWithScheme) {
	    String URL = URLWithScheme + "#" +System.currentTimeMillis();
        String encodedURL = encoder.encodeToString(URL.getBytes(StandardCharsets.UTF_8));
		collector.emit(new Values(encodedURL), encodedURL);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));
	}

	@Override
	public void ack(Object encodedURL) {
	    logger.info("Acking [{}]", encodedURL);
	    acker.enqueue(encodedURL.toString());
	}

	@Override
	public void fail(Object encodedURL) {
        String URL = getURL(encodedURL.toString());
		logger.debug("Message [msg={}] failed", URL);
		if (urlValidator.isValid(URL)) {
		    logger.warn("Requeueing [msg={}]", URL);
		    urllist.add(URL.toString());
		} else {
		    logger.warn("Skipping invalid URL [msg={}]", URL);
		}
	}

    private String getURL(String encodedURL) {
        byte[] decoded = decoder.decode(encodedURL);
        String longURL = new String(decoded, StandardCharsets.UTF_8);
        String URL = StringUtils.substringBeforeLast(longURL, "#");
        return URL;
    }

}
