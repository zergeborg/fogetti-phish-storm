
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CrawlStats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrawlStats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AllOtherCodes" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="BlockedByRobotsTxt" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Code2xx" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Code301" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Code302" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Code4xx" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Code5xx" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ConnectionTimeout" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ContainsMalware" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CrawlErrors" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CrawledPages" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DnsFailures" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="InIndex" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="InLinks" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrawlStats", propOrder = {
    "allOtherCodes",
    "blockedByRobotsTxt",
    "code2Xx",
    "code301",
    "code302",
    "code4Xx",
    "code5Xx",
    "connectionTimeout",
    "containsMalware",
    "crawlErrors",
    "crawledPages",
    "date",
    "dnsFailures",
    "inIndex",
    "inLinks"
})
public class CrawlStats {

    @XmlElement(name = "AllOtherCodes")
    protected Long allOtherCodes;
    @XmlElement(name = "BlockedByRobotsTxt")
    protected Long blockedByRobotsTxt;
    @XmlElement(name = "Code2xx")
    protected Long code2Xx;
    @XmlElement(name = "Code301")
    protected Long code301;
    @XmlElement(name = "Code302")
    protected Long code302;
    @XmlElement(name = "Code4xx")
    protected Long code4Xx;
    @XmlElement(name = "Code5xx")
    protected Long code5Xx;
    @XmlElement(name = "ConnectionTimeout")
    protected Long connectionTimeout;
    @XmlElement(name = "ContainsMalware")
    protected Long containsMalware;
    @XmlElement(name = "CrawlErrors")
    protected Long crawlErrors;
    @XmlElement(name = "CrawledPages")
    protected Long crawledPages;
    @XmlElement(name = "Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "DnsFailures")
    protected Long dnsFailures;
    @XmlElement(name = "InIndex")
    protected Long inIndex;
    @XmlElement(name = "InLinks")
    protected Long inLinks;

    /**
     * Gets the value of the allOtherCodes property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAllOtherCodes() {
        return allOtherCodes;
    }

    /**
     * Sets the value of the allOtherCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAllOtherCodes(Long value) {
        this.allOtherCodes = value;
    }

    /**
     * Gets the value of the blockedByRobotsTxt property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBlockedByRobotsTxt() {
        return blockedByRobotsTxt;
    }

    /**
     * Sets the value of the blockedByRobotsTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBlockedByRobotsTxt(Long value) {
        this.blockedByRobotsTxt = value;
    }

    /**
     * Gets the value of the code2Xx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCode2Xx() {
        return code2Xx;
    }

    /**
     * Sets the value of the code2Xx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCode2Xx(Long value) {
        this.code2Xx = value;
    }

    /**
     * Gets the value of the code301 property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCode301() {
        return code301;
    }

    /**
     * Sets the value of the code301 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCode301(Long value) {
        this.code301 = value;
    }

    /**
     * Gets the value of the code302 property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCode302() {
        return code302;
    }

    /**
     * Sets the value of the code302 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCode302(Long value) {
        this.code302 = value;
    }

    /**
     * Gets the value of the code4Xx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCode4Xx() {
        return code4Xx;
    }

    /**
     * Sets the value of the code4Xx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCode4Xx(Long value) {
        this.code4Xx = value;
    }

    /**
     * Gets the value of the code5Xx property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCode5Xx() {
        return code5Xx;
    }

    /**
     * Sets the value of the code5Xx property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCode5Xx(Long value) {
        this.code5Xx = value;
    }

    /**
     * Gets the value of the connectionTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the value of the connectionTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConnectionTimeout(Long value) {
        this.connectionTimeout = value;
    }

    /**
     * Gets the value of the containsMalware property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContainsMalware() {
        return containsMalware;
    }

    /**
     * Sets the value of the containsMalware property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContainsMalware(Long value) {
        this.containsMalware = value;
    }

    /**
     * Gets the value of the crawlErrors property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCrawlErrors() {
        return crawlErrors;
    }

    /**
     * Sets the value of the crawlErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCrawlErrors(Long value) {
        this.crawlErrors = value;
    }

    /**
     * Gets the value of the crawledPages property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCrawledPages() {
        return crawledPages;
    }

    /**
     * Sets the value of the crawledPages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCrawledPages(Long value) {
        this.crawledPages = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the dnsFailures property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDnsFailures() {
        return dnsFailures;
    }

    /**
     * Sets the value of the dnsFailures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDnsFailures(Long value) {
        this.dnsFailures = value;
    }

    /**
     * Gets the value of the inIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInIndex() {
        return inIndex;
    }

    /**
     * Sets the value of the inIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInIndex(Long value) {
        this.inIndex = value;
    }

    /**
     * Gets the value of the inLinks property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInLinks() {
        return inLinks;
    }

    /**
     * Sets the value of the inLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInLinks(Long value) {
        this.inLinks = value;
    }

}
