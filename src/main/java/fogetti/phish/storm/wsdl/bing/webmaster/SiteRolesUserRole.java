
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteRoles.UserRole.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteRoles.UserRole">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Administrator"/>
 *     &lt;enumeration value="ReadOnly"/>
 *     &lt;enumeration value="ReadWrite"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteRoles.UserRole", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces")
@XmlEnum
public enum SiteRolesUserRole {

    @XmlEnumValue("Administrator")
    ADMINISTRATOR("Administrator"),
    @XmlEnumValue("ReadOnly")
    READ_ONLY("ReadOnly"),
    @XmlEnumValue("ReadWrite")
    READ_WRITE("ReadWrite");
    private final String value;

    SiteRolesUserRole(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteRolesUserRole fromValue(String v) {
        for (SiteRolesUserRole c: SiteRolesUserRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
