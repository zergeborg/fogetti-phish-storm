
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
 * <p>Java class for DeepLinkBlock complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeepLinkBlock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeepLinkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Market" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SiteUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubmitDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeepLinkBlock", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", propOrder = {
    "deepLinkUrl",
    "expiryDate",
    "market",
    "searchUrl",
    "siteUrl",
    "submitDate"
})
public class DeepLinkBlock {

    @XmlElementRef(name = "DeepLinkUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> deepLinkUrl;
    @XmlElement(name = "ExpiryDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryDate;
    @XmlElementRef(name = "Market", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> market;
    @XmlElementRef(name = "SearchUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> searchUrl;
    @XmlElementRef(name = "SiteUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> siteUrl;
    @XmlElement(name = "SubmitDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitDate;

    /**
     * Gets the value of the deepLinkUrl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeepLinkUrl() {
        return deepLinkUrl;
    }

    /**
     * Sets the value of the deepLinkUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeepLinkUrl(JAXBElement<String> value) {
        this.deepLinkUrl = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the market property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarket() {
        return market;
    }

    /**
     * Sets the value of the market property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarket(JAXBElement<String> value) {
        this.market = value;
    }

    /**
     * Gets the value of the searchUrl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSearchUrl() {
        return searchUrl;
    }

    /**
     * Sets the value of the searchUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSearchUrl(JAXBElement<String> value) {
        this.searchUrl = value;
    }

    /**
     * Gets the value of the siteUrl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSiteUrl() {
        return siteUrl;
    }

    /**
     * Sets the value of the siteUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSiteUrl(JAXBElement<String> value) {
        this.siteUrl = value;
    }

    /**
     * Gets the value of the submitDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmitDate() {
        return submitDate;
    }

    /**
     * Sets the value of the submitDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmitDate(XMLGregorianCalendar value) {
        this.submitDate = value;
    }

}
