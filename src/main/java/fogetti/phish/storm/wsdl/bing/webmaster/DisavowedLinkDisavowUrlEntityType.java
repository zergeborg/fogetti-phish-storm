
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisavowedLink.DisavowUrlEntityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DisavowedLink.DisavowUrlEntityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Page"/>
 *     &lt;enumeration value="Directory"/>
 *     &lt;enumeration value="Domain"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DisavowedLink.DisavowUrlEntityType")
@XmlEnum
public enum DisavowedLinkDisavowUrlEntityType {

    @XmlEnumValue("Page")
    PAGE("Page"),
    @XmlEnumValue("Directory")
    DIRECTORY("Directory"),
    @XmlEnumValue("Domain")
    DOMAIN("Domain");
    private final String value;

    DisavowedLinkDisavowUrlEntityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DisavowedLinkDisavowUrlEntityType fromValue(String v) {
        for (DisavowedLinkDisavowUrlEntityType c: DisavowedLinkDisavowUrlEntityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
