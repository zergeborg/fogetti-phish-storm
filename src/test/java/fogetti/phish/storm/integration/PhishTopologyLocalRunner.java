package fogetti.phish.storm.integration;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;

public class PhishTopologyLocalRunner {

	public static void run(String[] args, StormTopology topology)
			throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		Config conf = new Config();
		if (args != null && args.length > 0) {
			runWithProgressbar(args, topology, conf);
		}
		else {
			runSimply(topology, conf);
		}
	}

	private static void runWithProgressbar(String[] args, StormTopology topology, Config conf)
			throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		conf.setNumWorkers(3);
		StormSubmitter.submitTopologyWithProgressBar(args[0], conf, topology);
	}

	private static void runSimply(StormTopology topology, Config conf) {
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("test", conf, topology);
		Utils.sleep(600000);
		cluster.killTopology("test");
		cluster.shutdown();
	}
}