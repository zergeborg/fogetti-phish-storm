
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisavowedLink complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DisavowedLink">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisavowLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityType" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}DisavowedLink.DisavowUrlEntityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DisavowedLink", propOrder = {
    "date",
    "disavowLink",
    "entityType"
})
public class DisavowedLink {

    @XmlElementRef(name = "Date", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> date;
    @XmlElementRef(name = "DisavowLink", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> disavowLink;
    @XmlElement(name = "EntityType")
    @XmlSchemaType(name = "string")
    protected DisavowedLinkDisavowUrlEntityType entityType;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDate(JAXBElement<String> value) {
        this.date = value;
    }

    /**
     * Gets the value of the disavowLink property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisavowLink() {
        return disavowLink;
    }

    /**
     * Sets the value of the disavowLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisavowLink(JAXBElement<String> value) {
        this.disavowLink = value;
    }

    /**
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link DisavowedLinkDisavowUrlEntityType }
     *     
     */
    public DisavowedLinkDisavowUrlEntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisavowedLinkDisavowUrlEntityType }
     *     
     */
    public void setEntityType(DisavowedLinkDisavowUrlEntityType value) {
        this.entityType = value;
    }

}
