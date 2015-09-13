
package fogetti.phish.storm.wsdl;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fogetti.phish.storm.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _TokenSet_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "TokenSet");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _WordBreakerResultSet_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "WordBreakerResultSet");
    private final static QName _ArrayOfstring_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _ArrayOffloat_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOffloat");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _ArrayOfdouble_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfdouble");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _GetProbabilitiesResponseGetProbabilitiesResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GetProbabilitiesResult");
    private final static QName _GeneratePhraseContext_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "phraseContext");
    private final static QName _GenerateCookie_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "cookie");
    private final static QName _GenerateModelUrn_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "modelUrn");
    private final static QName _GenerateAuthorizationToken_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "authorizationToken");
    private final static QName _GetProbabilityPhrase_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "phrase");
    private final static QName _PhraseBreakTreeResponsePhraseBreakTreeResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "PhraseBreakTreeResult");
    private final static QName _GetProbabilitiesPhrases_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "phrases");
    private final static QName _GetModelsForWordBreakerResponseGetModelsForWordBreakerResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GetModelsForWordBreakerResult");
    private final static QName _GenerateResponseGenerateResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GenerateResult");
    private final static QName _PhraseBreakText_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "text");
    private final static QName _PhraseBreakAuthToken_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "authToken");
    private final static QName _TokenSetCookie_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "cookie");
    private final static QName _TokenSetProbabilities_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "probabilities");
    private final static QName _TokenSetWords_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "words");
    private final static QName _WordBreakerResultSetResults_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "results");
    private final static QName _WordBreakerResultSetLogprobabilities_QNAME = new QName("http://schemas.microsoft.com/research/2009/10/webngram/fanout", "logprobabilities");
    private final static QName _GetModelsForPhraseBreakerResponseGetModelsForPhraseBreakerResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GetModelsForPhraseBreakerResult");
    private final static QName _WordBreakResponseWordBreakResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "WordBreakResult");
    private final static QName _PhraseBreakResponsePhraseBreakResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "PhraseBreakResult");
    private final static QName _GetConditionalProbabilitiesResponseGetConditionalProbabilitiesResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GetConditionalProbabilitiesResult");
    private final static QName _GetModelsResponseGetModelsResult_QNAME = new QName("http://schemas.microsoft.com/research/2011/08/wiab", "GetModelsResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fogetti.phish.storm.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetModelsForPhraseBreakerResponse }
     * 
     */
    public GetModelsForPhraseBreakerResponse createGetModelsForPhraseBreakerResponse() {
        return new GetModelsForPhraseBreakerResponse();
    }

    /**
     * Create an instance of {@link ArrayOfstring }
     * 
     */
    public ArrayOfstring createArrayOfstring() {
        return new ArrayOfstring();
    }

    /**
     * Create an instance of {@link GetModelsForWordBreakerResponse }
     * 
     */
    public GetModelsForWordBreakerResponse createGetModelsForWordBreakerResponse() {
        return new GetModelsForWordBreakerResponse();
    }

    /**
     * Create an instance of {@link PhraseBreakTreeResponse }
     * 
     */
    public PhraseBreakTreeResponse createPhraseBreakTreeResponse() {
        return new PhraseBreakTreeResponse();
    }

    /**
     * Create an instance of {@link GetModelsResponse }
     * 
     */
    public GetModelsResponse createGetModelsResponse() {
        return new GetModelsResponse();
    }

    /**
     * Create an instance of {@link GetConditionalProbability }
     * 
     */
    public GetConditionalProbability createGetConditionalProbability() {
        return new GetConditionalProbability();
    }

    /**
     * Create an instance of {@link GenerateResponse }
     * 
     */
    public GenerateResponse createGenerateResponse() {
        return new GenerateResponse();
    }

    /**
     * Create an instance of {@link TokenSet }
     * 
     */
    public TokenSet createTokenSet() {
        return new TokenSet();
    }

    /**
     * Create an instance of {@link GetProbability }
     * 
     */
    public GetProbability createGetProbability() {
        return new GetProbability();
    }

    /**
     * Create an instance of {@link GetProbabilities }
     * 
     */
    public GetProbabilities createGetProbabilities() {
        return new GetProbabilities();
    }

    /**
     * Create an instance of {@link GetProbabilityResponse }
     * 
     */
    public GetProbabilityResponse createGetProbabilityResponse() {
        return new GetProbabilityResponse();
    }

    /**
     * Create an instance of {@link PhraseBreak }
     * 
     */
    public PhraseBreak createPhraseBreak() {
        return new PhraseBreak();
    }

    /**
     * Create an instance of {@link GetModelsForPhraseBreaker }
     * 
     */
    public GetModelsForPhraseBreaker createGetModelsForPhraseBreaker() {
        return new GetModelsForPhraseBreaker();
    }

    /**
     * Create an instance of {@link WordBreak }
     * 
     */
    public WordBreak createWordBreak() {
        return new WordBreak();
    }

    /**
     * Create an instance of {@link GetModels }
     * 
     */
    public GetModels createGetModels() {
        return new GetModels();
    }

    /**
     * Create an instance of {@link GetModelsForWordBreaker }
     * 
     */
    public GetModelsForWordBreaker createGetModelsForWordBreaker() {
        return new GetModelsForWordBreaker();
    }

    /**
     * Create an instance of {@link GetConditionalProbabilityResponse }
     * 
     */
    public GetConditionalProbabilityResponse createGetConditionalProbabilityResponse() {
        return new GetConditionalProbabilityResponse();
    }

    /**
     * Create an instance of {@link Generate }
     * 
     */
    public Generate createGenerate() {
        return new Generate();
    }

    /**
     * Create an instance of {@link GetProbabilitiesResponse }
     * 
     */
    public GetProbabilitiesResponse createGetProbabilitiesResponse() {
        return new GetProbabilitiesResponse();
    }

    /**
     * Create an instance of {@link ArrayOffloat }
     * 
     */
    public ArrayOffloat createArrayOffloat() {
        return new ArrayOffloat();
    }

    /**
     * Create an instance of {@link WordBreakResponse }
     * 
     */
    public WordBreakResponse createWordBreakResponse() {
        return new WordBreakResponse();
    }

    /**
     * Create an instance of {@link WordBreakerResultSet }
     * 
     */
    public WordBreakerResultSet createWordBreakerResultSet() {
        return new WordBreakerResultSet();
    }

    /**
     * Create an instance of {@link GetConditionalProbabilitiesResponse }
     * 
     */
    public GetConditionalProbabilitiesResponse createGetConditionalProbabilitiesResponse() {
        return new GetConditionalProbabilitiesResponse();
    }

    /**
     * Create an instance of {@link PhraseBreakResponse }
     * 
     */
    public PhraseBreakResponse createPhraseBreakResponse() {
        return new PhraseBreakResponse();
    }

    /**
     * Create an instance of {@link GetConditionalProbabilities }
     * 
     */
    public GetConditionalProbabilities createGetConditionalProbabilities() {
        return new GetConditionalProbabilities();
    }

    /**
     * Create an instance of {@link PhraseBreakTree }
     * 
     */
    public PhraseBreakTree createPhraseBreakTree() {
        return new PhraseBreakTree();
    }

    /**
     * Create an instance of {@link ArrayOfdouble }
     * 
     */
    public ArrayOfdouble createArrayOfdouble() {
        return new ArrayOfdouble();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TokenSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "TokenSet")
    public JAXBElement<TokenSet> createTokenSet(TokenSet value) {
        return new JAXBElement<TokenSet>(_TokenSet_QNAME, TokenSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WordBreakerResultSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "WordBreakerResultSet")
    public JAXBElement<WordBreakerResultSet> createWordBreakerResultSet(WordBreakerResultSet value) {
        return new JAXBElement<WordBreakerResultSet>(_WordBreakerResultSet_QNAME, WordBreakerResultSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfstring")
    public JAXBElement<ArrayOfstring> createArrayOfstring(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_ArrayOfstring_QNAME, ArrayOfstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOffloat")
    public JAXBElement<ArrayOffloat> createArrayOffloat(ArrayOffloat value) {
        return new JAXBElement<ArrayOffloat>(_ArrayOffloat_QNAME, ArrayOffloat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfdouble }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfdouble")
    public JAXBElement<ArrayOfdouble> createArrayOfdouble(ArrayOfdouble value) {
        return new JAXBElement<ArrayOfdouble>(_ArrayOfdouble_QNAME, ArrayOfdouble.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GetProbabilitiesResult", scope = GetProbabilitiesResponse.class)
    public JAXBElement<ArrayOffloat> createGetProbabilitiesResponseGetProbabilitiesResult(ArrayOffloat value) {
        return new JAXBElement<ArrayOffloat>(_GetProbabilitiesResponseGetProbabilitiesResult_QNAME, ArrayOffloat.class, GetProbabilitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "phraseContext", scope = Generate.class)
    public JAXBElement<String> createGeneratePhraseContext(String value) {
        return new JAXBElement<String>(_GeneratePhraseContext_QNAME, String.class, Generate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "cookie", scope = Generate.class)
    public JAXBElement<String> createGenerateCookie(String value) {
        return new JAXBElement<String>(_GenerateCookie_QNAME, String.class, Generate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = Generate.class)
    public JAXBElement<String> createGenerateModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, Generate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = Generate.class)
    public JAXBElement<String> createGenerateAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, Generate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "phrase", scope = GetProbability.class)
    public JAXBElement<String> createGetProbabilityPhrase(String value) {
        return new JAXBElement<String>(_GetProbabilityPhrase_QNAME, String.class, GetProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = GetProbability.class)
    public JAXBElement<String> createGetProbabilityModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, GetProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = GetProbability.class)
    public JAXBElement<String> createGetProbabilityAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, GetProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "PhraseBreakTreeResult", scope = PhraseBreakTreeResponse.class)
    public JAXBElement<String> createPhraseBreakTreeResponsePhraseBreakTreeResult(String value) {
        return new JAXBElement<String>(_PhraseBreakTreeResponsePhraseBreakTreeResult_QNAME, String.class, PhraseBreakTreeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "phrases", scope = GetProbabilities.class)
    public JAXBElement<ArrayOfstring> createGetProbabilitiesPhrases(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetProbabilitiesPhrases_QNAME, ArrayOfstring.class, GetProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = GetProbabilities.class)
    public JAXBElement<String> createGetProbabilitiesModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, GetProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = GetProbabilities.class)
    public JAXBElement<String> createGetProbabilitiesAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, GetProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GetModelsForWordBreakerResult", scope = GetModelsForWordBreakerResponse.class)
    public JAXBElement<ArrayOfstring> createGetModelsForWordBreakerResponseGetModelsForWordBreakerResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetModelsForWordBreakerResponseGetModelsForWordBreakerResult_QNAME, ArrayOfstring.class, GetModelsForWordBreakerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TokenSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GenerateResult", scope = GenerateResponse.class)
    public JAXBElement<TokenSet> createGenerateResponseGenerateResult(TokenSet value) {
        return new JAXBElement<TokenSet>(_GenerateResponseGenerateResult_QNAME, TokenSet.class, GenerateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "text", scope = PhraseBreak.class)
    public JAXBElement<String> createPhraseBreakText(String value) {
        return new JAXBElement<String>(_PhraseBreakText_QNAME, String.class, PhraseBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authToken", scope = PhraseBreak.class)
    public JAXBElement<String> createPhraseBreakAuthToken(String value) {
        return new JAXBElement<String>(_PhraseBreakAuthToken_QNAME, String.class, PhraseBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = PhraseBreak.class)
    public JAXBElement<String> createPhraseBreakModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, PhraseBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "cookie", scope = TokenSet.class)
    public JAXBElement<String> createTokenSetCookie(String value) {
        return new JAXBElement<String>(_TokenSetCookie_QNAME, String.class, TokenSet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "probabilities", scope = TokenSet.class)
    public JAXBElement<ArrayOffloat> createTokenSetProbabilities(ArrayOffloat value) {
        return new JAXBElement<ArrayOffloat>(_TokenSetProbabilities_QNAME, ArrayOffloat.class, TokenSet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "words", scope = TokenSet.class)
    public JAXBElement<ArrayOfstring> createTokenSetWords(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_TokenSetWords_QNAME, ArrayOfstring.class, TokenSet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "text", scope = WordBreak.class)
    public JAXBElement<String> createWordBreakText(String value) {
        return new JAXBElement<String>(_PhraseBreakText_QNAME, String.class, WordBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = WordBreak.class)
    public JAXBElement<String> createWordBreakModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, WordBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = WordBreak.class)
    public JAXBElement<String> createWordBreakAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, WordBreak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "results", scope = WordBreakerResultSet.class)
    public JAXBElement<ArrayOfstring> createWordBreakerResultSetResults(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_WordBreakerResultSetResults_QNAME, ArrayOfstring.class, WordBreakerResultSet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfdouble }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2009/10/webngram/fanout", name = "logprobabilities", scope = WordBreakerResultSet.class)
    public JAXBElement<ArrayOfdouble> createWordBreakerResultSetLogprobabilities(ArrayOfdouble value) {
        return new JAXBElement<ArrayOfdouble>(_WordBreakerResultSetLogprobabilities_QNAME, ArrayOfdouble.class, WordBreakerResultSet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GetModelsForPhraseBreakerResult", scope = GetModelsForPhraseBreakerResponse.class)
    public JAXBElement<ArrayOfstring> createGetModelsForPhraseBreakerResponseGetModelsForPhraseBreakerResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetModelsForPhraseBreakerResponseGetModelsForPhraseBreakerResult_QNAME, ArrayOfstring.class, GetModelsForPhraseBreakerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WordBreakerResultSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "WordBreakResult", scope = WordBreakResponse.class)
    public JAXBElement<WordBreakerResultSet> createWordBreakResponseWordBreakResult(WordBreakerResultSet value) {
        return new JAXBElement<WordBreakerResultSet>(_WordBreakResponseWordBreakResult_QNAME, WordBreakerResultSet.class, WordBreakResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "phrase", scope = GetConditionalProbability.class)
    public JAXBElement<String> createGetConditionalProbabilityPhrase(String value) {
        return new JAXBElement<String>(_GetProbabilityPhrase_QNAME, String.class, GetConditionalProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = GetConditionalProbability.class)
    public JAXBElement<String> createGetConditionalProbabilityModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, GetConditionalProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = GetConditionalProbability.class)
    public JAXBElement<String> createGetConditionalProbabilityAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, GetConditionalProbability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "PhraseBreakResult", scope = PhraseBreakResponse.class)
    public JAXBElement<String> createPhraseBreakResponsePhraseBreakResult(String value) {
        return new JAXBElement<String>(_PhraseBreakResponsePhraseBreakResult_QNAME, String.class, PhraseBreakResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOffloat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GetConditionalProbabilitiesResult", scope = GetConditionalProbabilitiesResponse.class)
    public JAXBElement<ArrayOffloat> createGetConditionalProbabilitiesResponseGetConditionalProbabilitiesResult(ArrayOffloat value) {
        return new JAXBElement<ArrayOffloat>(_GetConditionalProbabilitiesResponseGetConditionalProbabilitiesResult_QNAME, ArrayOffloat.class, GetConditionalProbabilitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "GetModelsResult", scope = GetModelsResponse.class)
    public JAXBElement<ArrayOfstring> createGetModelsResponseGetModelsResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetModelsResponseGetModelsResult_QNAME, ArrayOfstring.class, GetModelsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "phrases", scope = GetConditionalProbabilities.class)
    public JAXBElement<ArrayOfstring> createGetConditionalProbabilitiesPhrases(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetProbabilitiesPhrases_QNAME, ArrayOfstring.class, GetConditionalProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = GetConditionalProbabilities.class)
    public JAXBElement<String> createGetConditionalProbabilitiesModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, GetConditionalProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authorizationToken", scope = GetConditionalProbabilities.class)
    public JAXBElement<String> createGetConditionalProbabilitiesAuthorizationToken(String value) {
        return new JAXBElement<String>(_GenerateAuthorizationToken_QNAME, String.class, GetConditionalProbabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "text", scope = PhraseBreakTree.class)
    public JAXBElement<String> createPhraseBreakTreeText(String value) {
        return new JAXBElement<String>(_PhraseBreakText_QNAME, String.class, PhraseBreakTree.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "authToken", scope = PhraseBreakTree.class)
    public JAXBElement<String> createPhraseBreakTreeAuthToken(String value) {
        return new JAXBElement<String>(_PhraseBreakAuthToken_QNAME, String.class, PhraseBreakTree.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/research/2011/08/wiab", name = "modelUrn", scope = PhraseBreakTree.class)
    public JAXBElement<String> createPhraseBreakTreeModelUrn(String value) {
        return new JAXBElement<String>(_GenerateModelUrn_QNAME, String.class, PhraseBreakTree.class, value);
    }

}
