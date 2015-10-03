
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UrlWithCrawlIssues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UrlWithCrawlIssues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HttpCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="InLinks" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Issues" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces}UrlWithCrawlIssues.CrawlIssues" minOccurs="0"/>
 *         &lt;element name="Url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UrlWithCrawlIssues", propOrder = {
    "httpCode",
    "inLinks",
    "issues",
    "url"
})
public class UrlWithCrawlIssues {

    @XmlElement(name = "HttpCode")
    protected Integer httpCode;
    @XmlElement(name = "InLinks")
    protected Long inLinks;
    @XmlList
    @XmlElement(name = "Issues")
    @XmlSchemaType(name = "anySimpleType")
    protected List<String> issues;
    @XmlElementRef(name = "Url", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", type = JAXBElement.class, required = false)
    protected JAXBElement<String> url;

    /**
     * Gets the value of the httpCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Sets the value of the httpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHttpCode(Integer value) {
        this.httpCode = value;
    }

    /**
     * Gets the value of the inLinks property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInLinks() {
        return inLinks;
    }

    /**
     * Sets the value of the inLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInLinks(Long value) {
        this.inLinks = value;
    }

    /**
     * Gets the value of the issues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIssues() {
        if (issues == null) {
            issues = new ArrayList<String>();
        }
        return this.issues;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUrl(JAXBElement<String> value) {
        this.url = value;
    }

}
