package fogetti.phish.storm.client;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Terms implements Serializable {

    private static final long serialVersionUID = -3472432490997379521L;    
    private final ObjectMapper mapper = new ObjectMapper();
    public final List<Term> terms = new ArrayList<>();
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Integer retryCnt = 0;
    
    public Terms() {}
    
    public Terms(Term... term) {
        terms.addAll(Arrays.asList(term));
    }
    
    public void add(Term input) {
        terms.add(input);
    }
    
    public void add(Collection<Term> input) {
        terms.addAll(input);
    }
    
    public int count() {
        Set<String> words = new HashSet<>();
        for (Term term : terms) {
            for (String word : term.words) {
                words.add(word);
            }
        }
        return words.size();
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