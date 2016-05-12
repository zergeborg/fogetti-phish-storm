package fogetti.phish.storm.relatedness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.junit.Before;
import org.junit.Test;

public class MatcherBoltTest {

    private ClassLoader loader = this.getClass().getClassLoader();
    private String countDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/1gram-count.txt";
    private String psDataFile = "/Users/gergely.nagy/Work/git/fogetti-phish-storm/src/main/resources/public-suffix-list.dat";

    private class TestDoubleMatcherBolt extends MatcherBolt {

        private static final long serialVersionUID = -7748829151740848266L;

        public TestDoubleMatcherBolt(String countDataFile, String psDataFile) {
            super(countDataFile, psDataFile, mock(JedisPoolConfig.class));
        }

    }

    @Before
    public void setup() throws Exception {
        countDataFile = new File(loader.getResource("1gram-count.txt").toURI()).getAbsolutePath();
        psDataFile = new File(loader.getResource("public-suffix-list.dat").toURI()).getAbsolutePath();
    }

    @Test
    public void segmentOne() throws Exception {
        // Given we want to segment a word before calculating relatedness
        MatcherBolt bolt = new TestDoubleMatcherBolt(countDataFile, psDataFile);
        bolt.prepare(null, null, null);

        // When we submit "itwasabrightcolddayinaprilandtheclockswerestrikingthirteen"
        List<String> segment = bolt.segment("itwasabrightcolddayinaprilandtheclockswerestrikingthirteen");

        // Then it returns [it, was, a, bright, cold, day, in, april, and, the, clocks, were, striking, thirteen]
        System.out.println(segment);
        List<String> expected = Arrays.asList(new String[]{"it", "was", "a", "bright", "cold", "day", "in", "april", "and", "the", "clocks", "were", "striking", "thirteen"});
        assertEquals("The segmentation was wrong", expected.toString(), segment.toString());
    }

    @Test
    public void segmentTwo() throws Exception {
        // Given we want to segment a word before calculating relatedness
        MatcherBolt bolt = new TestDoubleMatcherBolt(countDataFile, psDataFile);
        bolt.prepare(null, null, null);

        // When we submit "inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort"
        List<String> segment = bolt.segment("inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort");

        // Then it returns [in, a, hole, in, the, ground, there, lived, a, hobbit, not, a, nasty, dirty, wet, hole, filled, with, the, ends, of, worms, and, an, oozy, smell, nor, yet, a, dry, bare, sandy, hole, with, nothing, in, it, to, sitdown, on, or, to, eat, it, was, a, hobbit, hole, and, that, means, comfort]
        System.out.println(segment);
        List<String> expected = Arrays.asList(new String[]{"in", "a", "hole", "in", "the", "ground", "there", "lived", "a", "hobbit", "not", "a", "nasty", "dirty", "wet", "hole", "filled", "with", "the", "ends", "of", "worms", "and", "an", "oozy", "smell", "nor", "yet", "a", "dry", "bare", "sandy", "hole", "with", "nothing", "in", "it", "to", "sitdown", "on", "or", "to", "eat", "it", "was", "a", "hobbit", "hole", "and", "that", "means", "comfort"});
        assertEquals("The segmentation was wrong", expected.toString(), segment.toString());
    }

}