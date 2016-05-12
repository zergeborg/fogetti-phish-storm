package fogetti.phish.storm.relatedness;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.container.JedisCommandsContainerBuilder;
import org.apache.storm.redis.common.container.JedisCommandsInstanceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import fogetti.phish.storm.db.PublishMessage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

/**
 * Implementation of the following real time phishing classifier:
 * <a href="https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf">
 * https://orbilu.uni.lu/bitstream/10993/20053/1/phishStorm-revised.pdf
 * </a>
 * @author gergely.nagy
 *
 */
public class URLSpout extends BaseRichSpout {

	private static final long serialVersionUID = -6424905468176142975L;
	private static final Logger logger = LoggerFactory.getLogger(URLSpout.class);

	private SpoutOutputCollector collector;
	private List<String> urllist;
	private ListIterator<String> iterator;
	private String urlDataFile;
	private JedisPoolConfig config;
	private JedisCommandsInstanceContainer container;
    private ObjectMapper mapper = new ObjectMapper();

	public URLSpout(String urlDataFile, JedisPoolConfig config) {
		this.urlDataFile = urlDataFile;
		this.config = config;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.urllist = readURLListFromFile();
		this.iterator = urllist.listIterator();
		this.container = JedisCommandsContainerBuilder.build(config);
	}

	private List<String> readURLListFromFile() {
		List<String> urls = new ArrayList<>();
		loadUrls(urls);
		return urls;
	}

	private void loadUrls(List<String> urls) {
		try(Scanner scanner = new Scanner(new FileReader(urlDataFile));) {
			while (scanner.hasNextLine()) {
				urls.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void nextTuple() {
		// Check the case when we read first seen elements
		if (iterator.hasNext()) {
			String URLWithScheme = iterator.next();
			doNextTuple(URLWithScheme);
		}
		// Check the case when we read failed elements
		else if (iterator.hasPrevious()) {
			String URLWithScheme = iterator.previous();
			doNextTuple(URLWithScheme);
		}
	}

	private void doNextTuple(String URLWithScheme) {
		collector.emit(new Values(URLWithScheme), URLWithScheme);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));
	}

	@Override
	public void ack(Object msgId) {
        Jedis jedis = null;
        AckResult result = null;
        try {
            jedis = (Jedis) getInstance();
            List<String> messages = jedis.blpop(0, new String[]{"acked:"+msgId.toString()});
            result = mapper.readValue(messages.get(1), AckResult.class);
        } catch (IOException e) {
            logger.error("Could not look up AckResult related to "+msgId.toString(), e);
            collector.reportError(e);
            return;
        } finally {
            returnInstance(jedis);
        }
        try {
            String msg = mapper.writeValueAsString(result);
            publish("phish", msg);
        } catch (JsonProcessingException e) {
            logger.error("Could not send acknowledgment to the intersection bolt", e);
            collector.reportError(e);
        }
	}

	private void publish(String channel, String msg) {
		Jedis jedis = null;
		try {
			jedis = (Jedis) getInstance();
			PublishMessage message = new PublishMessage(channel, msg);
			logger.info("Publishing [Message={}]", message.msg);
			jedis.rpush(message.channel, message.msg);
		} finally {
			returnInstance(jedis);
		}
	}

	@Override
	public void fail(Object msgId) {
		logger.debug("Message [msg={}] failed", msgId);
		logger.warn("Requeueing [msg={}]", msgId);
		iterator.add(msgId.toString());
	}

	/**
	 * Borrow JedisCommands instance from container.<p></p>
	 * JedisCommands is an interface which contains single key operations.
	 * @return implementation of JedisCommands
	 * @see JedisCommandsInstanceContainer#getInstance()
	 */
	protected JedisCommands getInstance() {
		return this.container.getInstance();
	}

	/**
	 * Return borrowed instance to container.
	 * @param instance borrowed object
	 */
	protected void returnInstance(JedisCommands instance) {
		this.container.returnInstance(instance);
	}
}
