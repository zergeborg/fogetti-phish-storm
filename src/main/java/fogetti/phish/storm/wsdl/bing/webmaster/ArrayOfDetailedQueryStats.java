
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDetailedQueryStats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDetailedQueryStats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetailedQueryStats" type="{http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api}DetailedQueryStats" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetailedQueryStats", propOrder = {
    "detailedQueryStats"
})
public class ArrayOfDetailedQueryStats {

    @XmlElement(name = "DetailedQueryStats", nillable = true)
    protected List<DetailedQueryStats> detailedQueryStats;

    /**
     * Gets the value of the detailedQueryStats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detailedQueryStats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetailedQueryStats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetailedQueryStats }
     * 
     * 
     */
    public List<DetailedQueryStats> getDetailedQueryStats() {
        if (detailedQueryStats == null) {
            detailedQueryStats = new ArrayList<DetailedQueryStats>();
        }
        return this.detailedQueryStats;
    }

}
