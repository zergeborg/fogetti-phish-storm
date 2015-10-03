
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteMoveSettings.Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteMoveSettings.Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Local"/>
 *     &lt;enumeration value="Global"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteMoveSettings.Type", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces")
@XmlEnum
public enum SiteMoveSettingsType {

    @XmlEnumValue("Local")
    LOCAL("Local"),
    @XmlEnumValue("Global")
    GLOBAL("Global");
    private final String value;

    SiteMoveSettingsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteMoveSettingsType fromValue(String v) {
        for (SiteMoveSettingsType c: SiteMoveSettingsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
