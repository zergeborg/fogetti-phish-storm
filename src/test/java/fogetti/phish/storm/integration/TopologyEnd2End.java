package fogetti.phish.storm.integration;

import backtype.storm.generated.StormTopology;
import redis.embedded.RedisServer;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		RedisServer server = new RedisServer();
		server.start();
		StormTopology topology = PhishTopologyBuilder.build();
		PhishTopologyRunner.run(args, topology);
		server.stop();
	}

}
