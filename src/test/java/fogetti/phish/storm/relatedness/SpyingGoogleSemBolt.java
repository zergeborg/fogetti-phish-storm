package fogetti.phish.storm.relatedness;

import java.io.File;
import java.util.Map;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;

import fogetti.phish.storm.client.IRequest;
import redis.clients.jedis.JedisCommands;

public class SpyingGoogleSemBolt extends GoogleSemBolt {

    private static final long serialVersionUID = 3798709600022221909L;
    private final JedisCommands jedis;
    protected OutputCollector collector;

    private SpyingGoogleSemBolt(JedisPoolConfig config, File proxies, IRequest request, JedisCommands jedis) {
        super(config, proxies, request);
        this.jedis = jedis;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        super.prepare(stormConf, context, collector);
        this.collector = collector;
    }
    @Override
    protected JedisCommands getInstance() {
        return jedis;
    }

    @Override
    protected void returnInstance(JedisCommands instance) {
    }
    
    public static class Builder {
        private JedisPoolConfig config;
        private JedisCommands jedis;
        private File proxies;
        private IRequest request;
        
        public Builder setConfig(JedisPoolConfig config) {
            this.config = config;
            return this;
        }

        public Builder setJedis(JedisCommands jedis) {
            this.jedis = jedis;
            return this;
        }

        public Builder setProxies(File proxies) {
            this.proxies = proxies;
            return this;
        }

        public Builder setRequest(IRequest request) {
            this.request = request;
            return this;
        }
        
        public SpyingGoogleSemBolt build() {
            return new SpyingGoogleSemBolt(this.config, this.proxies, this.request, this.jedis);
        }
    }
}