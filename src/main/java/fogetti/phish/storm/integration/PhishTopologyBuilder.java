package fogetti.phish.storm.integration;

import static fogetti.phish.storm.relatedness.URLSpout.INTERSECTION_STREAM;
import static fogetti.phish.storm.relatedness.URLSpout.SUCCESS_STREAM;

import java.io.File;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import fogetti.phish.storm.client.WrappedRequest;
import fogetti.phish.storm.relatedness.ClientBuildingGoogleSemBolt;
import fogetti.phish.storm.relatedness.MatcherBolt;
import fogetti.phish.storm.relatedness.URLSpout;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;
import fogetti.phish.storm.relatedness.intersection.ResultBolt;
import fogetti.phish.storm.relatedness.intersection.SegmentSavingBolt;

public class PhishTopologyBuilder {

    public static final String REDIS_SEGMENT_PREFIX = "segment:"; 
    
	public static StormTopology build() throws Exception {
		String countDataFile = System.getProperty("count.data.file");
		String psDataFile = System.getProperty("ps.data.file");
		String urlDataFile = System.getProperty("url.data.file");
		String proxyDataFile = System.getProperty("proxy.data.file");
		String resultDataFile = System.getProperty("result.data.file");
		return build(countDataFile, psDataFile, urlDataFile, proxyDataFile, resultDataFile, "petrucci", 6379, "Macska12");
	}

	public static StormTopology build(
	        String countDataFile,
	        String psDataFile,
	        String urlDataFile,
	        String proxyDataFile,
	        String resultDataFile,
	        String redishost,
	        Integer redisport,
	        String redispword) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

		JedisPoolConfig poolConfig = new JedisPoolConfig.Builder()
	        .setHost(redishost).setPort(redisport).setPassword(redispword).build();
		builder
			.setSpout("urlsource", new URLSpout(urlDataFile, poolConfig), 2)
			.setMaxSpoutPending(16)
			.setNumTasks(16);
        builder.setBolt("urlmatch", new MatcherBolt(countDataFile, psDataFile, poolConfig), 8)
            .fieldsGrouping("urlsource", new Fields("url"))
            .setNumTasks(64);
		builder.setBolt("googletrends", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 512)
		    .addConfiguration("timeout", 30000)
		    .fieldsGrouping("urlmatch", new Fields("word", "url"))
			.setNumTasks(1024);
		builder.setBolt("segmentsaving", segmentSavingBolt(poolConfig), 32)
			.shuffleGrouping("googletrends")
			.setNumTasks(128);
        builder.setBolt("intersection", intersectionBolt(poolConfig), 32)
            .shuffleGrouping("urlsource", INTERSECTION_STREAM)
            .setNumTasks(128);
        builder.setBolt("result", resultBolt(poolConfig, resultDataFile), 1)
            .globalGrouping("urlsource", SUCCESS_STREAM)
            .setNumTasks(1);
		StormTopology topology = builder.createTopology();
		return topology;
	}

    private static SegmentSavingBolt segmentSavingBolt(JedisPoolConfig poolConfig) throws Exception {
        SegmentSavingBolt callback = new SegmentSavingBolt(poolConfig);
        return callback;
    }

    private static IntersectionBolt intersectionBolt(JedisPoolConfig poolConfig) throws Exception {
		IntersectionBolt callback = new IntersectionBolt(poolConfig);
		return callback;
	}

    private static IRichBolt resultBolt(JedisPoolConfig config, String resultDataFile) {
        return new ResultBolt(config, resultDataFile);
    }

}