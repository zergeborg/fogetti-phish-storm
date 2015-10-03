
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
 *         &lt;element name="GetRelatedKeywordsResult" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}ArrayOfKeyword" minOccurs="0"/>
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
    "getRelatedKeywordsResult"
})
@XmlRootElement(name = "GetRelatedKeywordsResponse")
public class GetRelatedKeywordsResponse {

    @XmlElementRef(name = "GetRelatedKeywordsResult", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfKeyword> getRelatedKeywordsResult;

    /**
     * Gets the value of the getRelatedKeywordsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyword }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyword> getGetRelatedKeywordsResult() {
        return getRelatedKeywordsResult;
    }

    /**
     * Sets the value of the getRelatedKeywordsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyword }{@code >}
     *     
     */
    public void setGetRelatedKeywordsResult(JAXBElement<ArrayOfKeyword> value) {
        this.getRelatedKeywordsResult = value;
    }

}
