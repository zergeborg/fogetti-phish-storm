
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
 * <p>Java class for PagePreview complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagePreview">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Action" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts}PagePreviewAction" minOccurs="0"/>
 *         &lt;element name="BlockReason" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts}BlockReason" minOccurs="0"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RefreshReason" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts}RefreshReason" minOccurs="0"/>
 *         &lt;element name="SiteUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubmitDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagePreview", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", propOrder = {
    "action",
    "blockReason",
    "reason",
    "refreshReason",
    "siteUrl",
    "submitDate",
    "url",
    "userId"
})
public class PagePreview {

    @XmlElement(name = "Action")
    @XmlSchemaType(name = "string")
    protected PagePreviewAction action;
    @XmlElement(name = "BlockReason")
    @XmlSchemaType(name = "string")
    protected BlockReason blockReason;
    @XmlElementRef(name = "Reason", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reason;
    @XmlElement(name = "RefreshReason")
    @XmlSchemaType(name = "string")
    protected RefreshReason refreshReason;
    @XmlElementRef(name = "SiteUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> siteUrl;
    @XmlElement(name = "SubmitDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitDate;
    @XmlElementRef(name = "Url", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> url;
    @XmlElementRef(name = "UserId", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userId;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link PagePreviewAction }
     *     
     */
    public PagePreviewAction getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagePreviewAction }
     *     
     */
    public void setAction(PagePreviewAction value) {
        this.action = value;
    }

    /**
     * Gets the value of the blockReason property.
     * 
     * @return
     *     possible object is
     *     {@link BlockReason }
     *     
     */
    public BlockReason getBlockReason() {
        return blockReason;
    }

    /**
     * Sets the value of the blockReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockReason }
     *     
     */
    public void setBlockReason(BlockReason value) {
        this.blockReason = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReason(JAXBElement<String> value) {
        this.reason = value;
    }

    /**
     * Gets the value of the refreshReason property.
     * 
     * @return
     *     possible object is
     *     {@link RefreshReason }
     *     
     */
    public RefreshReason getRefreshReason() {
        return refreshReason;
    }

    /**
     * Sets the value of the refreshReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefreshReason }
     *     
     */
    public void setRefreshReason(RefreshReason value) {
        this.refreshReason = value;
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

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserId(JAXBElement<String> value) {
        this.userId = value;
    }

}
