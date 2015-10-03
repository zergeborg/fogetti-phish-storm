
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
 *         &lt;element name="GetPageQueryStatsResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfQueryStats" minOccurs="0"/>
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
    "getPageQueryStatsResult"
})
@XmlRootElement(name = "GetPageQueryStatsResponse")
public class GetPageQueryStatsResponse {

    @XmlElementRef(name = "GetPageQueryStatsResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfQueryStats> getPageQueryStatsResult;

    /**
     * Gets the value of the getPageQueryStatsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}
     *     
     */
    public JAXBElement<ArrayOfQueryStats> getGetPageQueryStatsResult() {
        return getPageQueryStatsResult;
    }

    /**
     * Sets the value of the getPageQueryStatsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}
     *     
     */
    public void setGetPageQueryStatsResult(JAXBElement<ArrayOfQueryStats> value) {
        this.getPageQueryStatsResult = value;
    }

}
