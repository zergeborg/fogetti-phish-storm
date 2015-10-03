
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for UrlInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UrlInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnchorCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DiscoveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DocumentSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="HttpStatus" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *         &lt;element name="IsPage" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LastCrawledDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TotalChildUrlCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UrlInfo", propOrder = {
    "anchorCount",
    "discoveryDate",
    "documentSize",
    "httpStatus",
    "isPage",
    "lastCrawledDate",
    "totalChildUrlCount",
    "url"
})
public class UrlInfo {

    @XmlElement(name = "AnchorCount")
    protected Long anchorCount;
    @XmlElement(name = "DiscoveryDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar discoveryDate;
    @XmlElement(name = "DocumentSize")
    @XmlSchemaType(name = "unsignedInt")
    protected Long documentSize;
    @XmlElement(name = "HttpStatus")
    @XmlSchemaType(name = "unsignedShort")
    protected Integer httpStatus;
    @XmlElement(name = "IsPage")
    protected Boolean isPage;
    @XmlElement(name = "LastCrawledDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastCrawledDate;
    @XmlElement(name = "TotalChildUrlCount")
    @XmlSchemaType(name = "unsignedInt")
    protected Long totalChildUrlCount;
    @XmlElementRef(name = "Url", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> url;

    /**
     * Gets the value of the anchorCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAnchorCount() {
        return anchorCount;
    }

    /**
     * Sets the value of the anchorCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAnchorCount(Long value) {
        this.anchorCount = value;
    }

    /**
     * Gets the value of the discoveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDiscoveryDate() {
        return discoveryDate;
    }

    /**
     * Sets the value of the discoveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDiscoveryDate(XMLGregorianCalendar value) {
        this.discoveryDate = value;
    }

    /**
     * Gets the value of the documentSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDocumentSize() {
        return documentSize;
    }

    /**
     * Sets the value of the documentSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDocumentSize(Long value) {
        this.documentSize = value;
    }

    /**
     * Gets the value of the httpStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /**
     * Sets the value of the httpStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHttpStatus(Integer value) {
        this.httpStatus = value;
    }

    /**
     * Gets the value of the isPage property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPage() {
        return isPage;
    }

    /**
     * Sets the value of the isPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPage(Boolean value) {
        this.isPage = value;
    }

    /**
     * Gets the value of the lastCrawledDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastCrawledDate() {
        return lastCrawledDate;
    }

    /**
     * Sets the value of the lastCrawledDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastCrawledDate(XMLGregorianCalendar value) {
        this.lastCrawledDate = value;
    }

    /**
     * Gets the value of the totalChildUrlCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalChildUrlCount() {
        return totalChildUrlCount;
    }

    /**
     * Sets the value of the totalChildUrlCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalChildUrlCount(Long value) {
        this.totalChildUrlCount = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUrl(JAXBElement<String> value) {
        this.url = value;
    }

}
