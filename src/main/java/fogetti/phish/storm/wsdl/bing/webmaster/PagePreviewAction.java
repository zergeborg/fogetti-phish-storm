
package fogetti.phish.storm.wsdl.bing.webmaster;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagePreviewAction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PagePreviewAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Block"/>
 *     &lt;enumeration value="Refresh"/>
 *     &lt;enumeration value="Unblock"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PagePreviewAction", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts")
@XmlEnum
public enum PagePreviewAction {

    @XmlEnumValue("Block")
    BLOCK("Block"),
    @XmlEnumValue("Refresh")
    REFRESH("Refresh"),
    @XmlEnumValue("Unblock")
    UNBLOCK("Unblock");
    private final String value;

    PagePreviewAction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PagePreviewAction fromValue(String v) {
        for (PagePreviewAction c: PagePreviewAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
