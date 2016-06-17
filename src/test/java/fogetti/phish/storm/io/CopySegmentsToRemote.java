package fogetti.phish.storm.io;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import fogetti.phish.storm.client.Terms;
import redis.clients.jedis.Jedis;

public class CopySegmentsToRemote {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (Jedis jedis = new Jedis("localhost");
                Jedis remote = new Jedis("remote", 6379)) {
            remote.auth("auth");
            jedis.select(1);
            remote.select(1);
            
            Set<String> keys = jedis.keys("segment:*");
            for (String key : keys) {
                String segments = jedis.get(key);
                Terms terms = mapper.readValue(segments, Terms.class);
                if (terms != null && terms.terms != null && !terms.terms.isEmpty()) {
                    System.out.println(String.format("Moving %s to remote", key));
                    String termString = mapper.writeValueAsString(terms);
                    remote.set(key, termString);
                }
            }
        }
    }
    
}