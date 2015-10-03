
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteVerificationMethod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteVerificationMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MetaTag"/>
 *     &lt;enumeration value="XmlFile"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="AutoDetect"/>
 *     &lt;enumeration value="Dns"/>
 *     &lt;enumeration value="Facebook"/>
 *     &lt;enumeration value="Twitter"/>
 *     &lt;enumeration value="LinkedIn"/>
 *     &lt;enumeration value="GooglePlus"/>
 *     &lt;enumeration value="Android"/>
 *     &lt;enumeration value="Win8"/>
 *     &lt;enumeration value="Youtube"/>
 *     &lt;enumeration value="MySpace"/>
 *     &lt;enumeration value="Instagram"/>
 *     &lt;enumeration value="WindowsPhone"/>
 *     &lt;enumeration value="Pinterest"/>
 *     &lt;enumeration value="ITunes"/>
 *     &lt;enumeration value="FirstVerificationMethod"/>
 *     &lt;enumeration value="LastVerificationMethod"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteVerificationMethod", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.DataContracts")
@XmlEnum
public enum SiteVerificationMethod {

    @XmlEnumValue("MetaTag")
    META_TAG("MetaTag"),
    @XmlEnumValue("XmlFile")
    XML_FILE("XmlFile"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("AutoDetect")
    AUTO_DETECT("AutoDetect"),
    @XmlEnumValue("Dns")
    DNS("Dns"),
    @XmlEnumValue("Facebook")
    FACEBOOK("Facebook"),
    @XmlEnumValue("Twitter")
    TWITTER("Twitter"),
    @XmlEnumValue("LinkedIn")
    LINKED_IN("LinkedIn"),
    @XmlEnumValue("GooglePlus")
    GOOGLE_PLUS("GooglePlus"),
    @XmlEnumValue("Android")
    ANDROID("Android"),
    @XmlEnumValue("Win8")
    WIN_8("Win8"),
    @XmlEnumValue("Youtube")
    YOUTUBE("Youtube"),
    @XmlEnumValue("MySpace")
    MY_SPACE("MySpace"),
    @XmlEnumValue("Instagram")
    INSTAGRAM("Instagram"),
    @XmlEnumValue("WindowsPhone")
    WINDOWS_PHONE("WindowsPhone"),
    @XmlEnumValue("Pinterest")
    PINTEREST("Pinterest"),
    @XmlEnumValue("ITunes")
    I_TUNES("ITunes"),
    @XmlEnumValue("FirstVerificationMethod")
    FIRST_VERIFICATION_METHOD("FirstVerificationMethod"),
    @XmlEnumValue("LastVerificationMethod")
    LAST_VERIFICATION_METHOD("LastVerificationMethod"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    SiteVerificationMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteVerificationMethod fromValue(String v) {
        for (SiteVerificationMethod c: SiteVerificationMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
