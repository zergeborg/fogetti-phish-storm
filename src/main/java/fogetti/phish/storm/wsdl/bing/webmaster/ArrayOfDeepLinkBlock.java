
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDeepLinkBlock complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDeepLinkBlock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeepLinkBlock" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts}DeepLinkBlock" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDeepLinkBlock", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", propOrder = {
    "deepLinkBlock"
})
public class ArrayOfDeepLinkBlock {

    @XmlElement(name = "DeepLinkBlock", nillable = true)
    protected List<DeepLinkBlock> deepLinkBlock;

    /**
     * Gets the value of the deepLinkBlock property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deepLinkBlock property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeepLinkBlock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeepLinkBlock }
     * 
     * 
     */
    public List<DeepLinkBlock> getDeepLinkBlock() {
        if (deepLinkBlock == null) {
            deepLinkBlock = new ArrayList<DeepLinkBlock>();
        }
        return this.deepLinkBlock;
    }

}
