package fogetti.phish.storm.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.storm.redis.common.config.JedisPoolConfig;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import fogetti.phish.storm.db.JedisListener;
import fogetti.phish.storm.relatedness.AckResult;
import fogetti.phish.storm.relatedness.BingSemBolt;
import fogetti.phish.storm.relatedness.GoogleSemBolt;
import fogetti.phish.storm.relatedness.URLBolt;
import fogetti.phish.storm.relatedness.URLSpout;
import fogetti.phish.storm.relatedness.intersection.IntersectionAction;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;

public class PhishTopologyBuilder {

	public static StormTopology build() throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
		String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
		String urlDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/url-list.txt";
		Map<String, AckResult> ackIndex = new HashMap<>();

		Scanner console = new Scanner(System.in);
		String uname = uname(console);
		String pword = pword(console);
		JedisPoolConfig poolConfig = new JedisPoolConfig.Builder()
	        .setHost("petrucci").setPort(6379).setPassword("Macska12").build();
		console.close();
		builder.setSpout("urlsource", new URLSpout(countDataFile, psDataFile, urlDataFile, ackIndex, poolConfig), 1);
		builder.setBolt("urlsplit", new URLBolt(), 7)
			.fieldsGrouping("urlsource", new Fields("word", "url"));
		builder.setBolt("googletrends", new GoogleSemBolt(uname, pword, poolConfig), 7)
			.fieldsGrouping("urlsplit", new Fields("segment", "url"));
		builder.setBolt("bingrelatedkeywords", new BingSemBolt(poolConfig), 7)
			.fieldsGrouping("urlsplit", new Fields("segment", "url"));
		builder.setBolt("intersection", intersectionBolt(poolConfig))
			.globalGrouping("googletrends")
			.globalGrouping("bingrelatedkeywords");
		StormTopology topology = builder.createTopology();
		return topology;
	}

	private static IntersectionBolt intersectionBolt(JedisPoolConfig poolConfig) throws Exception {
		IntersectionAction action = new IntersectionAction() { private static final long serialVersionUID = 5105509799523060930L; @Override public void run() {} };
		IntersectionBolt callback = new IntersectionBolt(action, poolConfig);
		JedisListener listener = new JedisListener(poolConfig.getHost(), poolConfig.getPort(), poolConfig.getTimeout(), poolConfig.getPassword(), "phish", callback);
		new Thread(listener, "subscriberThread").start();
		TimeUnit.MILLISECONDS.sleep(100);
		return callback;
	}

	private static String uname(Scanner console) {
		String uname = "";
		if (console.hasNext())
			uname = console.next();
		return uname;
	}

	private static String pword(Scanner console) {
		String pword = "";
		if (console.hasNext())
			pword = console.next();
		return pword;
	}

}