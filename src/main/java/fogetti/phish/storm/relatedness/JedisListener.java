package fogetti.phish.storm.relatedness;

import redis.clients.jedis.JedisPubSub;

public class JedisListener extends JedisPubSub {

	private final JedisCallback callback;
	
	public JedisListener(JedisCallback callback) {
		this.callback = callback;
	}
	
	@Override
	public void onMessage(String channel, String message) {
		callback.onMessage(channel, message);
	}

}
