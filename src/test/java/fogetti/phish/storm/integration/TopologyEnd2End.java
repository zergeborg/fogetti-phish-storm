package fogetti.phish.storm.integration;

import backtype.storm.generated.StormTopology;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		StormTopology topology = PhishTopologyBuilder.build();
		PhishTopologyRunner.run(args, topology);
	}

}
