package fogetti.phish.storm.relatedness;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.task.TopologyContext;
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
        bolt.prepare(null, mock(TopologyContext.class), null);

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
        bolt.prepare(null, mock(TopologyContext.class), null);

        // When we submit "inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort"
        List<String> segment = bolt.segment("inaholeinthegroundtherelivedahobbitnotanastydirtywetholefilledwiththeendsofwormsandanoozysmellnoryetadrybaresandyholewithnothinginittositdownonortoeatitwasahobbitholeandthatmeanscomfort");

        // Then it returns [in, a, hole, in, the, ground, there, lived, a, hobbit, not, a, nasty, dirty, wet, hole, filled, with, the, ends, of, worms, and, an, oozy, smell, nor, yet, a, dry, bare, sandy, hole, with, nothing, in, it, to, sitdown, on, or, to, eat, it, was, a, hobbit, hole, and, that, means, comfort]
        System.out.println(segment);
        List<String> expected = Arrays.asList(new String[]{"in", "a", "hole", "in", "the", "ground", "there", "lived", "a", "hobbit", "not", "a", "nasty", "dirty", "wet", "hole", "filled", "with", "the", "ends", "of", "worms", "and", "an", "oozy", "smell", "nor", "yet", "a", "dry", "bare", "sandy", "hole", "with", "nothing", "in", "it", "to", "sitdown", "on", "or", "to", "eat", "it", "was", "a", "hobbit", "hole", "and", "that", "means", "comfort"});
        assertEquals("The segmentation was wrong", expected.toString(), segment.toString());
    }
    
    @Test
    public void calculateLongURL() throws Exception {
        // Given
        MatcherBolt bolt = new TestDoubleMatcherBolt(countDataFile, psDataFile);
        bolt.prepare(null, mock(TopologyContext.class), null);
        String schemedUrl = "http://nmkvestnes.no/libraries/phputf8/login.do2562.html?fwylg4sdksgipbnwbuz2nllxbikdr4tna74r4h7t7jojg4o5nksy5ojsej4digvoxdsdghxu8ej7vqnzbwynwu5zs8e8vad8wbsmopuyfbjb7dsydqywowmsfjktymrjsjg1g7f5fexir8dwobl5pb75salilwd1pwuhzyoee353b3ycene3jymdmhcvaeh7h4cbd1ifnxyqtpayworqua5dtxgo48cpz3yzs3pfogpdd3suzffdnr4ocxjpznhahmaje5oxwak38i1aocw5rwcuoc2jy1dz7s2jmvuzcv2ftdn4lua85hr7xa1xdfqicbzzoy3dxrsbua8uw14vg3oiusskvurin2sytdfy4qm18xyyfrbsbs5rhpzad7fsdg4m557vs5ftu41fnr8wpczurvv2v22cfmfuwa2x47oodsz8vwo35cmnxnek4zp77sracszgyrgzttwn8sx3dvxwwkdburyabkcd37zdu7xj7iwm45e1yqtaqdk8dtvhic4mxddj88wkxob2vlsa22drfdb47hyascnf4s5vketbzsmsxqbnqcokbsbt3bkdgbhzpxskfqf111c5sni4pvumbiic54pssfujuj5vkdx7ccuf42357nqomawxdz2evbwsg5fz7572otpaqbiw3n7x822dq7ed4duqkaygwr4drduxzeijz7l5pg1oajbusvlhvf2pkujmrjecsp7udm7v7rufq7zs5uihxer1wbtlds4sl7xp2sjyut7gnbvr2oikdsrnsl4yw8q4tn3sbryysa3lejjkxgfyuca4m5jggh42yvt3f25pmkc77kuiefsdefiv5qq8ysmqeipalk1vf3awlsg4fvddjfrtqt38ru1x7smf3vgrs2l74i2nuzlaivd1kf5jic1tclr1fmuvxt24stqwph27sqabh4dvxh7ro21jkkarkrfudzw7qal2eto1zlyqudfqft2eshjiiouvicsxbthjhpkjsmmhgthcnd4ucm2ndyhngd4trymggwcrf7sukkacbndqdwax3jmlsdswcqwd5sbh5nvmyeomlzlv3jyk2sq77pnucsfbhodgenpy7vbwnqj8r55qjdbr18amkkf8n818n2sm51b8l384ylax2it38mubgidze38ktp3wc1yqpufcg524y1an1of8hsamgzycz1dxx2c4prl1btnbs8mtmq8qn7lqqmtudz5idbtqjnxzzxhwxmz84di4uwmi4bsev2l731vzh4uedti4gefey3plks3iowxjfmwf38mduplg28jlnrellozxjrzgddcwdwqnevoucdypzsmhcz18eprmdslwrzl47jgbumpl74wahacks23fjrtrk1pdhgsqbp5cfinwysnkym1obckrpfnzcd3mjlkhlnfcradbdr5sxwh1hq8ax1mbd5y2gfmf2i3ef7pzkqz4zdstghmkdqadx3ja43s8voxai42dstg2eclibmqrfgiz18kndvr4wvpelqpxobxdooiwj4pq3ldfms5cm77fc3x5sw35f4rld14gjb42dhanmrk8bdngnfghczgvwrgwtanw37dn4hyrdxxdd42zdedc24yjbnjjfr3t4egwylbdxwpa2wpvsgcekaxnc5lr2kc8drv24piyn4azp3spagzmisblezst547sgd5k4sdpx5p1ucozra3bmwoqeigm5fhi1o8k1hlhmyb3n2kgx3y5lwu822l83cimnst8mkgqsrqeqevrebtyvftbwr71zgekshdalitskb8idw4prf2cpibds4xtxpshwr77zap1kozvxs1gkd4se4o5rpdvskw53drxhcf5dav2hdthm8f1bd5dhvd4rfuspk2klnsxb5fa8soxirmwcwlnvqsgidndl2ufgjqedjmlqllm2cypkvhycku3v4xxoddep5ek54ajpj1rwdt2nrdztsmq4cxjfi7n1su3uxwsom2huewskcjxwacfz2cn7fmcsq3mmm4ic7jkvxsh2l3daambj2r2r1ko1bjislol71uesvz5zds2xdhgswddzzvobme1dweehdd2j77sbmwislbslgxsim3ud4kkqeyfe7bs23wohgndfmwadkh5hfu2sazpv4vly7y45vlpvvxfw4vmstjmdd4n58inqc8r8smx2td8pwgipt";
        
        // When
        System.out.println(String.format("Calculating relatedness for [%s]", schemedUrl));
        String[] urlParts = schemedUrl.split("//");
        String URL = urlParts[1];
        bolt.createNewAckResult();
        bolt.calculateREMurl(URL);
        
        // Then
    }

}