
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrawlSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrawlSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CrawlBoostAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CrawlBoostEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CrawlRate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrawlSettings", propOrder = {
    "crawlBoostAvailable",
    "crawlBoostEnabled",
    "crawlRate"
})
public class CrawlSettings {

    @XmlElement(name = "CrawlBoostAvailable")
    protected Boolean crawlBoostAvailable;
    @XmlElement(name = "CrawlBoostEnabled")
    protected Boolean crawlBoostEnabled;
    @XmlElementRef(name = "CrawlRate", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> crawlRate;

    /**
     * Gets the value of the crawlBoostAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCrawlBoostAvailable() {
        return crawlBoostAvailable;
    }

    /**
     * Sets the value of the crawlBoostAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCrawlBoostAvailable(Boolean value) {
        this.crawlBoostAvailable = value;
    }

    /**
     * Gets the value of the crawlBoostEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCrawlBoostEnabled() {
        return crawlBoostEnabled;
    }

    /**
     * Sets the value of the crawlBoostEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCrawlBoostEnabled(Boolean value) {
        this.crawlBoostEnabled = value;
    }

    /**
     * Gets the value of the crawlRate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getCrawlRate() {
        return crawlRate;
    }

    /**
     * Sets the value of the crawlRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setCrawlRate(JAXBElement<byte[]> value) {
        this.crawlRate = value;
    }

}
