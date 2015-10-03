
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteMoveSettings.Scope.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteMoveSettings.Scope">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Domain"/>
 *     &lt;enumeration value="Host"/>
 *     &lt;enumeration value="Directory"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteMoveSettings.Scope", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces")
@XmlEnum
public enum SiteMoveSettingsScope {

    @XmlEnumValue("Domain")
    DOMAIN("Domain"),
    @XmlEnumValue("Host")
    HOST("Host"),
    @XmlEnumValue("Directory")
    DIRECTORY("Directory");
    private final String value;

    SiteMoveSettingsScope(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteMoveSettingsScope fromValue(String v) {
        for (SiteMoveSettingsScope c: SiteMoveSettingsScope.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
