package fogetti.phish.storm.db;

import java.io.Serializable;

public interface Persistency extends Serializable {

	void publish(String channel, String msg);
}