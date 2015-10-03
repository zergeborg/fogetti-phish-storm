
package fogetti.phish.storm.wsdl.bing.ngram;

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
 *         &lt;element name="GetModelsForPhraseBreakerResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
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
    "getModelsForPhraseBreakerResult"
})
@XmlRootElement(name = "GetModelsForPhraseBreakerResponse")
public class GetModelsForPhraseBreakerResponse {

    @XmlElementRef(name = "GetModelsForPhraseBreakerResult", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> getModelsForPhraseBreakerResult;

    /**
     * Gets the value of the getModelsForPhraseBreakerResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getGetModelsForPhraseBreakerResult() {
        return getModelsForPhraseBreakerResult;
    }

    /**
     * Sets the value of the getModelsForPhraseBreakerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setGetModelsForPhraseBreakerResult(JAXBElement<ArrayOfstring> value) {
        this.getModelsForPhraseBreakerResult = value;
    }

}
