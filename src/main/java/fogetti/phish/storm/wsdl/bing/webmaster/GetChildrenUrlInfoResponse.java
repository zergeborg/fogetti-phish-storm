
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
 *         &lt;element name="GetChildrenUrlInfoResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfUrlInfo" minOccurs="0"/>
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
    "getChildrenUrlInfoResult"
})
@XmlRootElement(name = "GetChildrenUrlInfoResponse")
public class GetChildrenUrlInfoResponse {

    @XmlElementRef(name = "GetChildrenUrlInfoResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfUrlInfo> getChildrenUrlInfoResult;

    /**
     * Gets the value of the getChildrenUrlInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfUrlInfo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfUrlInfo> getGetChildrenUrlInfoResult() {
        return getChildrenUrlInfoResult;
    }

    /**
     * Sets the value of the getChildrenUrlInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfUrlInfo }{@code >}
     *     
     */
    public void setGetChildrenUrlInfoResult(JAXBElement<ArrayOfUrlInfo> value) {
        this.getChildrenUrlInfoResult = value;
    }

}
