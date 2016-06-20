package fogetti.phish.storm.integration;

import static fogetti.phish.storm.relatedness.URLSpout.INTERSECTION_STREAM;
import static fogetti.phish.storm.relatedness.URLSpout.SUCCESS_STREAM;

import java.io.File;

import org.apache.storm.generated.StormTopology;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.TopologyBuilder;

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
			.setSpout("urlsource-0", new URLSpout(urlDataFile, poolConfig), 1)
			.setMaxSpoutPending(5)
			.setNumTasks(1);
        builder
            .setSpout("urlsource-1", new URLSpout(urlDataFile, poolConfig), 1)
            .setMaxSpoutPending(5)
            .setNumTasks(1);
        builder
            .setSpout("urlsource-2", new URLSpout(urlDataFile, poolConfig), 1)
            .setMaxSpoutPending(5)
            .setNumTasks(1);
        builder
            .setSpout("urlsource-3", new URLSpout(urlDataFile, poolConfig), 1)
            .setMaxSpoutPending(5)
            .setNumTasks(1);
        builder.setBolt("urlmatch-0", new MatcherBolt(countDataFile, psDataFile, poolConfig), 1)
            .allGrouping("urlsource-0")
            .setNumTasks(1);
        builder.setBolt("urlmatch-1", new MatcherBolt(countDataFile, psDataFile, poolConfig), 1)
            .allGrouping("urlsource-1")
            .setNumTasks(1);
        builder.setBolt("urlmatch-2", new MatcherBolt(countDataFile, psDataFile, poolConfig), 1)
            .allGrouping("urlsource-2")
            .setNumTasks(1);
        builder.setBolt("urlmatch-3", new MatcherBolt(countDataFile, psDataFile, poolConfig), 1)
            .allGrouping("urlsource-3")
            .setNumTasks(1);
		builder.setBolt("googletrends-0", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
		    .addConfiguration("timeout", 5000)
		    .shuffleGrouping("urlmatch-0")
			.setNumTasks(64);
        builder.setBolt("googletrends-1", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-1")
            .setNumTasks(64);
        builder.setBolt("googletrends-2", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-2")
            .setNumTasks(64);
        builder.setBolt("googletrends-3", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-3")
            .setNumTasks(64);
        builder.setBolt("googletrends-4", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-0")
            .setNumTasks(64);
        builder.setBolt("googletrends-5", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-1")
            .setNumTasks(64);
        builder.setBolt("googletrends-6", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-2")
            .setNumTasks(64);
        builder.setBolt("googletrends-7", new ClientBuildingGoogleSemBolt(poolConfig, new File(proxyDataFile), new WrappedRequest()), 64)
            .addConfiguration("timeout", 5000)
            .shuffleGrouping("urlmatch-3")
            .setNumTasks(64);
		builder.setBolt("segmentsaving", segmentSavingBolt(poolConfig), 32)
			.shuffleGrouping("googletrends-0")
			.shuffleGrouping("googletrends-1")
			.shuffleGrouping("googletrends-2")
			.shuffleGrouping("googletrends-3")
			.setNumTasks(32);
        builder.setBolt("intersection", intersectionBolt(poolConfig), 32)
            .shuffleGrouping("urlsource-0", INTERSECTION_STREAM)
            .shuffleGrouping("urlsource-1", INTERSECTION_STREAM)
            .shuffleGrouping("urlsource-2", INTERSECTION_STREAM)
            .shuffleGrouping("urlsource-3", INTERSECTION_STREAM)
            .setNumTasks(32);
        builder.setBolt("result", resultBolt(poolConfig, resultDataFile), 1)
            .globalGrouping("urlsource-0", SUCCESS_STREAM)
            .globalGrouping("urlsource-1", SUCCESS_STREAM)
            .globalGrouping("urlsource-2", SUCCESS_STREAM)
            .globalGrouping("urlsource-3", SUCCESS_STREAM)
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