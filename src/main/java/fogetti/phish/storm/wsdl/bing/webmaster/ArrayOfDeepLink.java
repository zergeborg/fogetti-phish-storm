
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDeepLink complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDeepLink">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeepLink" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}DeepLink" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDeepLink", propOrder = {
    "deepLink"
})
public class ArrayOfDeepLink {

    @XmlElement(name = "DeepLink", nillable = true)
    protected List<DeepLink> deepLink;

    /**
     * Gets the value of the deepLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deepLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeepLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeepLink }
     * 
     * 
     */
    public List<DeepLink> getDeepLink() {
        if (deepLink == null) {
            deepLink = new ArrayList<DeepLink>();
        }
        return this.deepLink;
    }

}
