package fogetti.phish.storm.client;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Term implements Serializable {

    private static final long serialVersionUID = 6726474681499268465L;
    private final ObjectMapper mapper = new ObjectMapper();
    public List<String> words;
    
    public Term() {
        words = new ArrayList<>();
    }

    public Term(String... input) {
        words = Arrays.asList(input);
    }

    public boolean contains(String key) {
        return words.contains(key);
    }
    
    @Override
    public String toString() {
        String msg = "";
        try {
            msg = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            msg = pw.toString();
        }
        return msg;
    }
}