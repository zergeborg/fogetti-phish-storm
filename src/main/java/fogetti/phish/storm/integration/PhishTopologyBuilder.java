package fogetti.phish.storm.integration;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.redis.common.config.JedisPoolConfig;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import fogetti.phish.storm.relatedness.AckResult;
import fogetti.phish.storm.relatedness.BingSemBolt;
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
		String uname = System.getProperty("uname");
		String pword = System.getProperty("pword");
		return build(countDataFile, psDataFile, urlDataFile, uname, pword);
	}

	public static StormTopology build(String countDataFile, String psDataFile, String urlDataFile, String uname, String pword) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		Map<String, AckResult> ackIndex = new HashMap<>();

		JedisPoolConfig poolConfig = new JedisPoolConfig.Builder()
	        .setHost("petrucci").setPort(6379).setPassword("Macska12").build();
		builder.setSpout("urlsource", new URLSpout(countDataFile, psDataFile, urlDataFile, ackIndex, poolConfig), 1);
		builder.setBolt("urlsplit", new URLBolt(), 1)
			.fieldsGrouping("urlsource", new Fields("word", "url"))
			.setNumTasks(2);
		builder.setBolt("googletrends", new GoogleSemBolt(uname, pword, poolConfig), 1)
			.fieldsGrouping("urlsplit", new Fields("segment", "url"))
			.setNumTasks(2);
		builder.setBolt("bingrelatedkeywords", new BingSemBolt(poolConfig), 1)
			.fieldsGrouping("urlsplit", new Fields("segment", "url"))
			.setNumTasks(2);
		builder.setBolt("intersection", intersectionBolt(poolConfig))
			.globalGrouping("googletrends")
			.globalGrouping("bingrelatedkeywords");
		StormTopology topology = builder.createTopology();
		return topology;
	}

	private static IntersectionBolt intersectionBolt(JedisPoolConfig poolConfig) throws Exception {
		IntersectionAction action = new IntersectionAction() { private static final long serialVersionUID = 5105509799523060930L; @Override public void run() {} };
		IntersectionBolt callback = new IntersectionBolt(action, poolConfig);
		return callback;
	}

}