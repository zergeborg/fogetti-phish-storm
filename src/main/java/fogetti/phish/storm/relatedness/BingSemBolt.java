package fogetti.phish.storm.relatedness;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import fogetti.phish.storm.wsdl.bing.webmaster.ArrayOfKeyword;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApi;
import fogetti.phish.storm.wsdl.bing.webmaster.IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage;
import fogetti.phish.storm.wsdl.bing.webmaster.Keyword;
import fogetti.phish.storm.wsdl.bing.webmaster.WebmasterApi;

public class BingSemBolt extends BaseRichBolt {

	private static final long serialVersionUID = -5744889615761052666L;
	private static final Logger logger = LoggerFactory.getLogger(BingSemBolt.class);
	private OutputCollector collector;
	private transient IWebmasterApi api;
	
	public BingSemBolt() {
		this.api = getApi();
	}

	public BingSemBolt(IWebmasterApi api) {
		this.api = api;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.api = getApi();
	}

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector, IWebmasterApi api) {
		this.collector = collector;
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
			start.set(Calendar.DAY_OF_YEAR, -60);
			XMLGregorianCalendar startDate = factory.newXMLGregorianCalendar(start);
			GregorianCalendar end = new GregorianCalendar();
			XMLGregorianCalendar endDate = factory.newXMLGregorianCalendar(end);
			execute(input, startDate, endDate);
		} catch (DatatypeConfigurationException e) {
			logger.error("Bing Keyword Stats request failed", e);
		}
	}

	public void execute(Tuple input, XMLGregorianCalendar startDate, XMLGregorianCalendar endDate) {
		try {
			String keyword = input.getString(0);
			ArrayOfKeyword relatedKeywords = api.getRelatedKeywords(keyword, "", "", startDate , endDate);
			collector.emit(input, new Values(calculateSearches(relatedKeywords)));
			collector.ack(input);
			logger.trace("Result [{}]", relatedKeywords);
		} catch (IWebmasterApiGetRelatedKeywordsApiFaultFaultFaultMessage e) {
			logger.error("Bing Keyword Stats request failed", e);
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
		declarer.declare(new Fields("bingrelatedkeywords"));
	}

}
