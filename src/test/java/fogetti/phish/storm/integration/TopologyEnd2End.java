package fogetti.phish.storm.integration;

import org.apache.storm.generated.StormTopology;

import redis.embedded.RedisServer;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		RedisServer server = new RedisServer();
		server.start();
		String countDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/1gram-count.txt";
		String psDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
		String urlDataFile = "/Users/fogetti/Work/fogetti-phish-storm/src/main/resources/url-list.txt";
        String proxyDataFile = "/Users/fogetti/Work/proxy-check/[unique-proxies]elite_proxies_2016_06_03.txt";
		String resultDataFile = "/Users/fogetti/Work/phishing-result.csv";
		String accessKey = args[0];
		String secretKey = args[1];
		StormTopology topology = PhishTopologyBuilder.build(countDataFile, psDataFile, urlDataFile, proxyDataFile, resultDataFile, "localhost", 6379, null, accessKey, secretKey);
		PhishTopologyLocalRunner.run(args, topology);
		server.stop();
	}

}
