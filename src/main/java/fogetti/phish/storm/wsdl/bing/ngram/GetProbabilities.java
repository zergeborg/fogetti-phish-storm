
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
 *         &lt;element name="authorizationToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modelUrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phrases" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
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
    "authorizationToken",
    "modelUrn",
    "phrases"
})
@XmlRootElement(name = "GetProbabilities")
public class GetProbabilities {

    @XmlElementRef(name = "authorizationToken", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<String> authorizationToken;
    @XmlElementRef(name = "modelUrn", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<String> modelUrn;
    @XmlElementRef(name = "phrases", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> phrases;

    /**
     * Gets the value of the authorizationToken property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Sets the value of the authorizationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuthorizationToken(JAXBElement<String> value) {
        this.authorizationToken = value;
    }

    /**
     * Gets the value of the modelUrn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModelUrn() {
        return modelUrn;
    }

    /**
     * Sets the value of the modelUrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModelUrn(JAXBElement<String> value) {
        this.modelUrn = value;
    }

    /**
     * Gets the value of the phrases property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getPhrases() {
        return phrases;
    }

    /**
     * Sets the value of the phrases property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setPhrases(JAXBElement<ArrayOfstring> value) {
        this.phrases = value;
    }

}
