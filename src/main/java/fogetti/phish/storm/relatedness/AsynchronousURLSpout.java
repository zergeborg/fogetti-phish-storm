package fogetti.phish.storm.relatedness;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.spout.SpoutOutputCollector;

public class AsynchronousURLSpout extends URLSpout {

    private static final long serialVersionUID = 7363220605350798971L;

    public AsynchronousURLSpout(String urlDataFile, JedisPoolConfig config) {
        super(urlDataFile, config);
    }

    @Override
    public IAcker buildAcker(SpoutOutputCollector collector, JedisPoolConfig config) {
        AsynchronousAcker acker = new AsynchronousAcker(collector, config);
        new Thread(acker, "ackerThread").start();
        return acker;
    }

}
