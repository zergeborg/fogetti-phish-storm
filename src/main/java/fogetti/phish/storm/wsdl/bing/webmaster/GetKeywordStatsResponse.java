
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
 *         &lt;element name="GetKeywordStatsResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfKeywordStats" minOccurs="0"/>
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
    "getKeywordStatsResult"
})
@XmlRootElement(name = "GetKeywordStatsResponse")
public class GetKeywordStatsResponse {

    @XmlElementRef(name = "GetKeywordStatsResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfKeywordStats> getKeywordStatsResult;

    /**
     * Gets the value of the getKeywordStatsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeywordStats }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeywordStats> getGetKeywordStatsResult() {
        return getKeywordStatsResult;
    }

    /**
     * Sets the value of the getKeywordStatsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeywordStats }{@code >}
     *     
     */
    public void setGetKeywordStatsResult(JAXBElement<ArrayOfKeywordStats> value) {
        this.getKeywordStatsResult = value;
    }

}
