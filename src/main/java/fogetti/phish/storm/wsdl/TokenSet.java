
package fogetti.phish.storm.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TokenSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TokenSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="backoff" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="cookie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="probabilities" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOffloat" minOccurs="0"/>
 *         &lt;element name="words" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TokenSet", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", propOrder = {
    "backoff",
    "cookie",
    "probabilities",
    "words"
})
public class TokenSet {

    protected Float backoff;
    @XmlElementRef(name = "cookie", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cookie;
    @XmlElementRef(name = "probabilities", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOffloat> probabilities;
    @XmlElementRef(name = "words", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> words;

    /**
     * Gets the value of the backoff property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getBackoff() {
        return backoff;
    }

    /**
     * Sets the value of the backoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setBackoff(Float value) {
        this.backoff = value;
    }

    /**
     * Gets the value of the cookie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCookie() {
        return cookie;
    }

    /**
     * Sets the value of the cookie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCookie(JAXBElement<String> value) {
        this.cookie = value;
    }

    /**
     * Gets the value of the probabilities property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}
     *     
     */
    public JAXBElement<ArrayOffloat> getProbabilities() {
        return probabilities;
    }

    /**
     * Sets the value of the probabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}
     *     
     */
    public void setProbabilities(JAXBElement<ArrayOffloat> value) {
        this.probabilities = value;
    }

    /**
     * Gets the value of the words property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getWords() {
        return words;
    }

    /**
     * Sets the value of the words property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setWords(JAXBElement<ArrayOfstring> value) {
        this.words = value;
    }

}
