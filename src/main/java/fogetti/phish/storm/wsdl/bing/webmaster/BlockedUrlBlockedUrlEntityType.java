
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlockedUrl.BlockedUrlEntityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlockedUrl.BlockedUrlEntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Page"/>
 *     &lt;enumeration value="Directory"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlockedUrl.BlockedUrlEntityType")
@XmlEnum
public enum BlockedUrlBlockedUrlEntityType {

    @XmlEnumValue("Page")
    PAGE("Page"),
    @XmlEnumValue("Directory")
    DIRECTORY("Directory");
    private final String value;

    BlockedUrlBlockedUrlEntityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BlockedUrlBlockedUrlEntityType fromValue(String v) {
        for (BlockedUrlBlockedUrlEntityType c: BlockedUrlBlockedUrlEntityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
