
package fogetti.phish.storm.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WordBreakerResultSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WordBreakerResultSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logprobabilities" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfdouble" minOccurs="0"/>
 *         &lt;element name="results" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WordBreakerResultSet", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", propOrder = {
    "logprobabilities",
    "results"
})
public class WordBreakerResultSet {

    @XmlElementRef(name = "logprobabilities", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfdouble> logprobabilities;
    @XmlElementRef(name = "results", namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> results;

    /**
     * Gets the value of the logprobabilities property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfdouble }{@code >}
     *     
     */
    public JAXBElement<ArrayOfdouble> getLogprobabilities() {
        return logprobabilities;
    }

    /**
     * Sets the value of the logprobabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfdouble }{@code >}
     *     
     */
    public void setLogprobabilities(JAXBElement<ArrayOfdouble> value) {
        this.logprobabilities = value;
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setResults(JAXBElement<ArrayOfstring> value) {
        this.results = value;
    }

}
