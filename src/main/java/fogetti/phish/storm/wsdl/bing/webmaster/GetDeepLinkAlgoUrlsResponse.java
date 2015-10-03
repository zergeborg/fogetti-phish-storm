
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
 *         &lt;element name="GetDeepLinkAlgoUrlsResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfDeepLinkAlgoUrl" minOccurs="0"/>
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
    "getDeepLinkAlgoUrlsResult"
})
@XmlRootElement(name = "GetDeepLinkAlgoUrlsResponse")
public class GetDeepLinkAlgoUrlsResponse {

    @XmlElementRef(name = "GetDeepLinkAlgoUrlsResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDeepLinkAlgoUrl> getDeepLinkAlgoUrlsResult;

    /**
     * Gets the value of the getDeepLinkAlgoUrlsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkAlgoUrl }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDeepLinkAlgoUrl> getGetDeepLinkAlgoUrlsResult() {
        return getDeepLinkAlgoUrlsResult;
    }

    /**
     * Sets the value of the getDeepLinkAlgoUrlsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkAlgoUrl }{@code >}
     *     
     */
    public void setGetDeepLinkAlgoUrlsResult(JAXBElement<ArrayOfDeepLinkAlgoUrl> value) {
        this.getDeepLinkAlgoUrlsResult = value;
    }

}
