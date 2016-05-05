package fogetti.phish.storm.integration;

import backtype.storm.generated.StormTopology;
import redis.embedded.RedisServer;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		RedisServer server = new RedisServer();
		server.start();
		String countDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/1gram-count.txt";
		String psDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
		String urlDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/url-list.txt";
		String proxyDataFile = "/Users/fogetti/Work/proxy-check/working-proxies.txt";
		StormTopology topology = PhishTopologyBuilder.build(countDataFile, psDataFile, urlDataFile, proxyDataFile, "localhost", 6379, null);
		PhishTopologyLocalRunner.run(args, topology);
		server.stop();
	}

}
