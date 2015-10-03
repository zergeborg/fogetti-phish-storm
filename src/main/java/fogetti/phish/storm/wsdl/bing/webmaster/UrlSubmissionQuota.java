
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UrlSubmissionQuota complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UrlSubmissionQuota">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DailyQuota" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MonthlyQuota" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UrlSubmissionQuota", propOrder = {
    "dailyQuota",
    "monthlyQuota"
})
public class UrlSubmissionQuota {

    @XmlElement(name = "DailyQuota")
    protected Integer dailyQuota;
    @XmlElement(name = "MonthlyQuota")
    protected Integer monthlyQuota;

    /**
     * Gets the value of the dailyQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDailyQuota() {
        return dailyQuota;
    }

    /**
     * Sets the value of the dailyQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDailyQuota(Integer value) {
        this.dailyQuota = value;
    }

    /**
     * Gets the value of the monthlyQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonthlyQuota() {
        return monthlyQuota;
    }

    /**
     * Sets the value of the monthlyQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonthlyQuota(Integer value) {
        this.monthlyQuota = value;
    }

}
