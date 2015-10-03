
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfUrlWithCrawlIssues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUrlWithCrawlIssues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UrlWithCrawlIssues" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}UrlWithCrawlIssues" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUrlWithCrawlIssues", propOrder = {
    "urlWithCrawlIssues"
})
public class ArrayOfUrlWithCrawlIssues {

    @XmlElement(name = "UrlWithCrawlIssues", nillable = true)
    protected List<UrlWithCrawlIssues> urlWithCrawlIssues;

    /**
     * Gets the value of the urlWithCrawlIssues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the urlWithCrawlIssues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrlWithCrawlIssues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UrlWithCrawlIssues }
     * 
     * 
     */
    public List<UrlWithCrawlIssues> getUrlWithCrawlIssues() {
        if (urlWithCrawlIssues == null) {
            urlWithCrawlIssues = new ArrayList<UrlWithCrawlIssues>();
        }
        return this.urlWithCrawlIssues;
    }

}
