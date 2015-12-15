package fogetti.phish.storm.integration;

import java.util.Scanner;

import backtype.storm.generated.StormTopology;
import redis.embedded.RedisServer;

public class TopologyEnd2End {

	public static void main(String[] args) throws Exception {
		RedisServer server = new RedisServer();
		server.start();
		String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
		String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";
		String urlDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/url-list.txt";
		Scanner console = new Scanner(System.in);
		String uname = uname(console);
		String pword = pword(console);
		console.close();
		StormTopology topology = PhishTopologyBuilder.build(countDataFile, psDataFile, urlDataFile, uname, pword, "localhost", 6379, null);
		PhishTopologyLocalRunner.run(args, topology);
		server.stop();
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
