package fogetti.phish.storm.integration;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;

public class PhishTopologyRemoteRunner {

	public static void main(String[] args) throws Exception {
		StormTopology topology = PhishTopologyBuilder.build();
		
		Config config = new Config();
		config.setNumWorkers(10);
		config.setMessageTimeoutSecs(30);
		
		StormSubmitter.submitTopology("phish-storm-topology", config, topology);
	}
	
}