
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBlockedUrl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBlockedUrl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BlockedUrl" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}BlockedUrl" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBlockedUrl", propOrder = {
    "blockedUrl"
})
public class ArrayOfBlockedUrl {

    @XmlElement(name = "BlockedUrl", nillable = true)
    protected List<BlockedUrl> blockedUrl;

    /**
     * Gets the value of the blockedUrl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockedUrl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockedUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlockedUrl }
     * 
     * 
     */
    public List<BlockedUrl> getBlockedUrl() {
        if (blockedUrl == null) {
            blockedUrl = new ArrayList<BlockedUrl>();
        }
        return this.blockedUrl;
    }

}
