
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSiteMoveSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSiteMoveSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SiteMoveSettings" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}SiteMoveSettings" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSiteMoveSettings", propOrder = {
    "siteMoveSettings"
})
public class ArrayOfSiteMoveSettings {

    @XmlElement(name = "SiteMoveSettings", nillable = true)
    protected List<SiteMoveSettings> siteMoveSettings;

    /**
     * Gets the value of the siteMoveSettings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the siteMoveSettings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSiteMoveSettings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SiteMoveSettings }
     * 
     * 
     */
    public List<SiteMoveSettings> getSiteMoveSettings() {
        if (siteMoveSettings == null) {
            siteMoveSettings = new ArrayList<SiteMoveSettings>();
        }
        return this.siteMoveSettings;
    }

}
