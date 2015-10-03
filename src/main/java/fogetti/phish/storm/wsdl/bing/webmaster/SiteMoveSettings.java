
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
 * <p>Java class for SiteMoveSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SiteMoveSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="MoveScope" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces}SiteMoveSettings.Scope" minOccurs="0"/>
 *         &lt;element name="MoveType" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces}SiteMoveSettings.Type" minOccurs="0"/>
 *         &lt;element name="SourceUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TargetUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SiteMoveSettings", propOrder = {
    "date",
    "moveScope",
    "moveType",
    "sourceUrl",
    "targetUrl"
})
public class SiteMoveSettings {

    @XmlElement(name = "Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "MoveScope")
    @XmlSchemaType(name = "string")
    protected SiteMoveSettingsScope moveScope;
    @XmlElement(name = "MoveType")
    @XmlSchemaType(name = "string")
    protected SiteMoveSettingsType moveType;
    @XmlElementRef(name = "SourceUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sourceUrl;
    @XmlElementRef(name = "TargetUrl", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetUrl;

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
     * Gets the value of the moveScope property.
     * 
     * @return
     *     possible object is
     *     {@link SiteMoveSettingsScope }
     *     
     */
    public SiteMoveSettingsScope getMoveScope() {
        return moveScope;
    }

    /**
     * Sets the value of the moveScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteMoveSettingsScope }
     *     
     */
    public void setMoveScope(SiteMoveSettingsScope value) {
        this.moveScope = value;
    }

    /**
     * Gets the value of the moveType property.
     * 
     * @return
     *     possible object is
     *     {@link SiteMoveSettingsType }
     *     
     */
    public SiteMoveSettingsType getMoveType() {
        return moveType;
    }

    /**
     * Sets the value of the moveType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteMoveSettingsType }
     *     
     */
    public void setMoveType(SiteMoveSettingsType value) {
        this.moveType = value;
    }

    /**
     * Gets the value of the sourceUrl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSourceUrl() {
        return sourceUrl;
    }

    /**
     * Sets the value of the sourceUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSourceUrl(JAXBElement<String> value) {
        this.sourceUrl = value;
    }

    /**
     * Gets the value of the targetUrl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetUrl() {
        return targetUrl;
    }

    /**
     * Sets the value of the targetUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetUrl(JAXBElement<String> value) {
        this.targetUrl = value;
    }

}
