
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiscoveredDateFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DiscoveredDateFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="LastWeek"/>
 *     &lt;enumeration value="LastMonth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DiscoveredDateFilter")
@XmlEnum
public enum DiscoveredDateFilter {

    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("LastWeek")
    LAST_WEEK("LastWeek"),
    @XmlEnumValue("LastMonth")
    LAST_MONTH("LastMonth");
    private final String value;

    DiscoveredDateFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DiscoveredDateFilter fromValue(String v) {
        for (DiscoveredDateFilter c: DiscoveredDateFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
