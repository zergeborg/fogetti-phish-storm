
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterProperties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterProperties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CrawlDateFilter" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}CrawlDateFilter" minOccurs="0"/>
 *         &lt;element name="DiscoveredDateFilter" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}DiscoveredDateFilter" minOccurs="0"/>
 *         &lt;element name="DocFlagsFilters" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}DocFlagsFilters" minOccurs="0"/>
 *         &lt;element name="HttpCodeFilters" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}HttpCodeFilters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterProperties", propOrder = {
    "crawlDateFilter",
    "discoveredDateFilter",
    "docFlagsFilters",
    "httpCodeFilters"
})
public class FilterProperties {

    @XmlElement(name = "CrawlDateFilter")
    @XmlSchemaType(name = "string")
    protected CrawlDateFilter crawlDateFilter;
    @XmlElement(name = "DiscoveredDateFilter")
    @XmlSchemaType(name = "string")
    protected DiscoveredDateFilter discoveredDateFilter;
    @XmlList
    @XmlElement(name = "DocFlagsFilters")
    @XmlSchemaType(name = "anySimpleType")
    protected List<String> docFlagsFilters;
    @XmlList
    @XmlElement(name = "HttpCodeFilters")
    @XmlSchemaType(name = "anySimpleType")
    protected List<String> httpCodeFilters;

    /**
     * Gets the value of the crawlDateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link CrawlDateFilter }
     *     
     */
    public CrawlDateFilter getCrawlDateFilter() {
        return crawlDateFilter;
    }

    /**
     * Sets the value of the crawlDateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrawlDateFilter }
     *     
     */
    public void setCrawlDateFilter(CrawlDateFilter value) {
        this.crawlDateFilter = value;
    }

    /**
     * Gets the value of the discoveredDateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link DiscoveredDateFilter }
     *     
     */
    public DiscoveredDateFilter getDiscoveredDateFilter() {
        return discoveredDateFilter;
    }

    /**
     * Sets the value of the discoveredDateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscoveredDateFilter }
     *     
     */
    public void setDiscoveredDateFilter(DiscoveredDateFilter value) {
        this.discoveredDateFilter = value;
    }

    /**
     * Gets the value of the docFlagsFilters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docFlagsFilters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocFlagsFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDocFlagsFilters() {
        if (docFlagsFilters == null) {
            docFlagsFilters = new ArrayList<String>();
        }
        return this.docFlagsFilters;
    }

    /**
     * Gets the value of the httpCodeFilters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the httpCodeFilters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHttpCodeFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHttpCodeFilters() {
        if (httpCodeFilters == null) {
            httpCodeFilters = new ArrayList<String>();
        }
        return this.httpCodeFilters;
    }

}
