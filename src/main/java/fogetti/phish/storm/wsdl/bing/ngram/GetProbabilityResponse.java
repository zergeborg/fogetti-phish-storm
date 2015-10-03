
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
 *         &lt;element name="GetProbabilityResult" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
    "getProbabilityResult"
})
@XmlRootElement(name = "GetProbabilityResponse")
public class GetProbabilityResponse {

    @XmlElement(name = "GetProbabilityResult")
    protected Float getProbabilityResult;

    /**
     * Gets the value of the getProbabilityResult property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGetProbabilityResult() {
        return getProbabilityResult;
    }

    /**
     * Sets the value of the getProbabilityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGetProbabilityResult(Float value) {
        this.getProbabilityResult = value;
    }

}
