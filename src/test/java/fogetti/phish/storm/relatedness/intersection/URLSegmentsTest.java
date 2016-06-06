package fogetti.phish.storm.relatedness.intersection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import fogetti.phish.storm.client.Term;
import fogetti.phish.storm.client.Terms;

public class URLSegmentsTest {

    @Test
    public void convertToStringMap() throws Exception {
        // Given
        Terms almaTerms = new Terms();
        almaTerms.add(new Term("alma"));
        Terms korteTerms = new Terms();
        korteTerms.add(new Term("korte"));
        URLSegments seg = new URLSegments();
        seg.put("alma", almaTerms);
        seg.put("korte", korteTerms);
        
        // When
        Map<String, String> map = seg.toStringMap();
        System.out.println(map);
        
        // Then
        assertTrue("The map didn't contain the term 'alma'", map.get("alma").contains("alma"));
        assertTrue("The map didn't contain the term 'korte'", map.get("korte").contains("korte"));
    }
    
    @Test
    public void readFromString() throws Exception {
        // Given
        Terms almaTerms = new Terms();
        almaTerms.add(new Term("alma", "barack"));
        almaTerms.add(new Term("eggy", "ketto"));
        Terms korteTerms = new Terms();
        korteTerms.add(new Term("korte", "meggy"));
        URLSegments seg = new URLSegments();
        seg.put("alma", almaTerms);
        seg.put("korte", korteTerms);
        String strMap = seg.toString();

        // When
        URLSegments newSeg = URLSegments.fromString(strMap);
        
        // Then
        assertEquals("The segments count was incorrect", 6, newSeg.count());
    }
    
    @Test
    public void writeAsString() throws Exception {
        // Given
        Terms almaTerms = new Terms();
        almaTerms.add(new Term("alma", "barack"));
        almaTerms.add(new Term("eggy", "ketto"));
        Terms korteTerms = new Terms();
        korteTerms.add(new Term("korte", "meggy"));
        URLSegments seg = new URLSegments();
        seg.put("alma", almaTerms);
        seg.put("korte", korteTerms);
        
        // When
        String strMap = seg.toString();
        System.out.println(strMap);
        
        // Then
        assertTrue("The map didn't contain the term 'alma'", strMap.contains("alma"));
        assertTrue("The map didn't contain the term 'korte'", strMap.contains("korte"));
    }
    
}