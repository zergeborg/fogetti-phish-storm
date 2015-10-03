
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrawlDateFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CrawlDateFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="LastWeek"/>
 *     &lt;enumeration value="LastTwoWeeks"/>
 *     &lt;enumeration value="LastThreeWeeks"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CrawlDateFilter")
@XmlEnum
public enum CrawlDateFilter {

    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("LastWeek")
    LAST_WEEK("LastWeek"),
    @XmlEnumValue("LastTwoWeeks")
    LAST_TWO_WEEKS("LastTwoWeeks"),
    @XmlEnumValue("LastThreeWeeks")
    LAST_THREE_WEEKS("LastThreeWeeks");
    private final String value;

    CrawlDateFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrawlDateFilter fromValue(String v) {
        for (CrawlDateFilter c: CrawlDateFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
