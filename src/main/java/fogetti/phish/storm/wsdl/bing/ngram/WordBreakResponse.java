
package fogetti.phish.storm.wsdl.bing.ngram;

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
 *         &lt;element name="WordBreakResult" type="{http://schemas.microsoft.com/research/2009/10/webngram/fanout}WordBreakerResultSet" minOccurs="0"/>
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
    "wordBreakResult"
})
@XmlRootElement(name = "WordBreakResponse")
public class WordBreakResponse {

    @XmlElementRef(name = "WordBreakResult", namespace = "http://schemas.microsoft.com/research/2011/08/wiab", type = JAXBElement.class, required = false)
    protected JAXBElement<WordBreakerResultSet> wordBreakResult;

    /**
     * Gets the value of the wordBreakResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WordBreakerResultSet }{@code >}
     *     
     */
    public JAXBElement<WordBreakerResultSet> getWordBreakResult() {
        return wordBreakResult;
    }

    /**
     * Sets the value of the wordBreakResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WordBreakerResultSet }{@code >}
     *     
     */
    public void setWordBreakResult(JAXBElement<WordBreakerResultSet> value) {
        this.wordBreakResult = value;
    }

}
