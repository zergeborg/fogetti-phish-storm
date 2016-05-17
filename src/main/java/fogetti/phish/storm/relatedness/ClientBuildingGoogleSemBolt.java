package fogetti.phish.storm.relatedness;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.storm.redis.common.config.JedisPoolConfig;

import fogetti.phish.storm.client.IRequest;
import okhttp3.OkHttpClient;

public class ClientBuildingGoogleSemBolt extends GoogleSemBolt {

    private static final long serialVersionUID = -8280832599330302218L;

    public ClientBuildingGoogleSemBolt(JedisPoolConfig config, File proxies, IRequest request) {
        super(config, proxies, request);
    }

    @Override
    public OkHttpClient buildClient() {
        OkHttpClient client
            = new OkHttpClient
                .Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

}
