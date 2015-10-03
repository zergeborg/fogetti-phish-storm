
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
 *         &lt;element name="GetDisavowedLinksResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfDisavowedLink" minOccurs="0"/>
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
    "getDisavowedLinksResult"
})
@XmlRootElement(name = "GetDisavowedLinksResponse")
public class GetDisavowedLinksResponse {

    @XmlElementRef(name = "GetDisavowedLinksResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDisavowedLink> getDisavowedLinksResult;

    /**
     * Gets the value of the getDisavowedLinksResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDisavowedLink }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDisavowedLink> getGetDisavowedLinksResult() {
        return getDisavowedLinksResult;
    }

    /**
     * Sets the value of the getDisavowedLinksResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDisavowedLink }{@code >}
     *     
     */
    public void setGetDisavowedLinksResult(JAXBElement<ArrayOfDisavowedLink> value) {
        this.getDisavowedLinksResult = value;
    }

}
