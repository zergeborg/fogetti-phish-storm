package fogetti.phish.storm.integration;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import fogetti.phish.storm.db.JedisListener;
import fogetti.phish.storm.relatedness.BingSemBolt;
import fogetti.phish.storm.relatedness.GoogleSemBolt;
import fogetti.phish.storm.relatedness.URLBolt;
import fogetti.phish.storm.relatedness.URLSpout;
import fogetti.phish.storm.relatedness.intersection.BloomFilter;
import fogetti.phish.storm.relatedness.intersection.IntersectionBolt;

public class PhishTopologyBuilder {

	public static StormTopology build() throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

		Scanner console = new Scanner(System.in);
		String uname = uname(console);
		String pword = pword(console);
		console.close();
		builder.setSpout("urlsource", new URLSpout(), 1);
		builder.setBolt("urlsplit", new URLBolt(), 7)
			.fieldsGrouping("urlsource", new Fields("word"));
		builder.setBolt("googletrends", new GoogleSemBolt(uname, pword), 7)
			.fieldsGrouping("urlsplit", new Fields("url"));
		builder.setBolt("bingrelatedkeywords", new BingSemBolt(), 7)
			.fieldsGrouping("urlsplit", new Fields("url"));
		builder.setBolt("intersection", bloomfilter())
			.globalGrouping("googletrends")
			.globalGrouping("bingrelatedkeywords");
		StormTopology topology = builder.createTopology();
		return topology;
	}

	private static IntersectionBolt bloomfilter() throws Exception {
		BloomFilter bloomfilter = new BloomFilter() { private static final long serialVersionUID = 5105509799523060930L; @Override public void run() {} };
		IntersectionBolt callback = new IntersectionBolt(bloomfilter);
		JedisListener listener = new JedisListener(6379, 2000, "phish", callback);
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