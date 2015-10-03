
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfLinkCount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLinkCount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LinkCount" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}LinkCount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLinkCount", propOrder = {
    "linkCount"
})
public class ArrayOfLinkCount {

    @XmlElement(name = "LinkCount", nillable = true)
    protected List<LinkCount> linkCount;

    /**
     * Gets the value of the linkCount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkCount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkCount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkCount }
     * 
     * 
     */
    public List<LinkCount> getLinkCount() {
        if (linkCount == null) {
            linkCount = new ArrayList<LinkCount>();
        }
        return this.linkCount;
    }

}
