
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CountryRegionSettings.CountryRegionSettingsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CountryRegionSettings.CountryRegionSettingsType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Page"/>
 *     &lt;enumeration value="Directory"/>
 *     &lt;enumeration value="Domain"/>
 *     &lt;enumeration value="Subdomain"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CountryRegionSettings.CountryRegionSettingsType")
@XmlEnum
public enum CountryRegionSettingsCountryRegionSettingsType {

    @XmlEnumValue("Page")
    PAGE("Page"),
    @XmlEnumValue("Directory")
    DIRECTORY("Directory"),
    @XmlEnumValue("Domain")
    DOMAIN("Domain"),
    @XmlEnumValue("Subdomain")
    SUBDOMAIN("Subdomain");
    private final String value;

    CountryRegionSettingsCountryRegionSettingsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CountryRegionSettingsCountryRegionSettingsType fromValue(String v) {
        for (CountryRegionSettingsCountryRegionSettingsType c: CountryRegionSettingsCountryRegionSettingsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
