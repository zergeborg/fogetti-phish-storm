package fogetti.phish.storm.relatedness;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.storm.redis.bolt.AbstractRedisBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import fogetti.phish.storm.wsdl.bing.webmaster.ArrayOfKeyword;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApi;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage;
import fogetti.phish.storm.wsdl.bing.webmaster.Keyword;
import fogetti.phish.storm.wsdl.bing.webmaster.WebmasterApi;
import redis.clients.jedis.JedisCommands;

public class BingSemBolt extends AbstractRedisBolt {

	private static final long serialVersionUID = -5744889615761052666L;
	private static final Logger logger = LoggerFactory.getLogger(BingSemBolt.class);
	private transient IWebmasterApi api;
	
	public BingSemBolt(JedisPoolConfig config) {
		super(config);
		this.api = getApi();
	}

	public BingSemBolt(IWebmasterApi api, JedisPoolConfig config) {
		super(config);
		this.api = api;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		this.api = getApi();
	}

	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector, IWebmasterApi api) {
		super.prepare(stormConf, context, collector);
		this.api = api;
	}

	private IWebmasterApi getApi() {
		WebmasterApi apifactory = new WebmasterApi();
		return apifactory.getBasicHttpBindingIWebmasterApi();
	}

	@Override
	public void execute(Tuple input) {
		try {
			DatatypeFactory factory = DatatypeFactory.newInstance();
			GregorianCalendar start = new GregorianCalendar();
			start.set(Calendar.DAY_OF_YEAR, -180);
			XMLGregorianCalendar startDate = factory.newXMLGregorianCalendar(start);
			GregorianCalendar end = new GregorianCalendar();
			XMLGregorianCalendar endDate = factory.newXMLGregorianCalendar(end);
			execute(input, startDate, endDate);
		} catch (DatatypeConfigurationException e) {
			logger.error("Bing Keyword Stats request failed", e);
		}
	}

	public void execute(Tuple input, XMLGregorianCalendar startDate, XMLGregorianCalendar endDate) {
		JedisCommands jedisCommand = null;
		try {
			TimeUnit.MILLISECONDS.sleep(500);
			String segment = input.getStringByField("segment");
			String url = input.getStringByField("url");
			
	        jedisCommand = getInstance();
            Set<String> lookupValue = jedisCommand.smembers(segment);
            if (lookupValue == null || lookupValue.isEmpty()) {
            	logger.debug("Cached Bing result not found for [segment={}]", segment);
            	ArrayOfKeyword relatedKeywords = api.getRelatedKeywords(segment, "", "", startDate , endDate);
            	lookupValue = calculateSearches(relatedKeywords);
            	logger.trace("Result [{}]", relatedKeywords);
            } else {
            	logger.debug("Found cached Bing result found for [segment={}]", segment);
            }
			collector.emit(input, new Values(new HashSet<>(lookupValue), segment, url));
			collector.ack(input);
		} catch (IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage e) {
			logger.error("Bing Keyword Stats request failed", e);
		} catch (InterruptedException e) {
			logger.warn("Interrupted while sleeping");
			Thread.currentThread().interrupt();
		} finally {
			returnInstance(jedisCommand);
		}
	}
	
	private Set<String> calculateSearches(ArrayOfKeyword relatedKeywords) {
		HashSet<String> result = new HashSet<>();
		List<Keyword> keywords = relatedKeywords.getKeyword();
		for (Keyword keyword : keywords) {
			result.add(keyword.getQuery().getValue());
		}
		return result;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("bingrelatedkeywords", "segment", "url"));
	}

}
