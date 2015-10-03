
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
 * <p>Java class for ConnectedSite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectedSite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActualMasterSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AppId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AppName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsecutiveFailedAttempts" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="FailureCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="FirstSuccessfullyVerified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="HttpStatusCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IsBlocked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsBlockedLastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IsDeepLaunchSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsDeepLaunchSupportedLastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IsVerified" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LastSuccessfullyVerified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Market" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestedMasterSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VerificationMethod" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.DataContracts}SiteVerificationMethod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectedSite", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", propOrder = {
    "actualMasterSite",
    "appId",
    "appName",
    "consecutiveFailedAttempts",
    "failureCode",
    "firstSuccessfullyVerified",
    "httpStatusCode",
    "isBlocked",
    "isBlockedLastModified",
    "isDeepLaunchSupported",
    "isDeepLaunchSupportedLastModified",
    "isVerified",
    "lastSuccessfullyVerified",
    "market",
    "requestedMasterSite",
    "updateTime",
    "url",
    "verificationMethod"
})
public class ConnectedSite {

    @XmlElementRef(name = "ActualMasterSite", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actualMasterSite;
    @XmlElementRef(name = "AppId", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> appId;
    @XmlElementRef(name = "AppName", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> appName;
    @XmlElement(name = "ConsecutiveFailedAttempts")
    protected Integer consecutiveFailedAttempts;
    @XmlElement(name = "FailureCode")
    protected Integer failureCode;
    @XmlElement(name = "FirstSuccessfullyVerified")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstSuccessfullyVerified;
    @XmlElement(name = "HttpStatusCode")
    protected Integer httpStatusCode;
    @XmlElementRef(name = "IsBlocked", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> isBlocked;
    @XmlElementRef(name = "IsBlockedLastModified", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> isBlockedLastModified;
    @XmlElementRef(name = "IsDeepLaunchSupported", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> isDeepLaunchSupported;
    @XmlElementRef(name = "IsDeepLaunchSupportedLastModified", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> isDeepLaunchSupportedLastModified;
    @XmlElement(name = "IsVerified")
    protected Boolean isVerified;
    @XmlElement(name = "LastSuccessfullyVerified")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSuccessfullyVerified;
    @XmlElementRef(name = "Market", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> market;
    @XmlElementRef(name = "RequestedMasterSite", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> requestedMasterSite;
    @XmlElement(name = "UpdateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    @XmlElementRef(name = "Url", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> url;
    @XmlElement(name = "VerificationMethod")
    @XmlSchemaType(name = "string")
    protected SiteVerificationMethod verificationMethod;

    /**
     * Gets the value of the actualMasterSite property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActualMasterSite() {
        return actualMasterSite;
    }

    /**
     * Sets the value of the actualMasterSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActualMasterSite(JAXBElement<String> value) {
        this.actualMasterSite = value;
    }

    /**
     * Gets the value of the appId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAppId() {
        return appId;
    }

    /**
     * Sets the value of the appId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAppId(JAXBElement<String> value) {
        this.appId = value;
    }

    /**
     * Gets the value of the appName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAppName() {
        return appName;
    }

    /**
     * Sets the value of the appName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAppName(JAXBElement<String> value) {
        this.appName = value;
    }

    /**
     * Gets the value of the consecutiveFailedAttempts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConsecutiveFailedAttempts() {
        return consecutiveFailedAttempts;
    }

    /**
     * Sets the value of the consecutiveFailedAttempts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConsecutiveFailedAttempts(Integer value) {
        this.consecutiveFailedAttempts = value;
    }

    /**
     * Gets the value of the failureCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFailureCode() {
        return failureCode;
    }

    /**
     * Sets the value of the failureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFailureCode(Integer value) {
        this.failureCode = value;
    }

    /**
     * Gets the value of the firstSuccessfullyVerified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstSuccessfullyVerified() {
        return firstSuccessfullyVerified;
    }

    /**
     * Sets the value of the firstSuccessfullyVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstSuccessfullyVerified(XMLGregorianCalendar value) {
        this.firstSuccessfullyVerified = value;
    }

    /**
     * Gets the value of the httpStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Sets the value of the httpStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHttpStatusCode(Integer value) {
        this.httpStatusCode = value;
    }

    /**
     * Gets the value of the isBlocked property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getIsBlocked() {
        return isBlocked;
    }

    /**
     * Sets the value of the isBlocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setIsBlocked(JAXBElement<Boolean> value) {
        this.isBlocked = value;
    }

    /**
     * Gets the value of the isBlockedLastModified property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getIsBlockedLastModified() {
        return isBlockedLastModified;
    }

    /**
     * Sets the value of the isBlockedLastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setIsBlockedLastModified(JAXBElement<XMLGregorianCalendar> value) {
        this.isBlockedLastModified = value;
    }

    /**
     * Gets the value of the isDeepLaunchSupported property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getIsDeepLaunchSupported() {
        return isDeepLaunchSupported;
    }

    /**
     * Sets the value of the isDeepLaunchSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setIsDeepLaunchSupported(JAXBElement<Boolean> value) {
        this.isDeepLaunchSupported = value;
    }

    /**
     * Gets the value of the isDeepLaunchSupportedLastModified property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getIsDeepLaunchSupportedLastModified() {
        return isDeepLaunchSupportedLastModified;
    }

    /**
     * Sets the value of the isDeepLaunchSupportedLastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setIsDeepLaunchSupportedLastModified(JAXBElement<XMLGregorianCalendar> value) {
        this.isDeepLaunchSupportedLastModified = value;
    }

    /**
     * Gets the value of the isVerified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsVerified() {
        return isVerified;
    }

    /**
     * Sets the value of the isVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsVerified(Boolean value) {
        this.isVerified = value;
    }

    /**
     * Gets the value of the lastSuccessfullyVerified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSuccessfullyVerified() {
        return lastSuccessfullyVerified;
    }

    /**
     * Sets the value of the lastSuccessfullyVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSuccessfullyVerified(XMLGregorianCalendar value) {
        this.lastSuccessfullyVerified = value;
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
     * Gets the value of the requestedMasterSite property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRequestedMasterSite() {
        return requestedMasterSite;
    }

    /**
     * Sets the value of the requestedMasterSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRequestedMasterSite(JAXBElement<String> value) {
        this.requestedMasterSite = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
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
     * Gets the value of the verificationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link SiteVerificationMethod }
     *     
     */
    public SiteVerificationMethod getVerificationMethod() {
        return verificationMethod;
    }

    /**
     * Sets the value of the verificationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteVerificationMethod }
     *     
     */
    public void setVerificationMethod(SiteVerificationMethod value) {
        this.verificationMethod = value;
    }

}
