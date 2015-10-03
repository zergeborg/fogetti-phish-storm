
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCountryRegionSettingsResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfCountryRegionSettings" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCountryRegionSettingsResult"
})
@XmlRootElement(name = "GetCountryRegionSettingsResponse")
public class GetCountryRegionSettingsResponse {

    @XmlElementRef(name = "GetCountryRegionSettingsResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCountryRegionSettings> getCountryRegionSettingsResult;

    /**
     * Gets the value of the getCountryRegionSettingsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCountryRegionSettings }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCountryRegionSettings> getGetCountryRegionSettingsResult() {
        return getCountryRegionSettingsResult;
    }

    /**
     * Sets the value of the getCountryRegionSettingsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCountryRegionSettings }{@code >}
     *     
     */
    public void setGetCountryRegionSettingsResult(JAXBElement<ArrayOfCountryRegionSettings> value) {
        this.getCountryRegionSettingsResult = value;
    }

}
