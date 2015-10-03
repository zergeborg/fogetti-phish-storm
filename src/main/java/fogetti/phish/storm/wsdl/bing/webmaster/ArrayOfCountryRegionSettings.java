
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCountryRegionSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCountryRegionSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CountryRegionSettings" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}CountryRegionSettings" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCountryRegionSettings", propOrder = {
    "countryRegionSettings"
})
public class ArrayOfCountryRegionSettings {

    @XmlElement(name = "CountryRegionSettings", nillable = true)
    protected List<CountryRegionSettings> countryRegionSettings;

    /**
     * Gets the value of the countryRegionSettings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryRegionSettings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryRegionSettings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryRegionSettings }
     * 
     * 
     */
    public List<CountryRegionSettings> getCountryRegionSettings() {
        if (countryRegionSettings == null) {
            countryRegionSettings = new ArrayList<CountryRegionSettings>();
        }
        return this.countryRegionSettings;
    }

}
