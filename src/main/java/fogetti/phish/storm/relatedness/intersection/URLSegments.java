package fogetti.phish.storm.relatedness.intersection;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import fogetti.phish.storm.client.Terms;
import fogetti.phish.storm.relatedness.AckResult;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class URLSegments implements Serializable {

    private static final long serialVersionUID = 6774105562350012171L;
    private static final Logger logger = LoggerFactory.getLogger(URLSegments.class);
    public static final ObjectMapper mapper = new ObjectMapper();
    public final Map<String, Terms> termindex = new ConcurrentHashMap<>();

    public void put(String segment, Terms terms) {
        if (termindex.containsKey(segment)) {
            termindex.get(segment).add(terms.terms);
        } else {
            termindex.put(segment, terms);
        }
    }

    public void removeIf(Predicate<? super Map.Entry<String, Terms>> filter) {
        termindex.entrySet().removeIf(filter);
    }

    public Map<String, Terms> getREMTerms(AckResult result) {
        Map<String, Terms> remTerms = result.getREMTerms(termindex);
        logger.info("REMTerms[{}]", remTerms.values());
        return remTerms;
    }

    public Map<String, Terms> getRDTerms(AckResult result) {
        Map<String, Terms> rdTerms = result.getRDTerms(termindex);
        logger.info("RDTerms[{}]", rdTerms.values());
        return rdTerms;
    }

    public Map<String, Terms> getMLDTerms(AckResult result) {
        Map<String, Terms> mldTerms = result.getMLDTerms(termindex);
        logger.info("MLDTerms[{}]", mldTerms.values());
        return mldTerms;
    }

    public Map<String, Terms> getMLDPSTerms(AckResult result) {
        Map<String, Terms> mldpsTerms = result.getMLDPSTerms(termindex);
        logger.info("MLDPSTerms[{}]", mldpsTerms.values());
        return mldpsTerms;
    }

    public int count() {
        int count = 0;
        for (Terms terms : termindex.values()) {
            count += terms.count();
        }
        return count;
    }

    public Map<String, String> toStringMap() {
        Map<String, String> map
        = termindex.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
        return map;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"termindex\":{");
        StringBuilder result = new StringBuilder();
        termindex.entrySet().stream().forEach(
                e -> {
                    if (result.length() > 0) result.append( ", " );
                    result.append(String.format("\"%s\":", e.getKey()));
                    result.append(e.getValue());
                });
        result.append("}");
        builder.append(result.toString());
        builder.append("}");
        return builder.toString();
    }

    public static URLSegments fromStringMap(Map<String, String> src) throws IOException {
        URLSegments segments = new URLSegments();
        Iterator<Map.Entry<String, String>> it = src.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            segments.put(e.getKey(), mapper.readValue(e.getValue(), Terms.class));
            it.remove();
        }
        return segments;
    }
    
    public static URLSegments fromString(String src) throws IOException {
        URLSegments segments = new URLSegments();
        JsonFactory jfactory = new JsonFactory();
        jfactory.setCodec(mapper);
        JsonParser jParser = jfactory.createParser(src);
        jParser.getCurrentName(); // current token is "null", move next
        jParser.nextToken(); // current token is "{", move next
        while (jParser.nextToken() != JsonToken.END_OBJECT) {
            jParser.getCurrentName(); // current token is "termindex", move next
            jParser.nextToken(); // current token is "{", move next
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                String sgmnt = jParser.getCurrentName(); // current token is segment key
                jParser.nextToken(); // move to next, which is segment key's value
                StringWriter strWrt = new StringWriter();
                JsonGenerator jgen = jfactory.createGenerator(strWrt);
                TokenBuffer buf = jParser.readValueAs(TokenBuffer.class);
                jgen.writeObject(buf);
                jgen.close();
                Terms urlsegments = mapper.readValue(strWrt.toString(), Terms.class);
                segments.put(sgmnt, urlsegments);
            }
        }
        return segments;
    }
}