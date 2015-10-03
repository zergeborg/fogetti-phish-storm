
package fogetti.phish.storm.wsdl.bing.ngram;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetConditionalProbabilityResult" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
    "getConditionalProbabilityResult"
})
@XmlRootElement(name = "GetConditionalProbabilityResponse")
public class GetConditionalProbabilityResponse {

    @XmlElement(name = "GetConditionalProbabilityResult")
    protected Float getConditionalProbabilityResult;

    /**
     * Gets the value of the getConditionalProbabilityResult property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGetConditionalProbabilityResult() {
        return getConditionalProbabilityResult;
    }

    /**
     * Sets the value of the getConditionalProbabilityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGetConditionalProbabilityResult(Float value) {
        this.getConditionalProbabilityResult = value;
    }

}
