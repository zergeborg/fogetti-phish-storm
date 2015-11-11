package fogetti.phish.storm.db;

public interface JedisCallback {

	void onMessage(String channel, String message);

}