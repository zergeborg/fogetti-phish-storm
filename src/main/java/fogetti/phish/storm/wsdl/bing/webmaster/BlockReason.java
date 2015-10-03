
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlockReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlockReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="ContentNeedsToBeRemoved"/>
 *     &lt;enumeration value="PoorQualityAndIncorrect"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlockReason", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts")
@XmlEnum
public enum BlockReason {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("ContentNeedsToBeRemoved")
    CONTENT_NEEDS_TO_BE_REMOVED("ContentNeedsToBeRemoved"),
    @XmlEnumValue("PoorQualityAndIncorrect")
    POOR_QUALITY_AND_INCORRECT("PoorQualityAndIncorrect");
    private final String value;

    BlockReason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BlockReason fromValue(String v) {
        for (BlockReason c: BlockReason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
