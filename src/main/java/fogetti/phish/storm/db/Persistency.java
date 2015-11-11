package fogetti.phish.storm.db;

public interface Persistency {

	void publish(String channel, String msg);
}