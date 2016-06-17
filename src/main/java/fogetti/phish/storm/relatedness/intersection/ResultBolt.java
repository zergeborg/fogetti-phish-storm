package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.storm.metric.api.CountMetric;
import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.relatedness.AckResult;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import redis.clients.jedis.Jedis;

public class ResultBolt extends AbstractRedisBolt {

    private static final String REDIS_INTERSECTION_PREFIX = "intersect:";
    private static final long serialVersionUID = 4351069045589201855L;
    private static final Logger logger = LoggerFactory.getLogger(ResultBolt.class);
    private String resultDataFile;
    private ObjectMapper mapper;
    private final int METRICS_WINDOW = 10;
    private int connectTimeout = 10000;
    private int socketTimeout = 10000;
    private Encoder encoder;
    private transient CountMetric intersectionMsgLookupSuccess;
    private transient CountMetric intersectionMsgLookupFailure;
    private transient CountMetric intersectionActionPerformed;
    private transient CountMetric intersectionActionSkipped;
    private transient CountMetric intersectionActionLogged;
    private transient CountMetric intersectionActionSaved;
    
    public ResultBolt(JedisPoolConfig config, String resultDataFile) {
        super(config);
        this.resultDataFile = resultDataFile;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        super.prepare(stormConf, context, collector);
        mapper = new ObjectMapper();
        encoder = Base64.getEncoder();
        intersectionMsgLookupSuccess = new CountMetric();
        context.registerMetric("int-msg-lookup-success",
                               intersectionMsgLookupSuccess,
                               METRICS_WINDOW);
        intersectionMsgLookupFailure = new CountMetric();
        context.registerMetric("int-msg-lookup-failure",
                               intersectionMsgLookupFailure,
                               METRICS_WINDOW);
        intersectionActionPerformed = new CountMetric();
        context.registerMetric("int-action-performed",
                               intersectionActionPerformed,
                               METRICS_WINDOW);
        intersectionActionSkipped = new CountMetric();
        context.registerMetric("int-action-skipped",
                               intersectionActionSkipped,
                               METRICS_WINDOW);
        intersectionActionLogged = new CountMetric();
        context.registerMetric("int-action-logged",
                               intersectionActionLogged,
                               METRICS_WINDOW);
        intersectionActionSaved = new CountMetric();
        context.registerMetric("int-action-saved",
                               intersectionActionSaved,
                               METRICS_WINDOW);
    }

    @Override
    public void execute(Tuple input) {
        String message = input.getString(0);
        try {
            AckResult result = mapper.readValue(message, AckResult.class);
            URLSegments segments = findSegments(result);
            performIntersection(segments, result, message);
            collector.ack(input);
        } catch (Throwable e) {
            logger.info("Message [{}] failed", message, e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

    private URLSegments findSegments(AckResult result) {
        try (Jedis jedis = (Jedis) getInstance()) {
            String encodedURL = encoder.encodeToString(result.URL.getBytes(StandardCharsets.UTF_8));
            String key = REDIS_INTERSECTION_PREFIX + encodedURL;
            Map<String, String> rawSegments = jedis.hgetAll(key);
            URLSegments segments = URLSegments.fromStringMap(rawSegments);
            intersectionMsgLookupSuccess.incr();
            return segments;
        } catch (IOException e) {
            logger.error("Could not find saved segments", e);
            intersectionMsgLookupFailure.incr();
        }
        return null;
    }

    private void performIntersection(URLSegments segments, AckResult result, String message) {
        if (segments != null) {
            Map<String, Terms> MLDTermindex = segments.getMLDTerms(result);
            Map<String, Terms> MLDPSTermindex = segments.getMLDPSTerms(result);
            Map<String, Terms> REMTermindex = segments.getREMTerms(result);
            Map<String, Terms> RDTermindex = segments.getRDTerms(result);
            segments.removeIf(termEntry -> REMTermindex.containsKey(termEntry.getKey()));
            segments.removeIf(termEntry -> RDTermindex.containsKey(termEntry.getKey()));
            OkHttpClient client = buildClient();
            IntersectionResult intersection = new IntersectionResult(RDTermindex,REMTermindex,MLDTermindex,MLDPSTermindex, new WrappedRequest(), result.URL, client);
            intersection.init();
            logIntersectionResult(intersection, result.URL);
            saveIntersectionResult(intersection, result.URL);
            logger.info("Message [{}] intersected", message);
            intersectionActionPerformed.incr();
        } else {
            logger.warn("There are no segments for [{}]. Skipping intersection", result.URL);
            intersectionActionSkipped.incr();
        }
    }

    private OkHttpClient buildClient() {
        OkHttpClient client
            = new OkHttpClient
                .Builder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        // try the request
                        Response response = chain.proceed(request);
                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {
                            logger.info("Retry request [{}] was not successful", tryCount);
                            tryCount++;
                            // retry the request
                            response = chain.proceed(request);
                        }
                        // otherwise just pass the original response on
                        return response;
                    }
                }).build();
        return client;
    }

    private void logIntersectionResult(IntersectionResult intersection, String URL) {
        logger.info("[JRR={}, "
                    + "JRA={}, "
                    + "JAA={}, "
                    + "JAR={}, "
                    + "JARRD={}, "
                    + "JARREM={}, "
                    + "CARDREM={}, "
                    + "RATIOAREM={}, "
                    + "RATIORREM={}, "
                    + "MLDRES={}, "
                    + "MLDPSRES={}, "
                    + "RANKING={}, "
                    + "URL={}]",
                    intersection.JRR(),
                    intersection.JRA(),
                    intersection.JAA(),
                    intersection.JAR(),
                    intersection.JARRD(),
                    intersection.JARREM(),
                    intersection.CARDREM(),
                    intersection.RATIOAREM(),
                    intersection.RATIORREM(),
                    intersection.MLDRES(),
                    intersection.MLDPSRES(),
                    intersection.RANKING(),
                    URL);
        intersectionActionLogged.incr();
    }

    private void saveIntersectionResult(IntersectionResult intersection, String URL) {
        List<String> lines = Arrays.asList(new String[] {
                String.format(
                "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%f,"
                + "%d,"
                + "%f,"
                + "%f,"
                + "%d,"
                + "%d,"
                + "%d,"
                + "\"%s\"",
                intersection.JRR(),
                intersection.JRA(),
                intersection.JAA(),
                intersection.JAR(),
                intersection.JARRD(),
                intersection.JARREM(),
                intersection.CARDREM(),
                intersection.RATIOAREM(),
                intersection.RATIORREM(),
                intersection.MLDRES(),
                intersection.MLDPSRES(),
                intersection.RANKING(),
                URL)});
        try {
            Files.write(Paths.get(resultDataFile), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            intersectionActionSaved.incr();
        } catch (IOException e) {
            logger.error("Writing the result failed", e);
        }
    }

}