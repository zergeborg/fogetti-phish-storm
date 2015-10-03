
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefreshReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RefreshReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="ImageOutdated"/>
 *     &lt;enumeration value="ProblemsFixed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RefreshReason", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts")
@XmlEnum
public enum RefreshReason {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("ImageOutdated")
    IMAGE_OUTDATED("ImageOutdated"),
    @XmlEnumValue("ProblemsFixed")
    PROBLEMS_FIXED("ProblemsFixed");
    private final String value;

    RefreshReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RefreshReason fromValue(String v) {
        for (RefreshReason c: RefreshReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
