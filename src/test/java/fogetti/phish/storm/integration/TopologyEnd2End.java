package fogetti.phish.storm.integration;

import java.util.Scanner;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import fogetti.phish.storm.relatedness.BingSemBolt;
import fogetti.phish.storm.relatedness.GoogleSemBolt;
import fogetti.phish.storm.relatedness.IntersectionBolt;
import fogetti.phish.storm.relatedness.URLBolt;
import fogetti.phish.storm.relatedness.URLSpout;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();

		Scanner console = new Scanner(System.in);
		String uname = "";
		if (console.hasNext())
			uname = console.next();
		String pword = "";
		if (console.hasNext())
			pword = console.next();
		console.close();
		builder.setSpout("urlsource", new URLSpout(), 1);
		builder.setBolt("urlsplit", new URLBolt(), 7).fieldsGrouping("urlsource", new Fields("word"));
		builder.setBolt("googletrends", new GoogleSemBolt(uname, pword), 7).fieldsGrouping("urlsplit", new Fields("url"));
		builder.setBolt("bingrelatedkeywords", new BingSemBolt(), 7).fieldsGrouping("urlsplit", new Fields("url"));
		builder.setBolt("intersection", new IntersectionBolt()).globalGrouping("googletrends").globalGrouping("bingrelatedkeywords");

		Config conf = new Config();
		conf.setDebug(true);

		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);

			StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
		}
		else {

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test", conf, builder.createTopology());
			Utils.sleep(10000);
			cluster.killTopology("test");
			cluster.shutdown();
		}

	}
}
