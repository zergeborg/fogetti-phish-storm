package fogetti.phish.storm.relatedness;

public interface JedisCallback {

	void onMessage(String channel, String message);

}