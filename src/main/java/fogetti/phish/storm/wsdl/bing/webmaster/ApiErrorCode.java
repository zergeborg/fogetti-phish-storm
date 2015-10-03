
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApiErrorCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ApiErrorCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="InternalError"/>
 *     &lt;enumeration value="UnknownError"/>
 *     &lt;enumeration value="InvalidApiKey"/>
 *     &lt;enumeration value="ThrottleUser"/>
 *     &lt;enumeration value="ThrottleHost"/>
 *     &lt;enumeration value="UserBlocked"/>
 *     &lt;enumeration value="InvalidUrl"/>
 *     &lt;enumeration value="InvalidParameter"/>
 *     &lt;enumeration value="TooManySites"/>
 *     &lt;enumeration value="UserNotFound"/>
 *     &lt;enumeration value="NotFound"/>
 *     &lt;enumeration value="AlreadyExists"/>
 *     &lt;enumeration value="NotAllowed"/>
 *     &lt;enumeration value="NotAuthorized"/>
 *     &lt;enumeration value="UnexpectedState"/>
 *     &lt;enumeration value="Deprecated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ApiErrorCode")
@XmlEnum
public enum ApiErrorCode {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError"),
    @XmlEnumValue("UnknownError")
    UNKNOWN_ERROR("UnknownError"),
    @XmlEnumValue("InvalidApiKey")
    INVALID_API_KEY("InvalidApiKey"),
    @XmlEnumValue("ThrottleUser")
    THROTTLE_USER("ThrottleUser"),
    @XmlEnumValue("ThrottleHost")
    THROTTLE_HOST("ThrottleHost"),
    @XmlEnumValue("UserBlocked")
    USER_BLOCKED("UserBlocked"),
    @XmlEnumValue("InvalidUrl")
    INVALID_URL("InvalidUrl"),
    @XmlEnumValue("InvalidParameter")
    INVALID_PARAMETER("InvalidParameter"),
    @XmlEnumValue("TooManySites")
    TOO_MANY_SITES("TooManySites"),
    @XmlEnumValue("UserNotFound")
    USER_NOT_FOUND("UserNotFound"),
    @XmlEnumValue("NotFound")
    NOT_FOUND("NotFound"),
    @XmlEnumValue("AlreadyExists")
    ALREADY_EXISTS("AlreadyExists"),
    @XmlEnumValue("NotAllowed")
    NOT_ALLOWED("NotAllowed"),
    @XmlEnumValue("NotAuthorized")
    NOT_AUTHORIZED("NotAuthorized"),
    @XmlEnumValue("UnexpectedState")
    UNEXPECTED_STATE("UnexpectedState"),
    @XmlEnumValue("Deprecated")
    DEPRECATED("Deprecated");
    private final String value;

    ApiErrorCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ApiErrorCode fromValue(String v) {
        for (ApiErrorCode c: ApiErrorCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
