package fogetti.phish.storm.integration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.redis.common.config.JedisPoolConfig;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import fogetti.phish.storm.client.TrendRequest;
import fogetti.phish.storm.relatedness.AckResult;
import fogetti.phish.storm.relatedness.GoogleSemBolt;
import fogetti.phish.storm.relatedness.URLBolt;
import fogetti.phish.storm.relatedness.URLSpout;
import fogetti.phish.storm.relatedness.intersection.IntersectionAction;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;

public class PhishTopologyBuilder {

	public static StormTopology build() throws Exception {
		String countDataFile = System.getProperty("count.data.file");
		String psDataFile = System.getProperty("ps.data.file");
		String urlDataFile = System.getProperty("url.data.file");
		String proxyDataFile = System.getProperty("proxy.data.file");
		return build(countDataFile, psDataFile, urlDataFile, proxyDataFile, "petrucci", 6379, "Macska12");
	}

	public static StormTopology build(String countDataFile, String psDataFile, String urlDataFile, String proxyDataFile, String redishost, Integer redisport, String redispword) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		Map<String, AckResult> ackIndex = new HashMap<>();

		JedisPoolConfig poolConfig = new JedisPoolConfig.Builder()
	        .setHost(redishost).setPort(redisport).setPassword(redispword).build();
		builder
			.setSpout("urlsource", new URLSpout(countDataFile, psDataFile, urlDataFile, ackIndex, poolConfig), 1)
			.setMaxSpoutPending(50);
		builder.setBolt("urlsplit", new URLBolt(), 4)
			.fieldsGrouping("urlsource", new Fields("word", "url"))
			.setNumTasks(2);
		builder.setBolt("googletrends", new GoogleSemBolt(poolConfig, new File(proxyDataFile), new TrendRequest()), 1)
			.fieldsGrouping("urlsplit", new Fields("segment", "url"))
			.setNumTasks(1);
		builder.setBolt("intersection", intersectionBolt(poolConfig))
			.globalGrouping("googletrends");
		StormTopology topology = builder.createTopology();
		return topology;
	}

	private static IntersectionBolt intersectionBolt(JedisPoolConfig poolConfig) throws Exception {
		IntersectionAction action = new IntersectionAction() { private static final long serialVersionUID = 5105509799523060930L; @Override public void run() {} };
		IntersectionBolt callback = new IntersectionBolt(action, poolConfig);
		return callback;
	}

}