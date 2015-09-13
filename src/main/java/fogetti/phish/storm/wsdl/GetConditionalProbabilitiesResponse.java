
package fogetti.phish.storm.wsdl;

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
 *         &lt;element name="GetConditionalProbabilitiesResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOffloat" minOccurs="0"/>
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
    "getConditionalProbabilitiesResult"
})
@XmlRootElement(name = "GetConditionalProbabilitiesResponse")
public class GetConditionalProbabilitiesResponse {

    @XmlElementRef(name = "GetConditionalProbabilitiesResult", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOffloat> getConditionalProbabilitiesResult;

    /**
     * Gets the value of the getConditionalProbabilitiesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}
     *     
     */
    public JAXBElement<ArrayOffloat> getGetConditionalProbabilitiesResult() {
        return getConditionalProbabilitiesResult;
    }

    /**
     * Sets the value of the getConditionalProbabilitiesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}
     *     
     */
    public void setGetConditionalProbabilitiesResult(JAXBElement<ArrayOffloat> value) {
        this.getConditionalProbabilitiesResult = value;
    }

}
