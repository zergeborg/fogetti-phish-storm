package fogetti.phish.storm.db;

import java.io.Serializable;

public interface IPublishing extends Serializable {

	void publish(String channel, String msg);
}