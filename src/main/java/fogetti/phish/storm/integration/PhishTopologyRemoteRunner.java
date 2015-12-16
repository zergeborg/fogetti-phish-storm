package fogetti.phish.storm.integration;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;

public class PhishTopologyRemoteRunner {

	public static void main(String[] args) throws Exception {
		StormTopology topology = PhishTopologyBuilder.build();
		
		Config config = new Config();
		config.setNumWorkers(2);
		config.setMessageTimeoutSecs(30);
		
		StormSubmitter.submitTopology("phish-storm-topology", config, topology);
	}
	
}