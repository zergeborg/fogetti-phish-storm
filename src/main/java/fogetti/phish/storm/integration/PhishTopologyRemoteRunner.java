package fogetti.phish.storm.integration;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.metric.LoggingMetricsConsumer;

public class PhishTopologyRemoteRunner {

	public static void main(String[] args) throws Exception {
		StormTopology topology = PhishTopologyBuilder.build();
		
		Config config = new Config();
		config.setNumWorkers(17);
		config.setMessageTimeoutSecs(20);
		config.put(Config.TOPOLOGY_EXECUTOR_RECEIVE_BUFFER_SIZE,
	               new Integer(131072));
	    config.put(Config.TOPOLOGY_EXECUTOR_SEND_BUFFER_SIZE,
	               new Integer(131072));
	    config.put(Config.TOPOLOGY_TRANSFER_BUFFER_SIZE,
	               new Integer(131072));
	    config.registerMetricsConsumer(LoggingMetricsConsumer.class, 1);
		
		StormSubmitter.submitTopology("phish-storm", config, topology);
	}
	
}