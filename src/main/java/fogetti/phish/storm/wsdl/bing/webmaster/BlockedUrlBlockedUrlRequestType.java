
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlockedUrl.BlockedUrlRequestType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlockedUrl.BlockedUrlRequestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CacheOnly"/>
 *     &lt;enumeration value="FullRemoval"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlockedUrl.BlockedUrlRequestType")
@XmlEnum
public enum BlockedUrlBlockedUrlRequestType {

    @XmlEnumValue("CacheOnly")
    CACHE_ONLY("CacheOnly"),
    @XmlEnumValue("FullRemoval")
    FULL_REMOVAL("FullRemoval");
    private final String value;

    BlockedUrlBlockedUrlRequestType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BlockedUrlBlockedUrlRequestType fromValue(String v) {
        for (BlockedUrlBlockedUrlRequestType c: BlockedUrlBlockedUrlRequestType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
