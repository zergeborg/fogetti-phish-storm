
package fogetti.phish.storm.wsdl.bing.webmaster;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fogetti.phish.storm.wsdl.bing.webmaster package. 
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

    private final static QName _UrlSubmissionQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "UrlSubmissionQuota");
    private final static QName _BlockedUrlBlockedUrlEntityType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "BlockedUrl.BlockedUrlEntityType");
    private final static QName _ArrayOfQueryParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfQueryParameter");
    private final static QName _SiteVerificationMethod_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.DataContracts", "SiteVerificationMethod");
    private final static QName _ArrayOfDetailedQueryStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfDetailedQueryStats");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _DiscoveredDateFilter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DiscoveredDateFilter");
    private final static QName _RankAndTrafficStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "RankAndTrafficStats");
    private final static QName _FilterProperties_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "FilterProperties");
    private final static QName _DeepLinkDeepLinkWeight_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DeepLink.DeepLinkWeight");
    private final static QName _KeywordStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "KeywordStats");
    private final static QName _DisavowedLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DisavowedLink");
    private final static QName _ArrayOfUrlInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfUrlInfo");
    private final static QName _ArrayOfDeepLinkAlgoUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfDeepLinkAlgoUrl");
    private final static QName _FetchedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "FetchedUrl");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _UrlTrafficInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "UrlTrafficInfo");
    private final static QName _ArrayOfLinkCount_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfLinkCount");
    private final static QName _FetchedUrlDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "FetchedUrlDetails");
    private final static QName _LinkDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "LinkDetail");
    private final static QName _ArrayOfSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfSite");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _ArrayOfConnectedSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "ArrayOfConnectedSite");
    private final static QName _ArrayOfLinkDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfLinkDetail");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _CrawlSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CrawlSettings");
    private final static QName _ArrayOfDeepLinkBlock_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "ArrayOfDeepLinkBlock");
    private final static QName _ArrayOfCrawlStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfCrawlStats");
    private final static QName _ArrayOfKeywordStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfKeywordStats");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _BlockReason_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "BlockReason");
    private final static QName _Feed_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Feed");
    private final static QName _ArrayOfQueryStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfQueryStats");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _ArrayOfSiteMoveSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfSiteMoveSettings");
    private final static QName _UrlInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "UrlInfo");
    private final static QName _ArrayOfRankAndTrafficStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfRankAndTrafficStats");
    private final static QName _SiteMoveSettingsType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", "SiteMoveSettings.Type");
    private final static QName _ArrayOfPagePreview_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "ArrayOfPagePreview");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _CrawlDateFilter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CrawlDateFilter");
    private final static QName _LinkCount_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "LinkCount");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _SiteRoles_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "SiteRoles");
    private final static QName _CountryRegionSettingsCountryRegionSettingsType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CountryRegionSettings.CountryRegionSettingsType");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _LinkCounts_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "LinkCounts");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _RefreshReason_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "RefreshReason");
    private final static QName _ArrayOfKeyword_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfKeyword");
    private final static QName _SiteMoveSettingsScope_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", "SiteMoveSettings.Scope");
    private final static QName _ArrayOfBlockedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfBlockedUrl");
    private final static QName _PagePreviewAction_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "PagePreviewAction");
    private final static QName _ArrayOfUrlTrafficInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfUrlTrafficInfo");
    private final static QName _QueryStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "QueryStats");
    private final static QName _ArrayOfFetchedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfFetchedUrl");
    private final static QName _ArrayOfDisavowedLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfDisavowedLink");
    private final static QName _DetailedQueryStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DetailedQueryStats");
    private final static QName _DeepLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DeepLink");
    private final static QName _DisavowedLinkDisavowUrlEntityType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DisavowedLink.DisavowUrlEntityType");
    private final static QName _ApiErrorCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ApiErrorCode");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _PagePreview_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "PagePreview");
    private final static QName _SiteRolesUserRole_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", "SiteRoles.UserRole");
    private final static QName _ArrayOfDeepLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfDeepLink");
    private final static QName _Keyword_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Keyword");
    private final static QName _UrlWithCrawlIssuesCrawlIssues_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", "UrlWithCrawlIssues.CrawlIssues");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _DeepLinkBlock_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "DeepLinkBlock");
    private final static QName _UrlWithCrawlIssues_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "UrlWithCrawlIssues");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _HttpCodeFilters_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "HttpCodeFilters");
    private final static QName _ArrayOfFeed_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfFeed");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _SiteMoveSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "SiteMoveSettings");
    private final static QName _ApiFault_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ApiFault");
    private final static QName _ArrayOfUrlWithCrawlIssues_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfUrlWithCrawlIssues");
    private final static QName _LinkDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "LinkDetails");
    private final static QName _DocFlagsFilters_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DocFlagsFilters");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _CountryRegionSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CountryRegionSettings");
    private final static QName _DeepLinkAlgoUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DeepLinkAlgoUrl");
    private final static QName _BlockedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "BlockedUrl");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Site_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Site");
    private final static QName _QueryParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "QueryParameter");
    private final static QName _ArrayOfSiteRoles_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfSiteRoles");
    private final static QName _CrawlStats_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CrawlStats");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _BlockedUrlBlockedUrlRequestType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "BlockedUrl.BlockedUrlRequestType");
    private final static QName _ArrayOfCountryRegionSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "ArrayOfCountryRegionSettings");
    private final static QName _ConnectedSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "ConnectedSite");
    private final static QName _GetUrlInfoResponseGetUrlInfoResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetUrlInfoResult");
    private final static QName _GetCountryRegionSettingsResponseGetCountryRegionSettingsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetCountryRegionSettingsResult");
    private final static QName _PagePreviewUserId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "UserId");
    private final static QName _PagePreviewSiteUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "SiteUrl");
    private final static QName _PagePreviewReason_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "Reason");
    private final static QName _PagePreviewUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "Url");
    private final static QName _FetchUrlUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "url");
    private final static QName _FetchUrlSiteUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "siteUrl");
    private final static QName _GetPageQueryStatsPage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "page");
    private final static QName _GetQueryParametersResponseGetQueryParametersResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetQueryParametersResult");
    private final static QName _GetRelatedKeywordsLanguage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "language");
    private final static QName _GetRelatedKeywordsQ_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "q");
    private final static QName _GetRelatedKeywordsCountry_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "country");
    private final static QName _GetConnectedPagesResponseGetConnectedPagesResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetConnectedPagesResult");
    private final static QName _KeywordStatsQuery_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Query");
    private final static QName _GetQueryTrafficStatsResponseGetQueryTrafficStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetQueryTrafficStatsResult");
    private final static QName _LinkDetailUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Url");
    private final static QName _LinkDetailAnchorText_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "AnchorText");
    private final static QName _DisavowedLinkDisavowLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DisavowLink");
    private final static QName _DisavowedLinkDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Date");
    private final static QName _GetCrawlStatsResponseGetCrawlStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetCrawlStatsResult");
    private final static QName _GetChildrenUrlTrafficInfoResponseGetChildrenUrlTrafficInfoResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetChildrenUrlTrafficInfoResult");
    private final static QName _AddDisavowedLinkDisavowUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "disavowUrl");
    private final static QName _RemoveCountryRegionSettingsSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "settings");
    private final static QName _RemoveFeedFeedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "feedUrl");
    private final static QName _RemoveQueryParameterQueryParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "queryParameter");
    private final static QName _RemoveBlockedUrlBlockedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "blockedUrl");
    private final static QName _GetUserSitesResponseGetUserSitesResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetUserSitesResult");
    private final static QName _GetUrlLinksResponseGetUrlLinksResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetUrlLinksResult");
    private final static QName _GetChildrenUrlInfoResponseGetChildrenUrlInfoResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetChildrenUrlInfoResult");
    private final static QName _FeedStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Status");
    private final static QName _FeedType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Type");
    private final static QName _GetDeepLinkAlgoUrlsResponseGetDeepLinkAlgoUrlsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetDeepLinkAlgoUrlsResult");
    private final static QName _SiteRolesDelegatorEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DelegatorEmail");
    private final static QName _SiteRolesDelegatedCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DelegatedCode");
    private final static QName _SiteRolesVerificationSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "VerificationSite");
    private final static QName _SiteRolesEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Email");
    private final static QName _SiteRolesDelegatedCodeOwnerEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DelegatedCodeOwnerEmail");
    private final static QName _CountryRegionSettingsTwoLetterIsoCountryCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "TwoLetterIsoCountryCode");
    private final static QName _SiteMoveSettingsTargetUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "TargetUrl");
    private final static QName _SiteMoveSettingsSourceUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "SourceUrl");
    private final static QName _GetBlockedUrlsResponseGetBlockedUrlsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetBlockedUrlsResult");
    private final static QName _GetQueryPageDetailStatsResponseGetQueryPageDetailStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetQueryPageDetailStatsResult");
    private final static QName _GetKeywordResponseGetKeywordResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetKeywordResult");
    private final static QName _AddConnectedPageMasterUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "masterUrl");
    private final static QName _CrawlSettingsCrawlRate_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "CrawlRate");
    private final static QName _GetActivePagePreviewBlocksResponseGetActivePagePreviewBlocksResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetActivePagePreviewBlocksResult");
    private final static QName _QueryParameterParameter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Parameter");
    private final static QName _GetQueryPageStatsResponseGetQueryPageStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetQueryPageStatsResult");
    private final static QName _GetPageQueryStatsResponseGetPageQueryStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetPageQueryStatsResult");
    private final static QName _GetUrlTrafficInfoResponseGetUrlTrafficInfoResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetUrlTrafficInfoResult");
    private final static QName _GetQueryPageStatsQuery_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "query");
    private final static QName _RemoveDeepLinkBlockSearchUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "searchUrl");
    private final static QName _RemoveDeepLinkBlockMarket_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "market");
    private final static QName _RemoveDeepLinkBlockDeepLinkUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "deepLinkUrl");
    private final static QName _GetSiteMovesResponseGetSiteMovesResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetSiteMovesResult");
    private final static QName _LinkDetailsDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Details");
    private final static QName _GetRankAndTrafficStatsResponseGetRankAndTrafficStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetRankAndTrafficStatsResult");
    private final static QName _DeepLinkBlockDeepLinkUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "DeepLinkUrl");
    private final static QName _DeepLinkBlockSearchUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "SearchUrl");
    private final static QName _DeepLinkBlockMarket_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "Market");
    private final static QName _RemoveDisavowLinkUrlsToProcess_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "urlsToProcess");
    private final static QName _SaveCrawlSettingsCrawlSettings_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "crawlSettings");
    private final static QName _GetPageStatsResponseGetPageStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetPageStatsResult");
    private final static QName _GetLinkCountsResponseGetLinkCountsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetLinkCountsResult");
    private final static QName _FetchedUrlDetailsDocument_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Document");
    private final static QName _FetchedUrlDetailsHeaders_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Headers");
    private final static QName _GetCrawlIssuesResponseGetCrawlIssuesResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetCrawlIssuesResult");
    private final static QName _UpdateDeepLinkAlgoUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "algoUrl");
    private final static QName _UpdateDeepLinkDeepLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "deepLink");
    private final static QName _GetKeywordStatsResponseGetKeywordStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetKeywordStatsResult");
    private final static QName _GetFeedDetailsResponseGetFeedDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetFeedDetailsResult");
    private final static QName _LinkCountsLinks_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Links");
    private final static QName _DeepLinkTitle_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Title");
    private final static QName _GetChildrenUrlInfoFilterProperties_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "filterProperties");
    private final static QName _SiteDnsVerificationCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "DnsVerificationCode");
    private final static QName _SiteAuthenticationCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "AuthenticationCode");
    private final static QName _AddSiteRolesUserEmail_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "userEmail");
    private final static QName _AddSiteRolesDelegatedUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "delegatedUrl");
    private final static QName _AddSiteRolesAuthenticationCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "authenticationCode");
    private final static QName _GetSiteRolesResponseGetSiteRolesResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetSiteRolesResult");
    private final static QName _GetFetchedUrlsResponseGetFetchedUrlsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetFetchedUrlsResult");
    private final static QName _GetCrawlSettingsResponseGetCrawlSettingsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetCrawlSettingsResult");
    private final static QName _GetDeepLinkResponseGetDeepLinkResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetDeepLinkResult");
    private final static QName _ApiFaultMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "Message");
    private final static QName _GetFeedsResponseGetFeedsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetFeedsResult");
    private final static QName _GetDisavowedLinksResponseGetDisavowedLinksResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetDisavowedLinksResult");
    private final static QName _GetFetchedUrlDetailsResponseGetFetchedUrlDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetFetchedUrlDetailsResult");
    private final static QName _ConnectedSiteIsDeepLaunchSupported_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "IsDeepLaunchSupported");
    private final static QName _ConnectedSiteActualMasterSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "ActualMasterSite");
    private final static QName _ConnectedSiteAppId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "AppId");
    private final static QName _ConnectedSiteIsBlocked_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "IsBlocked");
    private final static QName _ConnectedSiteAppName_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "AppName");
    private final static QName _ConnectedSiteIsDeepLaunchSupportedLastModified_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "IsDeepLaunchSupportedLastModified");
    private final static QName _ConnectedSiteIsBlockedLastModified_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "IsBlockedLastModified");
    private final static QName _ConnectedSiteRequestedMasterSite_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", "RequestedMasterSite");
    private final static QName _GetDeepLinkBlocksResponseGetDeepLinkBlocksResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetDeepLinkBlocksResult");
    private final static QName _GetUrlSubmissionQuotaResponseGetUrlSubmissionQuotaResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetUrlSubmissionQuotaResult");
    private final static QName _GetRelatedKeywordsResponseGetRelatedKeywordsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetRelatedKeywordsResult");
    private final static QName _RemoveSiteRoleSiteRole_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "siteRole");
    private final static QName _GetQueryStatsResponseGetQueryStatsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "GetQueryStatsResult");
    private final static QName _GetUrlLinksLink_QNAME = new QName("http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", "link");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fogetti.phish.storm.wsdl.bing.webmaster
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LinkCount }
     * 
     */
    public LinkCount createLinkCount() {
        return new LinkCount();
    }

    /**
     * Create an instance of {@link GetConnectedPages }
     * 
     */
    public GetConnectedPages createGetConnectedPages() {
        return new GetConnectedPages();
    }

    /**
     * Create an instance of {@link SubmitUrlResponse }
     * 
     */
    public SubmitUrlResponse createSubmitUrlResponse() {
        return new SubmitUrlResponse();
    }

    /**
     * Create an instance of {@link AddCountryRegionSettingsResponse }
     * 
     */
    public AddCountryRegionSettingsResponse createAddCountryRegionSettingsResponse() {
        return new AddCountryRegionSettingsResponse();
    }

    /**
     * Create an instance of {@link FetchUrlResponse }
     * 
     */
    public FetchUrlResponse createFetchUrlResponse() {
        return new FetchUrlResponse();
    }

    /**
     * Create an instance of {@link GetChildrenUrlInfo }
     * 
     */
    public GetChildrenUrlInfo createGetChildrenUrlInfo() {
        return new GetChildrenUrlInfo();
    }

    /**
     * Create an instance of {@link FilterProperties }
     * 
     */
    public FilterProperties createFilterProperties() {
        return new FilterProperties();
    }

    /**
     * Create an instance of {@link AddSiteResponse }
     * 
     */
    public AddSiteResponse createAddSiteResponse() {
        return new AddSiteResponse();
    }

    /**
     * Create an instance of {@link GetQueryTrafficStats }
     * 
     */
    public GetQueryTrafficStats createGetQueryTrafficStats() {
        return new GetQueryTrafficStats();
    }

    /**
     * Create an instance of {@link GetFetchedUrlDetails }
     * 
     */
    public GetFetchedUrlDetails createGetFetchedUrlDetails() {
        return new GetFetchedUrlDetails();
    }

    /**
     * Create an instance of {@link ArrayOfQueryStats }
     * 
     */
    public ArrayOfQueryStats createArrayOfQueryStats() {
        return new ArrayOfQueryStats();
    }

    /**
     * Create an instance of {@link ArrayOfSiteMoveSettings }
     * 
     */
    public ArrayOfSiteMoveSettings createArrayOfSiteMoveSettings() {
        return new ArrayOfSiteMoveSettings();
    }

    /**
     * Create an instance of {@link ArrayOfRankAndTrafficStats }
     * 
     */
    public ArrayOfRankAndTrafficStats createArrayOfRankAndTrafficStats() {
        return new ArrayOfRankAndTrafficStats();
    }

    /**
     * Create an instance of {@link ArrayOfCrawlStats }
     * 
     */
    public ArrayOfCrawlStats createArrayOfCrawlStats() {
        return new ArrayOfCrawlStats();
    }

    /**
     * Create an instance of {@link RemovePagePreviewBlockResponse }
     * 
     */
    public RemovePagePreviewBlockResponse createRemovePagePreviewBlockResponse() {
        return new RemovePagePreviewBlockResponse();
    }

    /**
     * Create an instance of {@link GetQueryPageStatsResponse }
     * 
     */
    public GetQueryPageStatsResponse createGetQueryPageStatsResponse() {
        return new GetQueryPageStatsResponse();
    }

    /**
     * Create an instance of {@link GetLinkCounts }
     * 
     */
    public GetLinkCounts createGetLinkCounts() {
        return new GetLinkCounts();
    }

    /**
     * Create an instance of {@link RemoveFeed }
     * 
     */
    public RemoveFeed createRemoveFeed() {
        return new RemoveFeed();
    }

    /**
     * Create an instance of {@link AddSiteRoles }
     * 
     */
    public AddSiteRoles createAddSiteRoles() {
        return new AddSiteRoles();
    }

    /**
     * Create an instance of {@link GetCrawlSettings }
     * 
     */
    public GetCrawlSettings createGetCrawlSettings() {
        return new GetCrawlSettings();
    }

    /**
     * Create an instance of {@link CrawlSettings }
     * 
     */
    public CrawlSettings createCrawlSettings() {
        return new CrawlSettings();
    }

    /**
     * Create an instance of {@link DisavowedLink }
     * 
     */
    public DisavowedLink createDisavowedLink() {
        return new DisavowedLink();
    }

    /**
     * Create an instance of {@link SubmitUrl }
     * 
     */
    public SubmitUrl createSubmitUrl() {
        return new SubmitUrl();
    }

    /**
     * Create an instance of {@link GetQueryPageDetailStats }
     * 
     */
    public GetQueryPageDetailStats createGetQueryPageDetailStats() {
        return new GetQueryPageDetailStats();
    }

    /**
     * Create an instance of {@link ArrayOfLinkCount }
     * 
     */
    public ArrayOfLinkCount createArrayOfLinkCount() {
        return new ArrayOfLinkCount();
    }

    /**
     * Create an instance of {@link ArrayOfDetailedQueryStats }
     * 
     */
    public ArrayOfDetailedQueryStats createArrayOfDetailedQueryStats() {
        return new ArrayOfDetailedQueryStats();
    }

    /**
     * Create an instance of {@link RankAndTrafficStats }
     * 
     */
    public RankAndTrafficStats createRankAndTrafficStats() {
        return new RankAndTrafficStats();
    }

    /**
     * Create an instance of {@link GetDisavowedLinksResponse }
     * 
     */
    public GetDisavowedLinksResponse createGetDisavowedLinksResponse() {
        return new GetDisavowedLinksResponse();
    }

    /**
     * Create an instance of {@link ArrayOfDisavowedLink }
     * 
     */
    public ArrayOfDisavowedLink createArrayOfDisavowedLink() {
        return new ArrayOfDisavowedLink();
    }

    /**
     * Create an instance of {@link KeywordStats }
     * 
     */
    public KeywordStats createKeywordStats() {
        return new KeywordStats();
    }

    /**
     * Create an instance of {@link GetRankAndTrafficStatsResponse }
     * 
     */
    public GetRankAndTrafficStatsResponse createGetRankAndTrafficStatsResponse() {
        return new GetRankAndTrafficStatsResponse();
    }

    /**
     * Create an instance of {@link GetChildrenUrlTrafficInfo }
     * 
     */
    public GetChildrenUrlTrafficInfo createGetChildrenUrlTrafficInfo() {
        return new GetChildrenUrlTrafficInfo();
    }

    /**
     * Create an instance of {@link UrlSubmissionQuota }
     * 
     */
    public UrlSubmissionQuota createUrlSubmissionQuota() {
        return new UrlSubmissionQuota();
    }

    /**
     * Create an instance of {@link GetKeywordResponse }
     * 
     */
    public GetKeywordResponse createGetKeywordResponse() {
        return new GetKeywordResponse();
    }

    /**
     * Create an instance of {@link Keyword }
     * 
     */
    public Keyword createKeyword() {
        return new Keyword();
    }

    /**
     * Create an instance of {@link RemoveFeedResponse }
     * 
     */
    public RemoveFeedResponse createRemoveFeedResponse() {
        return new RemoveFeedResponse();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link QueryParameter }
     * 
     */
    public QueryParameter createQueryParameter() {
        return new QueryParameter();
    }

    /**
     * Create an instance of {@link ArrayOfSiteRoles }
     * 
     */
    public ArrayOfSiteRoles createArrayOfSiteRoles() {
        return new ArrayOfSiteRoles();
    }

    /**
     * Create an instance of {@link AddPagePreviewBlock }
     * 
     */
    public AddPagePreviewBlock createAddPagePreviewBlock() {
        return new AddPagePreviewBlock();
    }

    /**
     * Create an instance of {@link RemoveSiteResponse }
     * 
     */
    public RemoveSiteResponse createRemoveSiteResponse() {
        return new RemoveSiteResponse();
    }

    /**
     * Create an instance of {@link GetCrawlSettingsResponse }
     * 
     */
    public GetCrawlSettingsResponse createGetCrawlSettingsResponse() {
        return new GetCrawlSettingsResponse();
    }

    /**
     * Create an instance of {@link RemoveSite }
     * 
     */
    public RemoveSite createRemoveSite() {
        return new RemoveSite();
    }

    /**
     * Create an instance of {@link CountryRegionSettings }
     * 
     */
    public CountryRegionSettings createCountryRegionSettings() {
        return new CountryRegionSettings();
    }

    /**
     * Create an instance of {@link GetCountryRegionSettings }
     * 
     */
    public GetCountryRegionSettings createGetCountryRegionSettings() {
        return new GetCountryRegionSettings();
    }

    /**
     * Create an instance of {@link DeepLinkAlgoUrl }
     * 
     */
    public DeepLinkAlgoUrl createDeepLinkAlgoUrl() {
        return new DeepLinkAlgoUrl();
    }

    /**
     * Create an instance of {@link GetSiteMoves }
     * 
     */
    public GetSiteMoves createGetSiteMoves() {
        return new GetSiteMoves();
    }

    /**
     * Create an instance of {@link GetSiteRolesResponse }
     * 
     */
    public GetSiteRolesResponse createGetSiteRolesResponse() {
        return new GetSiteRolesResponse();
    }

    /**
     * Create an instance of {@link RemovePagePreviewBlock }
     * 
     */
    public RemovePagePreviewBlock createRemovePagePreviewBlock() {
        return new RemovePagePreviewBlock();
    }

    /**
     * Create an instance of {@link SiteMoveSettings }
     * 
     */
    public SiteMoveSettings createSiteMoveSettings() {
        return new SiteMoveSettings();
    }

    /**
     * Create an instance of {@link ApiFault }
     * 
     */
    public ApiFault createApiFault() {
        return new ApiFault();
    }

    /**
     * Create an instance of {@link ArrayOfUrlWithCrawlIssues }
     * 
     */
    public ArrayOfUrlWithCrawlIssues createArrayOfUrlWithCrawlIssues() {
        return new ArrayOfUrlWithCrawlIssues();
    }

    /**
     * Create an instance of {@link LinkDetails }
     * 
     */
    public LinkDetails createLinkDetails() {
        return new LinkDetails();
    }

    /**
     * Create an instance of {@link GetDisavowedLinks }
     * 
     */
    public GetDisavowedLinks createGetDisavowedLinks() {
        return new GetDisavowedLinks();
    }

    /**
     * Create an instance of {@link RemoveSiteRole }
     * 
     */
    public RemoveSiteRole createRemoveSiteRole() {
        return new RemoveSiteRole();
    }

    /**
     * Create an instance of {@link SiteRoles }
     * 
     */
    public SiteRoles createSiteRoles() {
        return new SiteRoles();
    }

    /**
     * Create an instance of {@link ArrayOfFeed }
     * 
     */
    public ArrayOfFeed createArrayOfFeed() {
        return new ArrayOfFeed();
    }

    /**
     * Create an instance of {@link ArrayOfDeepLink }
     * 
     */
    public ArrayOfDeepLink createArrayOfDeepLink() {
        return new ArrayOfDeepLink();
    }

    /**
     * Create an instance of {@link VerifySiteResponse }
     * 
     */
    public VerifySiteResponse createVerifySiteResponse() {
        return new VerifySiteResponse();
    }

    /**
     * Create an instance of {@link GetDeepLinkBlocksResponse }
     * 
     */
    public GetDeepLinkBlocksResponse createGetDeepLinkBlocksResponse() {
        return new GetDeepLinkBlocksResponse();
    }

    /**
     * Create an instance of {@link ArrayOfDeepLinkBlock }
     * 
     */
    public ArrayOfDeepLinkBlock createArrayOfDeepLinkBlock() {
        return new ArrayOfDeepLinkBlock();
    }

    /**
     * Create an instance of {@link AddCountryRegionSettings }
     * 
     */
    public AddCountryRegionSettings createAddCountryRegionSettings() {
        return new AddCountryRegionSettings();
    }

    /**
     * Create an instance of {@link DetailedQueryStats }
     * 
     */
    public DetailedQueryStats createDetailedQueryStats() {
        return new DetailedQueryStats();
    }

    /**
     * Create an instance of {@link RemoveBlockedUrlResponse }
     * 
     */
    public RemoveBlockedUrlResponse createRemoveBlockedUrlResponse() {
        return new RemoveBlockedUrlResponse();
    }

    /**
     * Create an instance of {@link DeepLink }
     * 
     */
    public DeepLink createDeepLink() {
        return new DeepLink();
    }

    /**
     * Create an instance of {@link ArrayOfBlockedUrl }
     * 
     */
    public ArrayOfBlockedUrl createArrayOfBlockedUrl() {
        return new ArrayOfBlockedUrl();
    }

    /**
     * Create an instance of {@link SubmitSiteMove }
     * 
     */
    public SubmitSiteMove createSubmitSiteMove() {
        return new SubmitSiteMove();
    }

    /**
     * Create an instance of {@link AddConnectedPageResponse }
     * 
     */
    public AddConnectedPageResponse createAddConnectedPageResponse() {
        return new AddConnectedPageResponse();
    }

    /**
     * Create an instance of {@link GetUserSitesResponse }
     * 
     */
    public GetUserSitesResponse createGetUserSitesResponse() {
        return new GetUserSitesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSite }
     * 
     */
    public ArrayOfSite createArrayOfSite() {
        return new ArrayOfSite();
    }

    /**
     * Create an instance of {@link GetDeepLinkBlocks }
     * 
     */
    public GetDeepLinkBlocks createGetDeepLinkBlocks() {
        return new GetDeepLinkBlocks();
    }

    /**
     * Create an instance of {@link GetFeedDetailsResponse }
     * 
     */
    public GetFeedDetailsResponse createGetFeedDetailsResponse() {
        return new GetFeedDetailsResponse();
    }

    /**
     * Create an instance of {@link GetPageQueryStatsResponse }
     * 
     */
    public GetPageQueryStatsResponse createGetPageQueryStatsResponse() {
        return new GetPageQueryStatsResponse();
    }

    /**
     * Create an instance of {@link GetBlockedUrls }
     * 
     */
    public GetBlockedUrls createGetBlockedUrls() {
        return new GetBlockedUrls();
    }

    /**
     * Create an instance of {@link GetFetchedUrlDetailsResponse }
     * 
     */
    public GetFetchedUrlDetailsResponse createGetFetchedUrlDetailsResponse() {
        return new GetFetchedUrlDetailsResponse();
    }

    /**
     * Create an instance of {@link FetchedUrlDetails }
     * 
     */
    public FetchedUrlDetails createFetchedUrlDetails() {
        return new FetchedUrlDetails();
    }

    /**
     * Create an instance of {@link LinkCounts }
     * 
     */
    public LinkCounts createLinkCounts() {
        return new LinkCounts();
    }

    /**
     * Create an instance of {@link AddBlockedUrlResponse }
     * 
     */
    public AddBlockedUrlResponse createAddBlockedUrlResponse() {
        return new AddBlockedUrlResponse();
    }

    /**
     * Create an instance of {@link GetQueryStatsResponse }
     * 
     */
    public GetQueryStatsResponse createGetQueryStatsResponse() {
        return new GetQueryStatsResponse();
    }

    /**
     * Create an instance of {@link GetQueryPageStats }
     * 
     */
    public GetQueryPageStats createGetQueryPageStats() {
        return new GetQueryPageStats();
    }

    /**
     * Create an instance of {@link SubmitSiteMoveResponse }
     * 
     */
    public SubmitSiteMoveResponse createSubmitSiteMoveResponse() {
        return new SubmitSiteMoveResponse();
    }

    /**
     * Create an instance of {@link ArrayOfKeyword }
     * 
     */
    public ArrayOfKeyword createArrayOfKeyword() {
        return new ArrayOfKeyword();
    }

    /**
     * Create an instance of {@link RemoveDeepLinkBlock }
     * 
     */
    public RemoveDeepLinkBlock createRemoveDeepLinkBlock() {
        return new RemoveDeepLinkBlock();
    }

    /**
     * Create an instance of {@link AddDeepLinkBlock }
     * 
     */
    public AddDeepLinkBlock createAddDeepLinkBlock() {
        return new AddDeepLinkBlock();
    }

    /**
     * Create an instance of {@link GetPageStats }
     * 
     */
    public GetPageStats createGetPageStats() {
        return new GetPageStats();
    }

    /**
     * Create an instance of {@link FetchUrl }
     * 
     */
    public FetchUrl createFetchUrl() {
        return new FetchUrl();
    }

    /**
     * Create an instance of {@link AddBlockedUrl }
     * 
     */
    public AddBlockedUrl createAddBlockedUrl() {
        return new AddBlockedUrl();
    }

    /**
     * Create an instance of {@link BlockedUrl }
     * 
     */
    public BlockedUrl createBlockedUrl() {
        return new BlockedUrl();
    }

    /**
     * Create an instance of {@link GetPageStatsResponse }
     * 
     */
    public GetPageStatsResponse createGetPageStatsResponse() {
        return new GetPageStatsResponse();
    }

    /**
     * Create an instance of {@link GetDeepLinkResponse }
     * 
     */
    public GetDeepLinkResponse createGetDeepLinkResponse() {
        return new GetDeepLinkResponse();
    }

    /**
     * Create an instance of {@link GetUrlSubmissionQuota }
     * 
     */
    public GetUrlSubmissionQuota createGetUrlSubmissionQuota() {
        return new GetUrlSubmissionQuota();
    }

    /**
     * Create an instance of {@link GetUrlTrafficInfoResponse }
     * 
     */
    public GetUrlTrafficInfoResponse createGetUrlTrafficInfoResponse() {
        return new GetUrlTrafficInfoResponse();
    }

    /**
     * Create an instance of {@link UrlTrafficInfo }
     * 
     */
    public UrlTrafficInfo createUrlTrafficInfo() {
        return new UrlTrafficInfo();
    }

    /**
     * Create an instance of {@link EnableDisableQueryParameter }
     * 
     */
    public EnableDisableQueryParameter createEnableDisableQueryParameter() {
        return new EnableDisableQueryParameter();
    }

    /**
     * Create an instance of {@link RemoveDeepLinkBlockResponse }
     * 
     */
    public RemoveDeepLinkBlockResponse createRemoveDeepLinkBlockResponse() {
        return new RemoveDeepLinkBlockResponse();
    }

    /**
     * Create an instance of {@link GetRelatedKeywords }
     * 
     */
    public GetRelatedKeywords createGetRelatedKeywords() {
        return new GetRelatedKeywords();
    }

    /**
     * Create an instance of {@link GetFetchedUrls }
     * 
     */
    public GetFetchedUrls createGetFetchedUrls() {
        return new GetFetchedUrls();
    }

    /**
     * Create an instance of {@link GetActivePagePreviewBlocksResponse }
     * 
     */
    public GetActivePagePreviewBlocksResponse createGetActivePagePreviewBlocksResponse() {
        return new GetActivePagePreviewBlocksResponse();
    }

    /**
     * Create an instance of {@link ArrayOfPagePreview }
     * 
     */
    public ArrayOfPagePreview createArrayOfPagePreview() {
        return new ArrayOfPagePreview();
    }

    /**
     * Create an instance of {@link GetFeedDetails }
     * 
     */
    public GetFeedDetails createGetFeedDetails() {
        return new GetFeedDetails();
    }

    /**
     * Create an instance of {@link RemoveDisavowLinkResponse }
     * 
     */
    public RemoveDisavowLinkResponse createRemoveDisavowLinkResponse() {
        return new RemoveDisavowLinkResponse();
    }

    /**
     * Create an instance of {@link UrlInfo }
     * 
     */
    public UrlInfo createUrlInfo() {
        return new UrlInfo();
    }

    /**
     * Create an instance of {@link GetDeepLinkAlgoUrlsResponse }
     * 
     */
    public GetDeepLinkAlgoUrlsResponse createGetDeepLinkAlgoUrlsResponse() {
        return new GetDeepLinkAlgoUrlsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfDeepLinkAlgoUrl }
     * 
     */
    public ArrayOfDeepLinkAlgoUrl createArrayOfDeepLinkAlgoUrl() {
        return new ArrayOfDeepLinkAlgoUrl();
    }

    /**
     * Create an instance of {@link GetFetchedUrlsResponse }
     * 
     */
    public GetFetchedUrlsResponse createGetFetchedUrlsResponse() {
        return new GetFetchedUrlsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfFetchedUrl }
     * 
     */
    public ArrayOfFetchedUrl createArrayOfFetchedUrl() {
        return new ArrayOfFetchedUrl();
    }

    /**
     * Create an instance of {@link GetSiteRoles }
     * 
     */
    public GetSiteRoles createGetSiteRoles() {
        return new GetSiteRoles();
    }

    /**
     * Create an instance of {@link AddDisavowedLinkResponse }
     * 
     */
    public AddDisavowedLinkResponse createAddDisavowedLinkResponse() {
        return new AddDisavowedLinkResponse();
    }

    /**
     * Create an instance of {@link GetChildrenUrlTrafficInfoResponse }
     * 
     */
    public GetChildrenUrlTrafficInfoResponse createGetChildrenUrlTrafficInfoResponse() {
        return new GetChildrenUrlTrafficInfoResponse();
    }

    /**
     * Create an instance of {@link ArrayOfUrlTrafficInfo }
     * 
     */
    public ArrayOfUrlTrafficInfo createArrayOfUrlTrafficInfo() {
        return new ArrayOfUrlTrafficInfo();
    }

    /**
     * Create an instance of {@link GetKeywordStats }
     * 
     */
    public GetKeywordStats createGetKeywordStats() {
        return new GetKeywordStats();
    }

    /**
     * Create an instance of {@link SaveCrawlSettingsResponse }
     * 
     */
    public SaveCrawlSettingsResponse createSaveCrawlSettingsResponse() {
        return new SaveCrawlSettingsResponse();
    }

    /**
     * Create an instance of {@link UpdateDeepLink }
     * 
     */
    public UpdateDeepLink createUpdateDeepLink() {
        return new UpdateDeepLink();
    }

    /**
     * Create an instance of {@link ArrayOfKeywordStats }
     * 
     */
    public ArrayOfKeywordStats createArrayOfKeywordStats() {
        return new ArrayOfKeywordStats();
    }

    /**
     * Create an instance of {@link GetPageQueryStats }
     * 
     */
    public GetPageQueryStats createGetPageQueryStats() {
        return new GetPageQueryStats();
    }

    /**
     * Create an instance of {@link Feed }
     * 
     */
    public Feed createFeed() {
        return new Feed();
    }

    /**
     * Create an instance of {@link GetDeepLinkAlgoUrls }
     * 
     */
    public GetDeepLinkAlgoUrls createGetDeepLinkAlgoUrls() {
        return new GetDeepLinkAlgoUrls();
    }

    /**
     * Create an instance of {@link GetCountryRegionSettingsResponse }
     * 
     */
    public GetCountryRegionSettingsResponse createGetCountryRegionSettingsResponse() {
        return new GetCountryRegionSettingsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCountryRegionSettings }
     * 
     */
    public ArrayOfCountryRegionSettings createArrayOfCountryRegionSettings() {
        return new ArrayOfCountryRegionSettings();
    }

    /**
     * Create an instance of {@link LinkDetail }
     * 
     */
    public LinkDetail createLinkDetail() {
        return new LinkDetail();
    }

    /**
     * Create an instance of {@link GetCrawlStats }
     * 
     */
    public GetCrawlStats createGetCrawlStats() {
        return new GetCrawlStats();
    }

    /**
     * Create an instance of {@link SaveCrawlSettings }
     * 
     */
    public SaveCrawlSettings createSaveCrawlSettings() {
        return new SaveCrawlSettings();
    }

    /**
     * Create an instance of {@link ArrayOfLinkDetail }
     * 
     */
    public ArrayOfLinkDetail createArrayOfLinkDetail() {
        return new ArrayOfLinkDetail();
    }

    /**
     * Create an instance of {@link GetQueryParametersResponse }
     * 
     */
    public GetQueryParametersResponse createGetQueryParametersResponse() {
        return new GetQueryParametersResponse();
    }

    /**
     * Create an instance of {@link ArrayOfQueryParameter }
     * 
     */
    public ArrayOfQueryParameter createArrayOfQueryParameter() {
        return new ArrayOfQueryParameter();
    }

    /**
     * Create an instance of {@link AddSite }
     * 
     */
    public AddSite createAddSite() {
        return new AddSite();
    }

    /**
     * Create an instance of {@link ArrayOfUrlInfo }
     * 
     */
    public ArrayOfUrlInfo createArrayOfUrlInfo() {
        return new ArrayOfUrlInfo();
    }

    /**
     * Create an instance of {@link FetchedUrl }
     * 
     */
    public FetchedUrl createFetchedUrl() {
        return new FetchedUrl();
    }

    /**
     * Create an instance of {@link GetLinkCountsResponse }
     * 
     */
    public GetLinkCountsResponse createGetLinkCountsResponse() {
        return new GetLinkCountsResponse();
    }

    /**
     * Create an instance of {@link GetSiteMovesResponse }
     * 
     */
    public GetSiteMovesResponse createGetSiteMovesResponse() {
        return new GetSiteMovesResponse();
    }

    /**
     * Create an instance of {@link GetUserSites }
     * 
     */
    public GetUserSites createGetUserSites() {
        return new GetUserSites();
    }

    /**
     * Create an instance of {@link AddDisavowedLink }
     * 
     */
    public AddDisavowedLink createAddDisavowedLink() {
        return new AddDisavowedLink();
    }

    /**
     * Create an instance of {@link AddSiteRolesResponse }
     * 
     */
    public AddSiteRolesResponse createAddSiteRolesResponse() {
        return new AddSiteRolesResponse();
    }

    /**
     * Create an instance of {@link GetCrawlIssues }
     * 
     */
    public GetCrawlIssues createGetCrawlIssues() {
        return new GetCrawlIssues();
    }

    /**
     * Create an instance of {@link GetQueryTrafficStatsResponse }
     * 
     */
    public GetQueryTrafficStatsResponse createGetQueryTrafficStatsResponse() {
        return new GetQueryTrafficStatsResponse();
    }

    /**
     * Create an instance of {@link GetCrawlStatsResponse }
     * 
     */
    public GetCrawlStatsResponse createGetCrawlStatsResponse() {
        return new GetCrawlStatsResponse();
    }

    /**
     * Create an instance of {@link GetActivePagePreviewBlocks }
     * 
     */
    public GetActivePagePreviewBlocks createGetActivePagePreviewBlocks() {
        return new GetActivePagePreviewBlocks();
    }

    /**
     * Create an instance of {@link GetFeeds }
     * 
     */
    public GetFeeds createGetFeeds() {
        return new GetFeeds();
    }

    /**
     * Create an instance of {@link AddDeepLinkBlockResponse }
     * 
     */
    public AddDeepLinkBlockResponse createAddDeepLinkBlockResponse() {
        return new AddDeepLinkBlockResponse();
    }

    /**
     * Create an instance of {@link GetQueryStats }
     * 
     */
    public GetQueryStats createGetQueryStats() {
        return new GetQueryStats();
    }

    /**
     * Create an instance of {@link VerifySite }
     * 
     */
    public VerifySite createVerifySite() {
        return new VerifySite();
    }

    /**
     * Create an instance of {@link GetUrlLinks }
     * 
     */
    public GetUrlLinks createGetUrlLinks() {
        return new GetUrlLinks();
    }

    /**
     * Create an instance of {@link GetUrlSubmissionQuotaResponse }
     * 
     */
    public GetUrlSubmissionQuotaResponse createGetUrlSubmissionQuotaResponse() {
        return new GetUrlSubmissionQuotaResponse();
    }

    /**
     * Create an instance of {@link RemoveCountryRegionSettings }
     * 
     */
    public RemoveCountryRegionSettings createRemoveCountryRegionSettings() {
        return new RemoveCountryRegionSettings();
    }

    /**
     * Create an instance of {@link AddQueryParameterResponse }
     * 
     */
    public AddQueryParameterResponse createAddQueryParameterResponse() {
        return new AddQueryParameterResponse();
    }

    /**
     * Create an instance of {@link RemoveCountryRegionSettingsResponse }
     * 
     */
    public RemoveCountryRegionSettingsResponse createRemoveCountryRegionSettingsResponse() {
        return new RemoveCountryRegionSettingsResponse();
    }

    /**
     * Create an instance of {@link RemoveQueryParameter }
     * 
     */
    public RemoveQueryParameter createRemoveQueryParameter() {
        return new RemoveQueryParameter();
    }

    /**
     * Create an instance of {@link GetKeywordStatsResponse }
     * 
     */
    public GetKeywordStatsResponse createGetKeywordStatsResponse() {
        return new GetKeywordStatsResponse();
    }

    /**
     * Create an instance of {@link CrawlStats }
     * 
     */
    public CrawlStats createCrawlStats() {
        return new CrawlStats();
    }

    /**
     * Create an instance of {@link GetKeyword }
     * 
     */
    public GetKeyword createGetKeyword() {
        return new GetKeyword();
    }

    /**
     * Create an instance of {@link GetUrlLinksResponse }
     * 
     */
    public GetUrlLinksResponse createGetUrlLinksResponse() {
        return new GetUrlLinksResponse();
    }

    /**
     * Create an instance of {@link EnableDisableQueryParameterResponse }
     * 
     */
    public EnableDisableQueryParameterResponse createEnableDisableQueryParameterResponse() {
        return new EnableDisableQueryParameterResponse();
    }

    /**
     * Create an instance of {@link GetFeedsResponse }
     * 
     */
    public GetFeedsResponse createGetFeedsResponse() {
        return new GetFeedsResponse();
    }

    /**
     * Create an instance of {@link GetCrawlIssuesResponse }
     * 
     */
    public GetCrawlIssuesResponse createGetCrawlIssuesResponse() {
        return new GetCrawlIssuesResponse();
    }

    /**
     * Create an instance of {@link SubmitFeedResponse }
     * 
     */
    public SubmitFeedResponse createSubmitFeedResponse() {
        return new SubmitFeedResponse();
    }

    /**
     * Create an instance of {@link GetRelatedKeywordsResponse }
     * 
     */
    public GetRelatedKeywordsResponse createGetRelatedKeywordsResponse() {
        return new GetRelatedKeywordsResponse();
    }

    /**
     * Create an instance of {@link AddPagePreviewBlockResponse }
     * 
     */
    public AddPagePreviewBlockResponse createAddPagePreviewBlockResponse() {
        return new AddPagePreviewBlockResponse();
    }

    /**
     * Create an instance of {@link RemoveSiteRoleResponse }
     * 
     */
    public RemoveSiteRoleResponse createRemoveSiteRoleResponse() {
        return new RemoveSiteRoleResponse();
    }

    /**
     * Create an instance of {@link UpdateDeepLinkResponse }
     * 
     */
    public UpdateDeepLinkResponse createUpdateDeepLinkResponse() {
        return new UpdateDeepLinkResponse();
    }

    /**
     * Create an instance of {@link RemoveQueryParameterResponse }
     * 
     */
    public RemoveQueryParameterResponse createRemoveQueryParameterResponse() {
        return new RemoveQueryParameterResponse();
    }

    /**
     * Create an instance of {@link GetUrlInfo }
     * 
     */
    public GetUrlInfo createGetUrlInfo() {
        return new GetUrlInfo();
    }

    /**
     * Create an instance of {@link RemoveBlockedUrl }
     * 
     */
    public RemoveBlockedUrl createRemoveBlockedUrl() {
        return new RemoveBlockedUrl();
    }

    /**
     * Create an instance of {@link GetUrlTrafficInfo }
     * 
     */
    public GetUrlTrafficInfo createGetUrlTrafficInfo() {
        return new GetUrlTrafficInfo();
    }

    /**
     * Create an instance of {@link AddQueryParameter }
     * 
     */
    public AddQueryParameter createAddQueryParameter() {
        return new AddQueryParameter();
    }

    /**
     * Create an instance of {@link GetQueryParameters }
     * 
     */
    public GetQueryParameters createGetQueryParameters() {
        return new GetQueryParameters();
    }

    /**
     * Create an instance of {@link AddConnectedPage }
     * 
     */
    public AddConnectedPage createAddConnectedPage() {
        return new AddConnectedPage();
    }

    /**
     * Create an instance of {@link GetConnectedPagesResponse }
     * 
     */
    public GetConnectedPagesResponse createGetConnectedPagesResponse() {
        return new GetConnectedPagesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfConnectedSite }
     * 
     */
    public ArrayOfConnectedSite createArrayOfConnectedSite() {
        return new ArrayOfConnectedSite();
    }

    /**
     * Create an instance of {@link UrlWithCrawlIssues }
     * 
     */
    public UrlWithCrawlIssues createUrlWithCrawlIssues() {
        return new UrlWithCrawlIssues();
    }

    /**
     * Create an instance of {@link GetDeepLink }
     * 
     */
    public GetDeepLink createGetDeepLink() {
        return new GetDeepLink();
    }

    /**
     * Create an instance of {@link GetBlockedUrlsResponse }
     * 
     */
    public GetBlockedUrlsResponse createGetBlockedUrlsResponse() {
        return new GetBlockedUrlsResponse();
    }

    /**
     * Create an instance of {@link GetRankAndTrafficStats }
     * 
     */
    public GetRankAndTrafficStats createGetRankAndTrafficStats() {
        return new GetRankAndTrafficStats();
    }

    /**
     * Create an instance of {@link GetChildrenUrlInfoResponse }
     * 
     */
    public GetChildrenUrlInfoResponse createGetChildrenUrlInfoResponse() {
        return new GetChildrenUrlInfoResponse();
    }

    /**
     * Create an instance of {@link GetUrlInfoResponse }
     * 
     */
    public GetUrlInfoResponse createGetUrlInfoResponse() {
        return new GetUrlInfoResponse();
    }

    /**
     * Create an instance of {@link RemoveDisavowLink }
     * 
     */
    public RemoveDisavowLink createRemoveDisavowLink() {
        return new RemoveDisavowLink();
    }

    /**
     * Create an instance of {@link QueryStats }
     * 
     */
    public QueryStats createQueryStats() {
        return new QueryStats();
    }

    /**
     * Create an instance of {@link SubmitFeed }
     * 
     */
    public SubmitFeed createSubmitFeed() {
        return new SubmitFeed();
    }

    /**
     * Create an instance of {@link GetQueryPageDetailStatsResponse }
     * 
     */
    public GetQueryPageDetailStatsResponse createGetQueryPageDetailStatsResponse() {
        return new GetQueryPageDetailStatsResponse();
    }

    /**
     * Create an instance of {@link DeepLinkBlock }
     * 
     */
    public DeepLinkBlock createDeepLinkBlock() {
        return new DeepLinkBlock();
    }

    /**
     * Create an instance of {@link PagePreview }
     * 
     */
    public PagePreview createPagePreview() {
        return new PagePreview();
    }

    /**
     * Create an instance of {@link ConnectedSite }
     * 
     */
    public ConnectedSite createConnectedSite() {
        return new ConnectedSite();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlSubmissionQuota }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "UrlSubmissionQuota")
    public JAXBElement<UrlSubmissionQuota> createUrlSubmissionQuota(UrlSubmissionQuota value) {
        return new JAXBElement<UrlSubmissionQuota>(_UrlSubmissionQuota_QNAME, UrlSubmissionQuota.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockedUrlBlockedUrlEntityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "BlockedUrl.BlockedUrlEntityType")
    public JAXBElement<BlockedUrlBlockedUrlEntityType> createBlockedUrlBlockedUrlEntityType(BlockedUrlBlockedUrlEntityType value) {
        return new JAXBElement<BlockedUrlBlockedUrlEntityType>(_BlockedUrlBlockedUrlEntityType_QNAME, BlockedUrlBlockedUrlEntityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfQueryParameter")
    public JAXBElement<ArrayOfQueryParameter> createArrayOfQueryParameter(ArrayOfQueryParameter value) {
        return new JAXBElement<ArrayOfQueryParameter>(_ArrayOfQueryParameter_QNAME, ArrayOfQueryParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteVerificationMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.DataContracts", name = "SiteVerificationMethod")
    public JAXBElement<SiteVerificationMethod> createSiteVerificationMethod(SiteVerificationMethod value) {
        return new JAXBElement<SiteVerificationMethod>(_SiteVerificationMethod_QNAME, SiteVerificationMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDetailedQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfDetailedQueryStats")
    public JAXBElement<ArrayOfDetailedQueryStats> createArrayOfDetailedQueryStats(ArrayOfDetailedQueryStats value) {
        return new JAXBElement<ArrayOfDetailedQueryStats>(_ArrayOfDetailedQueryStats_QNAME, ArrayOfDetailedQueryStats.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DiscoveredDateFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DiscoveredDateFilter")
    public JAXBElement<DiscoveredDateFilter> createDiscoveredDateFilter(DiscoveredDateFilter value) {
        return new JAXBElement<DiscoveredDateFilter>(_DiscoveredDateFilter_QNAME, DiscoveredDateFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RankAndTrafficStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "RankAndTrafficStats")
    public JAXBElement<RankAndTrafficStats> createRankAndTrafficStats(RankAndTrafficStats value) {
        return new JAXBElement<RankAndTrafficStats>(_RankAndTrafficStats_QNAME, RankAndTrafficStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "FilterProperties")
    public JAXBElement<FilterProperties> createFilterProperties(FilterProperties value) {
        return new JAXBElement<FilterProperties>(_FilterProperties_QNAME, FilterProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepLinkDeepLinkWeight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DeepLink.DeepLinkWeight")
    public JAXBElement<DeepLinkDeepLinkWeight> createDeepLinkDeepLinkWeight(DeepLinkDeepLinkWeight value) {
        return new JAXBElement<DeepLinkDeepLinkWeight>(_DeepLinkDeepLinkWeight_QNAME, DeepLinkDeepLinkWeight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "KeywordStats")
    public JAXBElement<KeywordStats> createKeywordStats(KeywordStats value) {
        return new JAXBElement<KeywordStats>(_KeywordStats_QNAME, KeywordStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisavowedLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DisavowedLink")
    public JAXBElement<DisavowedLink> createDisavowedLink(DisavowedLink value) {
        return new JAXBElement<DisavowedLink>(_DisavowedLink_QNAME, DisavowedLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfUrlInfo")
    public JAXBElement<ArrayOfUrlInfo> createArrayOfUrlInfo(ArrayOfUrlInfo value) {
        return new JAXBElement<ArrayOfUrlInfo>(_ArrayOfUrlInfo_QNAME, ArrayOfUrlInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkAlgoUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfDeepLinkAlgoUrl")
    public JAXBElement<ArrayOfDeepLinkAlgoUrl> createArrayOfDeepLinkAlgoUrl(ArrayOfDeepLinkAlgoUrl value) {
        return new JAXBElement<ArrayOfDeepLinkAlgoUrl>(_ArrayOfDeepLinkAlgoUrl_QNAME, ArrayOfDeepLinkAlgoUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "FetchedUrl")
    public JAXBElement<FetchedUrl> createFetchedUrl(FetchedUrl value) {
        return new JAXBElement<FetchedUrl>(_FetchedUrl_QNAME, FetchedUrl.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlTrafficInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "UrlTrafficInfo")
    public JAXBElement<UrlTrafficInfo> createUrlTrafficInfo(UrlTrafficInfo value) {
        return new JAXBElement<UrlTrafficInfo>(_UrlTrafficInfo_QNAME, UrlTrafficInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLinkCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfLinkCount")
    public JAXBElement<ArrayOfLinkCount> createArrayOfLinkCount(ArrayOfLinkCount value) {
        return new JAXBElement<ArrayOfLinkCount>(_ArrayOfLinkCount_QNAME, ArrayOfLinkCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchedUrlDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "FetchedUrlDetails")
    public JAXBElement<FetchedUrlDetails> createFetchedUrlDetails(FetchedUrlDetails value) {
        return new JAXBElement<FetchedUrlDetails>(_FetchedUrlDetails_QNAME, FetchedUrlDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "LinkDetail")
    public JAXBElement<LinkDetail> createLinkDetail(LinkDetail value) {
        return new JAXBElement<LinkDetail>(_LinkDetail_QNAME, LinkDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfSite")
    public JAXBElement<ArrayOfSite> createArrayOfSite(ArrayOfSite value) {
        return new JAXBElement<ArrayOfSite>(_ArrayOfSite_QNAME, ArrayOfSite.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConnectedSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "ArrayOfConnectedSite")
    public JAXBElement<ArrayOfConnectedSite> createArrayOfConnectedSite(ArrayOfConnectedSite value) {
        return new JAXBElement<ArrayOfConnectedSite>(_ArrayOfConnectedSite_QNAME, ArrayOfConnectedSite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLinkDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfLinkDetail")
    public JAXBElement<ArrayOfLinkDetail> createArrayOfLinkDetail(ArrayOfLinkDetail value) {
        return new JAXBElement<ArrayOfLinkDetail>(_ArrayOfLinkDetail_QNAME, ArrayOfLinkDetail.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CrawlSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CrawlSettings")
    public JAXBElement<CrawlSettings> createCrawlSettings(CrawlSettings value) {
        return new JAXBElement<CrawlSettings>(_CrawlSettings_QNAME, CrawlSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkBlock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "ArrayOfDeepLinkBlock")
    public JAXBElement<ArrayOfDeepLinkBlock> createArrayOfDeepLinkBlock(ArrayOfDeepLinkBlock value) {
        return new JAXBElement<ArrayOfDeepLinkBlock>(_ArrayOfDeepLinkBlock_QNAME, ArrayOfDeepLinkBlock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCrawlStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfCrawlStats")
    public JAXBElement<ArrayOfCrawlStats> createArrayOfCrawlStats(ArrayOfCrawlStats value) {
        return new JAXBElement<ArrayOfCrawlStats>(_ArrayOfCrawlStats_QNAME, ArrayOfCrawlStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeywordStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfKeywordStats")
    public JAXBElement<ArrayOfKeywordStats> createArrayOfKeywordStats(ArrayOfKeywordStats value) {
        return new JAXBElement<ArrayOfKeywordStats>(_ArrayOfKeywordStats_QNAME, ArrayOfKeywordStats.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockReason }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "BlockReason")
    public JAXBElement<BlockReason> createBlockReason(BlockReason value) {
        return new JAXBElement<BlockReason>(_BlockReason_QNAME, BlockReason.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Feed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Feed")
    public JAXBElement<Feed> createFeed(Feed value) {
        return new JAXBElement<Feed>(_Feed_QNAME, Feed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfQueryStats")
    public JAXBElement<ArrayOfQueryStats> createArrayOfQueryStats(ArrayOfQueryStats value) {
        return new JAXBElement<ArrayOfQueryStats>(_ArrayOfQueryStats_QNAME, ArrayOfQueryStats.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteMoveSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfSiteMoveSettings")
    public JAXBElement<ArrayOfSiteMoveSettings> createArrayOfSiteMoveSettings(ArrayOfSiteMoveSettings value) {
        return new JAXBElement<ArrayOfSiteMoveSettings>(_ArrayOfSiteMoveSettings_QNAME, ArrayOfSiteMoveSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "UrlInfo")
    public JAXBElement<UrlInfo> createUrlInfo(UrlInfo value) {
        return new JAXBElement<UrlInfo>(_UrlInfo_QNAME, UrlInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRankAndTrafficStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfRankAndTrafficStats")
    public JAXBElement<ArrayOfRankAndTrafficStats> createArrayOfRankAndTrafficStats(ArrayOfRankAndTrafficStats value) {
        return new JAXBElement<ArrayOfRankAndTrafficStats>(_ArrayOfRankAndTrafficStats_QNAME, ArrayOfRankAndTrafficStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteMoveSettingsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", name = "SiteMoveSettings.Type")
    public JAXBElement<SiteMoveSettingsType> createSiteMoveSettingsType(SiteMoveSettingsType value) {
        return new JAXBElement<SiteMoveSettingsType>(_SiteMoveSettingsType_QNAME, SiteMoveSettingsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPagePreview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "ArrayOfPagePreview")
    public JAXBElement<ArrayOfPagePreview> createArrayOfPagePreview(ArrayOfPagePreview value) {
        return new JAXBElement<ArrayOfPagePreview>(_ArrayOfPagePreview_QNAME, ArrayOfPagePreview.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CrawlDateFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CrawlDateFilter")
    public JAXBElement<CrawlDateFilter> createCrawlDateFilter(CrawlDateFilter value) {
        return new JAXBElement<CrawlDateFilter>(_CrawlDateFilter_QNAME, CrawlDateFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "LinkCount")
    public JAXBElement<LinkCount> createLinkCount(LinkCount value) {
        return new JAXBElement<LinkCount>(_LinkCount_QNAME, LinkCount.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "SiteRoles")
    public JAXBElement<SiteRoles> createSiteRoles(SiteRoles value) {
        return new JAXBElement<SiteRoles>(_SiteRoles_QNAME, SiteRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryRegionSettingsCountryRegionSettingsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CountryRegionSettings.CountryRegionSettingsType")
    public JAXBElement<CountryRegionSettingsCountryRegionSettingsType> createCountryRegionSettingsCountryRegionSettingsType(CountryRegionSettingsCountryRegionSettingsType value) {
        return new JAXBElement<CountryRegionSettingsCountryRegionSettingsType>(_CountryRegionSettingsCountryRegionSettingsType_QNAME, CountryRegionSettingsCountryRegionSettingsType.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkCounts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "LinkCounts")
    public JAXBElement<LinkCounts> createLinkCounts(LinkCounts value) {
        return new JAXBElement<LinkCounts>(_LinkCounts_QNAME, LinkCounts.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RefreshReason }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "RefreshReason")
    public JAXBElement<RefreshReason> createRefreshReason(RefreshReason value) {
        return new JAXBElement<RefreshReason>(_RefreshReason_QNAME, RefreshReason.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfKeyword")
    public JAXBElement<ArrayOfKeyword> createArrayOfKeyword(ArrayOfKeyword value) {
        return new JAXBElement<ArrayOfKeyword>(_ArrayOfKeyword_QNAME, ArrayOfKeyword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteMoveSettingsScope }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", name = "SiteMoveSettings.Scope")
    public JAXBElement<SiteMoveSettingsScope> createSiteMoveSettingsScope(SiteMoveSettingsScope value) {
        return new JAXBElement<SiteMoveSettingsScope>(_SiteMoveSettingsScope_QNAME, SiteMoveSettingsScope.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBlockedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfBlockedUrl")
    public JAXBElement<ArrayOfBlockedUrl> createArrayOfBlockedUrl(ArrayOfBlockedUrl value) {
        return new JAXBElement<ArrayOfBlockedUrl>(_ArrayOfBlockedUrl_QNAME, ArrayOfBlockedUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagePreviewAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "PagePreviewAction")
    public JAXBElement<PagePreviewAction> createPagePreviewAction(PagePreviewAction value) {
        return new JAXBElement<PagePreviewAction>(_PagePreviewAction_QNAME, PagePreviewAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlTrafficInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfUrlTrafficInfo")
    public JAXBElement<ArrayOfUrlTrafficInfo> createArrayOfUrlTrafficInfo(ArrayOfUrlTrafficInfo value) {
        return new JAXBElement<ArrayOfUrlTrafficInfo>(_ArrayOfUrlTrafficInfo_QNAME, ArrayOfUrlTrafficInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "QueryStats")
    public JAXBElement<QueryStats> createQueryStats(QueryStats value) {
        return new JAXBElement<QueryStats>(_QueryStats_QNAME, QueryStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFetchedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfFetchedUrl")
    public JAXBElement<ArrayOfFetchedUrl> createArrayOfFetchedUrl(ArrayOfFetchedUrl value) {
        return new JAXBElement<ArrayOfFetchedUrl>(_ArrayOfFetchedUrl_QNAME, ArrayOfFetchedUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDisavowedLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfDisavowedLink")
    public JAXBElement<ArrayOfDisavowedLink> createArrayOfDisavowedLink(ArrayOfDisavowedLink value) {
        return new JAXBElement<ArrayOfDisavowedLink>(_ArrayOfDisavowedLink_QNAME, ArrayOfDisavowedLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailedQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DetailedQueryStats")
    public JAXBElement<DetailedQueryStats> createDetailedQueryStats(DetailedQueryStats value) {
        return new JAXBElement<DetailedQueryStats>(_DetailedQueryStats_QNAME, DetailedQueryStats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DeepLink")
    public JAXBElement<DeepLink> createDeepLink(DeepLink value) {
        return new JAXBElement<DeepLink>(_DeepLink_QNAME, DeepLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisavowedLinkDisavowUrlEntityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DisavowedLink.DisavowUrlEntityType")
    public JAXBElement<DisavowedLinkDisavowUrlEntityType> createDisavowedLinkDisavowUrlEntityType(DisavowedLinkDisavowUrlEntityType value) {
        return new JAXBElement<DisavowedLinkDisavowUrlEntityType>(_DisavowedLinkDisavowUrlEntityType_QNAME, DisavowedLinkDisavowUrlEntityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiErrorCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ApiErrorCode")
    public JAXBElement<ApiErrorCode> createApiErrorCode(ApiErrorCode value) {
        return new JAXBElement<ApiErrorCode>(_ApiErrorCode_QNAME, ApiErrorCode.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PagePreview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "PagePreview")
    public JAXBElement<PagePreview> createPagePreview(PagePreview value) {
        return new JAXBElement<PagePreview>(_PagePreview_QNAME, PagePreview.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteRolesUserRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", name = "SiteRoles.UserRole")
    public JAXBElement<SiteRolesUserRole> createSiteRolesUserRole(SiteRolesUserRole value) {
        return new JAXBElement<SiteRolesUserRole>(_SiteRolesUserRole_QNAME, SiteRolesUserRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfDeepLink")
    public JAXBElement<ArrayOfDeepLink> createArrayOfDeepLink(ArrayOfDeepLink value) {
        return new JAXBElement<ArrayOfDeepLink>(_ArrayOfDeepLink_QNAME, ArrayOfDeepLink.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keyword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Keyword")
    public JAXBElement<Keyword> createKeyword(Keyword value) {
        return new JAXBElement<Keyword>(_Keyword_QNAME, Keyword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api.Interfaces", name = "UrlWithCrawlIssues.CrawlIssues")
    public JAXBElement<List<String>> createUrlWithCrawlIssuesCrawlIssues(List<String> value) {
        return new JAXBElement<List<String>>(_UrlWithCrawlIssuesCrawlIssues_QNAME, ((Class) List.class), null, ((List<String> ) value));
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepLinkBlock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "DeepLinkBlock")
    public JAXBElement<DeepLinkBlock> createDeepLinkBlock(DeepLinkBlock value) {
        return new JAXBElement<DeepLinkBlock>(_DeepLinkBlock_QNAME, DeepLinkBlock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlWithCrawlIssues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "UrlWithCrawlIssues")
    public JAXBElement<UrlWithCrawlIssues> createUrlWithCrawlIssues(UrlWithCrawlIssues value) {
        return new JAXBElement<UrlWithCrawlIssues>(_UrlWithCrawlIssues_QNAME, UrlWithCrawlIssues.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "HttpCodeFilters")
    public JAXBElement<List<String>> createHttpCodeFilters(List<String> value) {
        return new JAXBElement<List<String>>(_HttpCodeFilters_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfFeed")
    public JAXBElement<ArrayOfFeed> createArrayOfFeed(ArrayOfFeed value) {
        return new JAXBElement<ArrayOfFeed>(_ArrayOfFeed_QNAME, ArrayOfFeed.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteMoveSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "SiteMoveSettings")
    public JAXBElement<SiteMoveSettings> createSiteMoveSettings(SiteMoveSettings value) {
        return new JAXBElement<SiteMoveSettings>(_SiteMoveSettings_QNAME, SiteMoveSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ApiFault")
    public JAXBElement<ApiFault> createApiFault(ApiFault value) {
        return new JAXBElement<ApiFault>(_ApiFault_QNAME, ApiFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlWithCrawlIssues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfUrlWithCrawlIssues")
    public JAXBElement<ArrayOfUrlWithCrawlIssues> createArrayOfUrlWithCrawlIssues(ArrayOfUrlWithCrawlIssues value) {
        return new JAXBElement<ArrayOfUrlWithCrawlIssues>(_ArrayOfUrlWithCrawlIssues_QNAME, ArrayOfUrlWithCrawlIssues.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "LinkDetails")
    public JAXBElement<LinkDetails> createLinkDetails(LinkDetails value) {
        return new JAXBElement<LinkDetails>(_LinkDetails_QNAME, LinkDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DocFlagsFilters")
    public JAXBElement<List<String>> createDocFlagsFilters(List<String> value) {
        return new JAXBElement<List<String>>(_DocFlagsFilters_QNAME, ((Class) List.class), null, ((List<String> ) value));
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryRegionSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CountryRegionSettings")
    public JAXBElement<CountryRegionSettings> createCountryRegionSettings(CountryRegionSettings value) {
        return new JAXBElement<CountryRegionSettings>(_CountryRegionSettings_QNAME, CountryRegionSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepLinkAlgoUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DeepLinkAlgoUrl")
    public JAXBElement<DeepLinkAlgoUrl> createDeepLinkAlgoUrl(DeepLinkAlgoUrl value) {
        return new JAXBElement<DeepLinkAlgoUrl>(_DeepLinkAlgoUrl_QNAME, DeepLinkAlgoUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "BlockedUrl")
    public JAXBElement<BlockedUrl> createBlockedUrl(BlockedUrl value) {
        return new JAXBElement<BlockedUrl>(_BlockedUrl_QNAME, BlockedUrl.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Site }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Site")
    public JAXBElement<Site> createSite(Site value) {
        return new JAXBElement<Site>(_Site_QNAME, Site.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "QueryParameter")
    public JAXBElement<QueryParameter> createQueryParameter(QueryParameter value) {
        return new JAXBElement<QueryParameter>(_QueryParameter_QNAME, QueryParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfSiteRoles")
    public JAXBElement<ArrayOfSiteRoles> createArrayOfSiteRoles(ArrayOfSiteRoles value) {
        return new JAXBElement<ArrayOfSiteRoles>(_ArrayOfSiteRoles_QNAME, ArrayOfSiteRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrawlStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CrawlStats")
    public JAXBElement<CrawlStats> createCrawlStats(CrawlStats value) {
        return new JAXBElement<CrawlStats>(_CrawlStats_QNAME, CrawlStats.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockedUrlBlockedUrlRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "BlockedUrl.BlockedUrlRequestType")
    public JAXBElement<BlockedUrlBlockedUrlRequestType> createBlockedUrlBlockedUrlRequestType(BlockedUrlBlockedUrlRequestType value) {
        return new JAXBElement<BlockedUrlBlockedUrlRequestType>(_BlockedUrlBlockedUrlRequestType_QNAME, BlockedUrlBlockedUrlRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCountryRegionSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "ArrayOfCountryRegionSettings")
    public JAXBElement<ArrayOfCountryRegionSettings> createArrayOfCountryRegionSettings(ArrayOfCountryRegionSettings value) {
        return new JAXBElement<ArrayOfCountryRegionSettings>(_ArrayOfCountryRegionSettings_QNAME, ArrayOfCountryRegionSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectedSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "ConnectedSite")
    public JAXBElement<ConnectedSite> createConnectedSite(ConnectedSite value) {
        return new JAXBElement<ConnectedSite>(_ConnectedSite_QNAME, ConnectedSite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetUrlInfoResult", scope = GetUrlInfoResponse.class)
    public JAXBElement<UrlInfo> createGetUrlInfoResponseGetUrlInfoResult(UrlInfo value) {
        return new JAXBElement<UrlInfo>(_GetUrlInfoResponseGetUrlInfoResult_QNAME, UrlInfo.class, GetUrlInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCountryRegionSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetCountryRegionSettingsResult", scope = GetCountryRegionSettingsResponse.class)
    public JAXBElement<ArrayOfCountryRegionSettings> createGetCountryRegionSettingsResponseGetCountryRegionSettingsResult(ArrayOfCountryRegionSettings value) {
        return new JAXBElement<ArrayOfCountryRegionSettings>(_GetCountryRegionSettingsResponseGetCountryRegionSettingsResult_QNAME, ArrayOfCountryRegionSettings.class, GetCountryRegionSettingsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "UserId", scope = PagePreview.class)
    public JAXBElement<String> createPagePreviewUserId(String value) {
        return new JAXBElement<String>(_PagePreviewUserId_QNAME, String.class, PagePreview.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "SiteUrl", scope = PagePreview.class)
    public JAXBElement<String> createPagePreviewSiteUrl(String value) {
        return new JAXBElement<String>(_PagePreviewSiteUrl_QNAME, String.class, PagePreview.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "Reason", scope = PagePreview.class)
    public JAXBElement<String> createPagePreviewReason(String value) {
        return new JAXBElement<String>(_PagePreviewReason_QNAME, String.class, PagePreview.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "Url", scope = PagePreview.class)
    public JAXBElement<String> createPagePreviewUrl(String value) {
        return new JAXBElement<String>(_PagePreviewUrl_QNAME, String.class, PagePreview.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = FetchUrl.class)
    public JAXBElement<String> createFetchUrlUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, FetchUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = FetchUrl.class)
    public JAXBElement<String> createFetchUrlSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, FetchUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "page", scope = GetPageQueryStats.class)
    public JAXBElement<String> createGetPageQueryStatsPage(String value) {
        return new JAXBElement<String>(_GetPageQueryStatsPage_QNAME, String.class, GetPageQueryStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetPageQueryStats.class)
    public JAXBElement<String> createGetPageQueryStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetPageQueryStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetQueryParametersResult", scope = GetQueryParametersResponse.class)
    public JAXBElement<ArrayOfQueryParameter> createGetQueryParametersResponseGetQueryParametersResult(ArrayOfQueryParameter value) {
        return new JAXBElement<ArrayOfQueryParameter>(_GetQueryParametersResponseGetQueryParametersResult_QNAME, ArrayOfQueryParameter.class, GetQueryParametersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "language", scope = GetRelatedKeywords.class)
    public JAXBElement<String> createGetRelatedKeywordsLanguage(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsLanguage_QNAME, String.class, GetRelatedKeywords.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "q", scope = GetRelatedKeywords.class)
    public JAXBElement<String> createGetRelatedKeywordsQ(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsQ_QNAME, String.class, GetRelatedKeywords.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "country", scope = GetRelatedKeywords.class)
    public JAXBElement<String> createGetRelatedKeywordsCountry(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsCountry_QNAME, String.class, GetRelatedKeywords.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetDeepLinkAlgoUrls.class)
    public JAXBElement<String> createGetDeepLinkAlgoUrlsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetDeepLinkAlgoUrls.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfConnectedSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetConnectedPagesResult", scope = GetConnectedPagesResponse.class)
    public JAXBElement<ArrayOfConnectedSite> createGetConnectedPagesResponseGetConnectedPagesResult(ArrayOfConnectedSite value) {
        return new JAXBElement<ArrayOfConnectedSite>(_GetConnectedPagesResponseGetConnectedPagesResult_QNAME, ArrayOfConnectedSite.class, GetConnectedPagesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetConnectedPages.class)
    public JAXBElement<String> createGetConnectedPagesSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetConnectedPages.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Query", scope = KeywordStats.class)
    public JAXBElement<String> createKeywordStatsQuery(String value) {
        return new JAXBElement<String>(_KeywordStatsQuery_QNAME, String.class, KeywordStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetBlockedUrls.class)
    public JAXBElement<String> createGetBlockedUrlsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetBlockedUrls.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRankAndTrafficStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetQueryTrafficStatsResult", scope = GetQueryTrafficStatsResponse.class)
    public JAXBElement<ArrayOfRankAndTrafficStats> createGetQueryTrafficStatsResponseGetQueryTrafficStatsResult(ArrayOfRankAndTrafficStats value) {
        return new JAXBElement<ArrayOfRankAndTrafficStats>(_GetQueryTrafficStatsResponseGetQueryTrafficStatsResult_QNAME, ArrayOfRankAndTrafficStats.class, GetQueryTrafficStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Query", scope = Keyword.class)
    public JAXBElement<String> createKeywordQuery(String value) {
        return new JAXBElement<String>(_KeywordStatsQuery_QNAME, String.class, Keyword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetLinkCounts.class)
    public JAXBElement<String> createGetLinkCountsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetLinkCounts.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = LinkDetail.class)
    public JAXBElement<String> createLinkDetailUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, LinkDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "AnchorText", scope = LinkDetail.class)
    public JAXBElement<String> createLinkDetailAnchorText(String value) {
        return new JAXBElement<String>(_LinkDetailAnchorText_QNAME, String.class, LinkDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DisavowLink", scope = DisavowedLink.class)
    public JAXBElement<String> createDisavowedLinkDisavowLink(String value) {
        return new JAXBElement<String>(_DisavowedLinkDisavowLink_QNAME, String.class, DisavowedLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Date", scope = DisavowedLink.class)
    public JAXBElement<String> createDisavowedLinkDate(String value) {
        return new JAXBElement<String>(_DisavowedLinkDate_QNAME, String.class, DisavowedLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetSiteMoves.class)
    public JAXBElement<String> createGetSiteMovesSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetSiteMoves.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCrawlStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetCrawlStatsResult", scope = GetCrawlStatsResponse.class)
    public JAXBElement<ArrayOfCrawlStats> createGetCrawlStatsResponseGetCrawlStatsResult(ArrayOfCrawlStats value) {
        return new JAXBElement<ArrayOfCrawlStats>(_GetCrawlStatsResponseGetCrawlStatsResult_QNAME, ArrayOfCrawlStats.class, GetCrawlStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetUrlTrafficInfo.class)
    public JAXBElement<String> createGetUrlTrafficInfoUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetUrlTrafficInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetUrlTrafficInfo.class)
    public JAXBElement<String> createGetUrlTrafficInfoSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetUrlTrafficInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlTrafficInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetChildrenUrlTrafficInfoResult", scope = GetChildrenUrlTrafficInfoResponse.class)
    public JAXBElement<ArrayOfUrlTrafficInfo> createGetChildrenUrlTrafficInfoResponseGetChildrenUrlTrafficInfoResult(ArrayOfUrlTrafficInfo value) {
        return new JAXBElement<ArrayOfUrlTrafficInfo>(_GetChildrenUrlTrafficInfoResponseGetChildrenUrlTrafficInfoResult_QNAME, ArrayOfUrlTrafficInfo.class, GetChildrenUrlTrafficInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "disavowUrl", scope = AddDisavowedLink.class)
    public JAXBElement<String> createAddDisavowedLinkDisavowUrl(String value) {
        return new JAXBElement<String>(_AddDisavowedLinkDisavowUrl_QNAME, String.class, AddDisavowedLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddDisavowedLink.class)
    public JAXBElement<String> createAddDisavowedLinkSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddDisavowedLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryRegionSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "settings", scope = RemoveCountryRegionSettings.class)
    public JAXBElement<CountryRegionSettings> createRemoveCountryRegionSettingsSettings(CountryRegionSettings value) {
        return new JAXBElement<CountryRegionSettings>(_RemoveCountryRegionSettingsSettings_QNAME, CountryRegionSettings.class, RemoveCountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveCountryRegionSettings.class)
    public JAXBElement<String> createRemoveCountryRegionSettingsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveCountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "feedUrl", scope = RemoveFeed.class)
    public JAXBElement<String> createRemoveFeedFeedUrl(String value) {
        return new JAXBElement<String>(_RemoveFeedFeedUrl_QNAME, String.class, RemoveFeed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveFeed.class)
    public JAXBElement<String> createRemoveFeedSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveFeed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "queryParameter", scope = RemoveQueryParameter.class)
    public JAXBElement<String> createRemoveQueryParameterQueryParameter(String value) {
        return new JAXBElement<String>(_RemoveQueryParameterQueryParameter_QNAME, String.class, RemoveQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveQueryParameter.class)
    public JAXBElement<String> createRemoveQueryParameterSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "blockedUrl", scope = RemoveBlockedUrl.class)
    public JAXBElement<BlockedUrl> createRemoveBlockedUrlBlockedUrl(BlockedUrl value) {
        return new JAXBElement<BlockedUrl>(_RemoveBlockedUrlBlockedUrl_QNAME, BlockedUrl.class, RemoveBlockedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveBlockedUrl.class)
    public JAXBElement<String> createRemoveBlockedUrlSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveBlockedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetUserSitesResult", scope = GetUserSitesResponse.class)
    public JAXBElement<ArrayOfSite> createGetUserSitesResponseGetUserSitesResult(ArrayOfSite value) {
        return new JAXBElement<ArrayOfSite>(_GetUserSitesResponseGetUserSitesResult_QNAME, ArrayOfSite.class, GetUserSitesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetCrawlIssues.class)
    public JAXBElement<String> createGetCrawlIssuesSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetCrawlIssues.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetUrlLinksResult", scope = GetUrlLinksResponse.class)
    public JAXBElement<LinkDetails> createGetUrlLinksResponseGetUrlLinksResult(LinkDetails value) {
        return new JAXBElement<LinkDetails>(_GetUrlLinksResponseGetUrlLinksResult_QNAME, LinkDetails.class, GetUrlLinksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetFetchedUrlDetails.class)
    public JAXBElement<String> createGetFetchedUrlDetailsUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetFetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetFetchedUrlDetails.class)
    public JAXBElement<String> createGetFetchedUrlDetailsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetFetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetRankAndTrafficStats.class)
    public JAXBElement<String> createGetRankAndTrafficStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetRankAndTrafficStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetFeeds.class)
    public JAXBElement<String> createGetFeedsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetFeeds.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = SubmitUrl.class)
    public JAXBElement<String> createSubmitUrlUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, SubmitUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = SubmitUrl.class)
    public JAXBElement<String> createSubmitUrlSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, SubmitUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetChildrenUrlInfoResult", scope = GetChildrenUrlInfoResponse.class)
    public JAXBElement<ArrayOfUrlInfo> createGetChildrenUrlInfoResponseGetChildrenUrlInfoResult(ArrayOfUrlInfo value) {
        return new JAXBElement<ArrayOfUrlInfo>(_GetChildrenUrlInfoResponseGetChildrenUrlInfoResult_QNAME, ArrayOfUrlInfo.class, GetChildrenUrlInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = Feed.class)
    public JAXBElement<String> createFeedUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, Feed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Status", scope = Feed.class)
    public JAXBElement<String> createFeedStatus(String value) {
        return new JAXBElement<String>(_FeedStatus_QNAME, String.class, Feed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Type", scope = Feed.class)
    public JAXBElement<String> createFeedType(String value) {
        return new JAXBElement<String>(_FeedType_QNAME, String.class, Feed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkAlgoUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetDeepLinkAlgoUrlsResult", scope = GetDeepLinkAlgoUrlsResponse.class)
    public JAXBElement<ArrayOfDeepLinkAlgoUrl> createGetDeepLinkAlgoUrlsResponseGetDeepLinkAlgoUrlsResult(ArrayOfDeepLinkAlgoUrl value) {
        return new JAXBElement<ArrayOfDeepLinkAlgoUrl>(_GetDeepLinkAlgoUrlsResponseGetDeepLinkAlgoUrlsResult_QNAME, ArrayOfDeepLinkAlgoUrl.class, GetDeepLinkAlgoUrlsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DelegatorEmail", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesDelegatorEmail(String value) {
        return new JAXBElement<String>(_SiteRolesDelegatorEmail_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DelegatedCode", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesDelegatedCode(String value) {
        return new JAXBElement<String>(_SiteRolesDelegatedCode_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Site", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesSite(String value) {
        return new JAXBElement<String>(_Site_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "VerificationSite", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesVerificationSite(String value) {
        return new JAXBElement<String>(_SiteRolesVerificationSite_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Email", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesEmail(String value) {
        return new JAXBElement<String>(_SiteRolesEmail_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DelegatedCodeOwnerEmail", scope = SiteRoles.class)
    public JAXBElement<String> createSiteRolesDelegatedCodeOwnerEmail(String value) {
        return new JAXBElement<String>(_SiteRolesDelegatedCodeOwnerEmail_QNAME, String.class, SiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BlockedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "blockedUrl", scope = AddBlockedUrl.class)
    public JAXBElement<BlockedUrl> createAddBlockedUrlBlockedUrl(BlockedUrl value) {
        return new JAXBElement<BlockedUrl>(_RemoveBlockedUrlBlockedUrl_QNAME, BlockedUrl.class, AddBlockedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddBlockedUrl.class)
    public JAXBElement<String> createAddBlockedUrlSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddBlockedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "TwoLetterIsoCountryCode", scope = CountryRegionSettings.class)
    public JAXBElement<String> createCountryRegionSettingsTwoLetterIsoCountryCode(String value) {
        return new JAXBElement<String>(_CountryRegionSettingsTwoLetterIsoCountryCode_QNAME, String.class, CountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = CountryRegionSettings.class)
    public JAXBElement<String> createCountryRegionSettingsUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, CountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryRegionSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "settings", scope = AddCountryRegionSettings.class)
    public JAXBElement<CountryRegionSettings> createAddCountryRegionSettingsSettings(CountryRegionSettings value) {
        return new JAXBElement<CountryRegionSettings>(_RemoveCountryRegionSettingsSettings_QNAME, CountryRegionSettings.class, AddCountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddCountryRegionSettings.class)
    public JAXBElement<String> createAddCountryRegionSettingsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddCountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "TargetUrl", scope = SiteMoveSettings.class)
    public JAXBElement<String> createSiteMoveSettingsTargetUrl(String value) {
        return new JAXBElement<String>(_SiteMoveSettingsTargetUrl_QNAME, String.class, SiteMoveSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "SourceUrl", scope = SiteMoveSettings.class)
    public JAXBElement<String> createSiteMoveSettingsSourceUrl(String value) {
        return new JAXBElement<String>(_SiteMoveSettingsSourceUrl_QNAME, String.class, SiteMoveSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = UrlTrafficInfo.class)
    public JAXBElement<String> createUrlTrafficInfoUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, UrlTrafficInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBlockedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetBlockedUrlsResult", scope = GetBlockedUrlsResponse.class)
    public JAXBElement<ArrayOfBlockedUrl> createGetBlockedUrlsResponseGetBlockedUrlsResult(ArrayOfBlockedUrl value) {
        return new JAXBElement<ArrayOfBlockedUrl>(_GetBlockedUrlsResponseGetBlockedUrlsResult_QNAME, ArrayOfBlockedUrl.class, GetBlockedUrlsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetDeepLink.class)
    public JAXBElement<String> createGetDeepLinkUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetDeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetDeepLink.class)
    public JAXBElement<String> createGetDeepLinkSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetDeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDetailedQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetQueryPageDetailStatsResult", scope = GetQueryPageDetailStatsResponse.class)
    public JAXBElement<ArrayOfDetailedQueryStats> createGetQueryPageDetailStatsResponseGetQueryPageDetailStatsResult(ArrayOfDetailedQueryStats value) {
        return new JAXBElement<ArrayOfDetailedQueryStats>(_GetQueryPageDetailStatsResponseGetQueryPageDetailStatsResult_QNAME, ArrayOfDetailedQueryStats.class, GetQueryPageDetailStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = UrlInfo.class)
    public JAXBElement<String> createUrlInfoUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, UrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Keyword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetKeywordResult", scope = GetKeywordResponse.class)
    public JAXBElement<Keyword> createGetKeywordResponseGetKeywordResult(Keyword value) {
        return new JAXBElement<Keyword>(_GetKeywordResponseGetKeywordResult_QNAME, Keyword.class, GetKeywordResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddConnectedPage.class)
    public JAXBElement<String> createAddConnectedPageSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddConnectedPage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "masterUrl", scope = AddConnectedPage.class)
    public JAXBElement<String> createAddConnectedPageMasterUrl(String value) {
        return new JAXBElement<String>(_AddConnectedPageMasterUrl_QNAME, String.class, AddConnectedPage.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetQueryParameters.class)
    public JAXBElement<String> createGetQueryParametersSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetQueryParameters.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetSiteRoles.class)
    public JAXBElement<String> createGetSiteRolesSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetSiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "CrawlRate", scope = CrawlSettings.class)
    public JAXBElement<byte[]> createCrawlSettingsCrawlRate(byte[] value) {
        return new JAXBElement<byte[]>(_CrawlSettingsCrawlRate_QNAME, byte[].class, CrawlSettings.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetCountryRegionSettings.class)
    public JAXBElement<String> createGetCountryRegionSettingsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetCountryRegionSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPagePreview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetActivePagePreviewBlocksResult", scope = GetActivePagePreviewBlocksResponse.class)
    public JAXBElement<ArrayOfPagePreview> createGetActivePagePreviewBlocksResponseGetActivePagePreviewBlocksResult(ArrayOfPagePreview value) {
        return new JAXBElement<ArrayOfPagePreview>(_GetActivePagePreviewBlocksResponseGetActivePagePreviewBlocksResult_QNAME, ArrayOfPagePreview.class, GetActivePagePreviewBlocksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetCrawlStats.class)
    public JAXBElement<String> createGetCrawlStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetCrawlStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Parameter", scope = QueryParameter.class)
    public JAXBElement<String> createQueryParameterParameter(String value) {
        return new JAXBElement<String>(_QueryParameterParameter_QNAME, String.class, QueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetQueryPageStatsResult", scope = GetQueryPageStatsResponse.class)
    public JAXBElement<ArrayOfQueryStats> createGetQueryPageStatsResponseGetQueryPageStatsResult(ArrayOfQueryStats value) {
        return new JAXBElement<ArrayOfQueryStats>(_GetQueryPageStatsResponseGetQueryPageStatsResult_QNAME, ArrayOfQueryStats.class, GetQueryPageStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetPageQueryStatsResult", scope = GetPageQueryStatsResponse.class)
    public JAXBElement<ArrayOfQueryStats> createGetPageQueryStatsResponseGetPageQueryStatsResult(ArrayOfQueryStats value) {
        return new JAXBElement<ArrayOfQueryStats>(_GetPageQueryStatsResponseGetPageQueryStatsResult_QNAME, ArrayOfQueryStats.class, GetPageQueryStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Query", scope = QueryStats.class)
    public JAXBElement<String> createQueryStatsQuery(String value) {
        return new JAXBElement<String>(_KeywordStatsQuery_QNAME, String.class, QueryStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetDisavowedLinks.class)
    public JAXBElement<String> createGetDisavowedLinksSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetDisavowedLinks.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlTrafficInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetUrlTrafficInfoResult", scope = GetUrlTrafficInfoResponse.class)
    public JAXBElement<UrlTrafficInfo> createGetUrlTrafficInfoResponseGetUrlTrafficInfoResult(UrlTrafficInfo value) {
        return new JAXBElement<UrlTrafficInfo>(_GetUrlTrafficInfoResponseGetUrlTrafficInfoResult_QNAME, UrlTrafficInfo.class, GetUrlTrafficInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetQueryPageStats.class)
    public JAXBElement<String> createGetQueryPageStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetQueryPageStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "query", scope = GetQueryPageStats.class)
    public JAXBElement<String> createGetQueryPageStatsQuery(String value) {
        return new JAXBElement<String>(_GetQueryPageStatsQuery_QNAME, String.class, GetQueryPageStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "searchUrl", scope = RemoveDeepLinkBlock.class)
    public JAXBElement<String> createRemoveDeepLinkBlockSearchUrl(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockSearchUrl_QNAME, String.class, RemoveDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "market", scope = RemoveDeepLinkBlock.class)
    public JAXBElement<String> createRemoveDeepLinkBlockMarket(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockMarket_QNAME, String.class, RemoveDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "deepLinkUrl", scope = RemoveDeepLinkBlock.class)
    public JAXBElement<String> createRemoveDeepLinkBlockDeepLinkUrl(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockDeepLinkUrl_QNAME, String.class, RemoveDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveDeepLinkBlock.class)
    public JAXBElement<String> createRemoveDeepLinkBlockSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = VerifySite.class)
    public JAXBElement<String> createVerifySiteSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, VerifySite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetActivePagePreviewBlocks.class)
    public JAXBElement<String> createGetActivePagePreviewBlocksSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetActivePagePreviewBlocks.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetQueryStats.class)
    public JAXBElement<String> createGetQueryStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetQueryStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = RemovePagePreviewBlock.class)
    public JAXBElement<String> createRemovePagePreviewBlockUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, RemovePagePreviewBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemovePagePreviewBlock.class)
    public JAXBElement<String> createRemovePagePreviewBlockSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemovePagePreviewBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = UrlWithCrawlIssues.class)
    public JAXBElement<String> createUrlWithCrawlIssuesUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, UrlWithCrawlIssues.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "queryParameter", scope = AddQueryParameter.class)
    public JAXBElement<String> createAddQueryParameterQueryParameter(String value) {
        return new JAXBElement<String>(_RemoveQueryParameterQueryParameter_QNAME, String.class, AddQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddQueryParameter.class)
    public JAXBElement<String> createAddQueryParameterSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteMoveSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetSiteMovesResult", scope = GetSiteMovesResponse.class)
    public JAXBElement<ArrayOfSiteMoveSettings> createGetSiteMovesResponseGetSiteMovesResult(ArrayOfSiteMoveSettings value) {
        return new JAXBElement<ArrayOfSiteMoveSettings>(_GetSiteMovesResponseGetSiteMovesResult_QNAME, ArrayOfSiteMoveSettings.class, GetSiteMovesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = BlockedUrl.class)
    public JAXBElement<String> createBlockedUrlUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, BlockedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLinkDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Details", scope = LinkDetails.class)
    public JAXBElement<ArrayOfLinkDetail> createLinkDetailsDetails(ArrayOfLinkDetail value) {
        return new JAXBElement<ArrayOfLinkDetail>(_LinkDetailsDetails_QNAME, ArrayOfLinkDetail.class, LinkDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "feedUrl", scope = SubmitFeed.class)
    public JAXBElement<String> createSubmitFeedFeedUrl(String value) {
        return new JAXBElement<String>(_RemoveFeedFeedUrl_QNAME, String.class, SubmitFeed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = SubmitFeed.class)
    public JAXBElement<String> createSubmitFeedSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, SubmitFeed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = LinkCount.class)
    public JAXBElement<String> createLinkCountUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, LinkCount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRankAndTrafficStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetRankAndTrafficStatsResult", scope = GetRankAndTrafficStatsResponse.class)
    public JAXBElement<ArrayOfRankAndTrafficStats> createGetRankAndTrafficStatsResponseGetRankAndTrafficStatsResult(ArrayOfRankAndTrafficStats value) {
        return new JAXBElement<ArrayOfRankAndTrafficStats>(_GetRankAndTrafficStatsResponseGetRankAndTrafficStatsResult_QNAME, ArrayOfRankAndTrafficStats.class, GetRankAndTrafficStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "DeepLinkUrl", scope = DeepLinkBlock.class)
    public JAXBElement<String> createDeepLinkBlockDeepLinkUrl(String value) {
        return new JAXBElement<String>(_DeepLinkBlockDeepLinkUrl_QNAME, String.class, DeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "SiteUrl", scope = DeepLinkBlock.class)
    public JAXBElement<String> createDeepLinkBlockSiteUrl(String value) {
        return new JAXBElement<String>(_PagePreviewSiteUrl_QNAME, String.class, DeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "SearchUrl", scope = DeepLinkBlock.class)
    public JAXBElement<String> createDeepLinkBlockSearchUrl(String value) {
        return new JAXBElement<String>(_DeepLinkBlockSearchUrl_QNAME, String.class, DeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "Market", scope = DeepLinkBlock.class)
    public JAXBElement<String> createDeepLinkBlockMarket(String value) {
        return new JAXBElement<String>(_DeepLinkBlockMarket_QNAME, String.class, DeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetDeepLinkBlocks.class)
    public JAXBElement<String> createGetDeepLinkBlocksSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetDeepLinkBlocks.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveDisavowLink.class)
    public JAXBElement<String> createRemoveDisavowLinkSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveDisavowLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "urlsToProcess", scope = RemoveDisavowLink.class)
    public JAXBElement<String> createRemoveDisavowLinkUrlsToProcess(String value) {
        return new JAXBElement<String>(_RemoveDisavowLinkUrlsToProcess_QNAME, String.class, RemoveDisavowLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "page", scope = GetQueryPageDetailStats.class)
    public JAXBElement<String> createGetQueryPageDetailStatsPage(String value) {
        return new JAXBElement<String>(_GetPageQueryStatsPage_QNAME, String.class, GetQueryPageDetailStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetQueryPageDetailStats.class)
    public JAXBElement<String> createGetQueryPageDetailStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetQueryPageDetailStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "query", scope = GetQueryPageDetailStats.class)
    public JAXBElement<String> createGetQueryPageDetailStatsQuery(String value) {
        return new JAXBElement<String>(_GetQueryPageStatsQuery_QNAME, String.class, GetQueryPageDetailStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = SaveCrawlSettings.class)
    public JAXBElement<String> createSaveCrawlSettingsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, SaveCrawlSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrawlSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "crawlSettings", scope = SaveCrawlSettings.class)
    public JAXBElement<CrawlSettings> createSaveCrawlSettingsCrawlSettings(CrawlSettings value) {
        return new JAXBElement<CrawlSettings>(_SaveCrawlSettingsCrawlSettings_QNAME, CrawlSettings.class, SaveCrawlSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetFetchedUrls.class)
    public JAXBElement<String> createGetFetchedUrlsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetFetchedUrls.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteMoveSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "settings", scope = SubmitSiteMove.class)
    public JAXBElement<SiteMoveSettings> createSubmitSiteMoveSettings(SiteMoveSettings value) {
        return new JAXBElement<SiteMoveSettings>(_RemoveCountryRegionSettingsSettings_QNAME, SiteMoveSettings.class, SubmitSiteMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = SubmitSiteMove.class)
    public JAXBElement<String> createSubmitSiteMoveSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, SubmitSiteMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetPageStatsResult", scope = GetPageStatsResponse.class)
    public JAXBElement<ArrayOfQueryStats> createGetPageStatsResponseGetPageStatsResult(ArrayOfQueryStats value) {
        return new JAXBElement<ArrayOfQueryStats>(_GetPageStatsResponseGetPageStatsResult_QNAME, ArrayOfQueryStats.class, GetPageStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = AddPagePreviewBlock.class)
    public JAXBElement<String> createAddPagePreviewBlockUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, AddPagePreviewBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddPagePreviewBlock.class)
    public JAXBElement<String> createAddPagePreviewBlockSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddPagePreviewBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkCounts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetLinkCountsResult", scope = GetLinkCountsResponse.class)
    public JAXBElement<LinkCounts> createGetLinkCountsResponseGetLinkCountsResult(LinkCounts value) {
        return new JAXBElement<LinkCounts>(_GetLinkCountsResponseGetLinkCountsResult_QNAME, LinkCounts.class, GetLinkCountsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "language", scope = GetKeyword.class)
    public JAXBElement<String> createGetKeywordLanguage(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsLanguage_QNAME, String.class, GetKeyword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "q", scope = GetKeyword.class)
    public JAXBElement<String> createGetKeywordQ(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsQ_QNAME, String.class, GetKeyword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "country", scope = GetKeyword.class)
    public JAXBElement<String> createGetKeywordCountry(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsCountry_QNAME, String.class, GetKeyword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "language", scope = GetKeywordStats.class)
    public JAXBElement<String> createGetKeywordStatsLanguage(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsLanguage_QNAME, String.class, GetKeywordStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "q", scope = GetKeywordStats.class)
    public JAXBElement<String> createGetKeywordStatsQ(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsQ_QNAME, String.class, GetKeywordStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "country", scope = GetKeywordStats.class)
    public JAXBElement<String> createGetKeywordStatsCountry(String value) {
        return new JAXBElement<String>(_GetRelatedKeywordsCountry_QNAME, String.class, GetKeywordStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Document", scope = FetchedUrlDetails.class)
    public JAXBElement<String> createFetchedUrlDetailsDocument(String value) {
        return new JAXBElement<String>(_FetchedUrlDetailsDocument_QNAME, String.class, FetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = FetchedUrlDetails.class)
    public JAXBElement<String> createFetchedUrlDetailsUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, FetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Status", scope = FetchedUrlDetails.class)
    public JAXBElement<String> createFetchedUrlDetailsStatus(String value) {
        return new JAXBElement<String>(_FeedStatus_QNAME, String.class, FetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Headers", scope = FetchedUrlDetails.class)
    public JAXBElement<String> createFetchedUrlDetailsHeaders(String value) {
        return new JAXBElement<String>(_FetchedUrlDetailsHeaders_QNAME, String.class, FetchedUrlDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetCrawlSettings.class)
    public JAXBElement<String> createGetCrawlSettingsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetCrawlSettings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUrlWithCrawlIssues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetCrawlIssuesResult", scope = GetCrawlIssuesResponse.class)
    public JAXBElement<ArrayOfUrlWithCrawlIssues> createGetCrawlIssuesResponseGetCrawlIssuesResult(ArrayOfUrlWithCrawlIssues value) {
        return new JAXBElement<ArrayOfUrlWithCrawlIssues>(_GetCrawlIssuesResponseGetCrawlIssuesResult_QNAME, ArrayOfUrlWithCrawlIssues.class, GetCrawlIssuesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetUrlInfo.class)
    public JAXBElement<String> createGetUrlInfoUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetUrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetUrlInfo.class)
    public JAXBElement<String> createGetUrlInfoSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetUrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetQueryTrafficStats.class)
    public JAXBElement<String> createGetQueryTrafficStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetQueryTrafficStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "query", scope = GetQueryTrafficStats.class)
    public JAXBElement<String> createGetQueryTrafficStatsQuery(String value) {
        return new JAXBElement<String>(_GetQueryPageStatsQuery_QNAME, String.class, GetQueryTrafficStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "algoUrl", scope = UpdateDeepLink.class)
    public JAXBElement<String> createUpdateDeepLinkAlgoUrl(String value) {
        return new JAXBElement<String>(_UpdateDeepLinkAlgoUrl_QNAME, String.class, UpdateDeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = UpdateDeepLink.class)
    public JAXBElement<String> createUpdateDeepLinkSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, UpdateDeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "deepLink", scope = UpdateDeepLink.class)
    public JAXBElement<String> createUpdateDeepLinkDeepLink(String value) {
        return new JAXBElement<String>(_UpdateDeepLinkDeepLink_QNAME, String.class, UpdateDeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeywordStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetKeywordStatsResult", scope = GetKeywordStatsResponse.class)
    public JAXBElement<ArrayOfKeywordStats> createGetKeywordStatsResponseGetKeywordStatsResult(ArrayOfKeywordStats value) {
        return new JAXBElement<ArrayOfKeywordStats>(_GetKeywordStatsResponseGetKeywordStatsResult_QNAME, ArrayOfKeywordStats.class, GetKeywordStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetFeedDetailsResult", scope = GetFeedDetailsResponse.class)
    public JAXBElement<ArrayOfFeed> createGetFeedDetailsResponseGetFeedDetailsResult(ArrayOfFeed value) {
        return new JAXBElement<ArrayOfFeed>(_GetFeedDetailsResponseGetFeedDetailsResult_QNAME, ArrayOfFeed.class, GetFeedDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = DeepLinkAlgoUrl.class)
    public JAXBElement<String> createDeepLinkAlgoUrlUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, DeepLinkAlgoUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetChildrenUrlTrafficInfo.class)
    public JAXBElement<String> createGetChildrenUrlTrafficInfoUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetChildrenUrlTrafficInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetChildrenUrlTrafficInfo.class)
    public JAXBElement<String> createGetChildrenUrlTrafficInfoSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetChildrenUrlTrafficInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfLinkCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Links", scope = LinkCounts.class)
    public JAXBElement<ArrayOfLinkCount> createLinkCountsLinks(ArrayOfLinkCount value) {
        return new JAXBElement<ArrayOfLinkCount>(_LinkCountsLinks_QNAME, ArrayOfLinkCount.class, LinkCounts.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Title", scope = DeepLink.class)
    public JAXBElement<String> createDeepLinkTitle(String value) {
        return new JAXBElement<String>(_DeepLinkTitle_QNAME, String.class, DeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = DeepLink.class)
    public JAXBElement<String> createDeepLinkUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, DeepLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "url", scope = GetChildrenUrlInfo.class)
    public JAXBElement<String> createGetChildrenUrlInfoUrl(String value) {
        return new JAXBElement<String>(_FetchUrlUrl_QNAME, String.class, GetChildrenUrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "filterProperties", scope = GetChildrenUrlInfo.class)
    public JAXBElement<FilterProperties> createGetChildrenUrlInfoFilterProperties(FilterProperties value) {
        return new JAXBElement<FilterProperties>(_GetChildrenUrlInfoFilterProperties_QNAME, FilterProperties.class, GetChildrenUrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetChildrenUrlInfo.class)
    public JAXBElement<String> createGetChildrenUrlInfoSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetChildrenUrlInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "DnsVerificationCode", scope = Site.class)
    public JAXBElement<String> createSiteDnsVerificationCode(String value) {
        return new JAXBElement<String>(_SiteDnsVerificationCode_QNAME, String.class, Site.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = Site.class)
    public JAXBElement<String> createSiteUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, Site.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "AuthenticationCode", scope = Site.class)
    public JAXBElement<String> createSiteAuthenticationCode(String value) {
        return new JAXBElement<String>(_SiteAuthenticationCode_QNAME, String.class, Site.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "userEmail", scope = AddSiteRoles.class)
    public JAXBElement<String> createAddSiteRolesUserEmail(String value) {
        return new JAXBElement<String>(_AddSiteRolesUserEmail_QNAME, String.class, AddSiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "delegatedUrl", scope = AddSiteRoles.class)
    public JAXBElement<String> createAddSiteRolesDelegatedUrl(String value) {
        return new JAXBElement<String>(_AddSiteRolesDelegatedUrl_QNAME, String.class, AddSiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddSiteRoles.class)
    public JAXBElement<String> createAddSiteRolesSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddSiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "authenticationCode", scope = AddSiteRoles.class)
    public JAXBElement<String> createAddSiteRolesAuthenticationCode(String value) {
        return new JAXBElement<String>(_AddSiteRolesAuthenticationCode_QNAME, String.class, AddSiteRoles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "feedUrl", scope = GetFeedDetails.class)
    public JAXBElement<String> createGetFeedDetailsFeedUrl(String value) {
        return new JAXBElement<String>(_RemoveFeedFeedUrl_QNAME, String.class, GetFeedDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetFeedDetails.class)
    public JAXBElement<String> createGetFeedDetailsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetFeedDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetSiteRolesResult", scope = GetSiteRolesResponse.class)
    public JAXBElement<ArrayOfSiteRoles> createGetSiteRolesResponseGetSiteRolesResult(ArrayOfSiteRoles value) {
        return new JAXBElement<ArrayOfSiteRoles>(_GetSiteRolesResponseGetSiteRolesResult_QNAME, ArrayOfSiteRoles.class, GetSiteRolesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFetchedUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetFetchedUrlsResult", scope = GetFetchedUrlsResponse.class)
    public JAXBElement<ArrayOfFetchedUrl> createGetFetchedUrlsResponseGetFetchedUrlsResult(ArrayOfFetchedUrl value) {
        return new JAXBElement<ArrayOfFetchedUrl>(_GetFetchedUrlsResponseGetFetchedUrlsResult_QNAME, ArrayOfFetchedUrl.class, GetFetchedUrlsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveSite.class)
    public JAXBElement<String> createRemoveSiteSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Url", scope = FetchedUrl.class)
    public JAXBElement<String> createFetchedUrlUrl(String value) {
        return new JAXBElement<String>(_LinkDetailUrl_QNAME, String.class, FetchedUrl.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrawlSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetCrawlSettingsResult", scope = GetCrawlSettingsResponse.class)
    public JAXBElement<CrawlSettings> createGetCrawlSettingsResponseGetCrawlSettingsResult(CrawlSettings value) {
        return new JAXBElement<CrawlSettings>(_GetCrawlSettingsResponseGetCrawlSettingsResult_QNAME, CrawlSettings.class, GetCrawlSettingsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "searchUrl", scope = AddDeepLinkBlock.class)
    public JAXBElement<String> createAddDeepLinkBlockSearchUrl(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockSearchUrl_QNAME, String.class, AddDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "market", scope = AddDeepLinkBlock.class)
    public JAXBElement<String> createAddDeepLinkBlockMarket(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockMarket_QNAME, String.class, AddDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "deepLinkUrl", scope = AddDeepLinkBlock.class)
    public JAXBElement<String> createAddDeepLinkBlockDeepLinkUrl(String value) {
        return new JAXBElement<String>(_RemoveDeepLinkBlockDeepLinkUrl_QNAME, String.class, AddDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddDeepLinkBlock.class)
    public JAXBElement<String> createAddDeepLinkBlockSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddDeepLinkBlock.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetDeepLinkResult", scope = GetDeepLinkResponse.class)
    public JAXBElement<ArrayOfDeepLink> createGetDeepLinkResponseGetDeepLinkResult(ArrayOfDeepLink value) {
        return new JAXBElement<ArrayOfDeepLink>(_GetDeepLinkResponseGetDeepLinkResult_QNAME, ArrayOfDeepLink.class, GetDeepLinkResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "Message", scope = ApiFault.class)
    public JAXBElement<String> createApiFaultMessage(String value) {
        return new JAXBElement<String>(_ApiFaultMessage_QNAME, String.class, ApiFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetUrlSubmissionQuota.class)
    public JAXBElement<String> createGetUrlSubmissionQuotaSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetUrlSubmissionQuota.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetFeedsResult", scope = GetFeedsResponse.class)
    public JAXBElement<ArrayOfFeed> createGetFeedsResponseGetFeedsResult(ArrayOfFeed value) {
        return new JAXBElement<ArrayOfFeed>(_GetFeedsResponseGetFeedsResult_QNAME, ArrayOfFeed.class, GetFeedsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDisavowedLink }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetDisavowedLinksResult", scope = GetDisavowedLinksResponse.class)
    public JAXBElement<ArrayOfDisavowedLink> createGetDisavowedLinksResponseGetDisavowedLinksResult(ArrayOfDisavowedLink value) {
        return new JAXBElement<ArrayOfDisavowedLink>(_GetDisavowedLinksResponseGetDisavowedLinksResult_QNAME, ArrayOfDisavowedLink.class, GetDisavowedLinksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = AddSite.class)
    public JAXBElement<String> createAddSiteSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, AddSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchedUrlDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetFetchedUrlDetailsResult", scope = GetFetchedUrlDetailsResponse.class)
    public JAXBElement<FetchedUrlDetails> createGetFetchedUrlDetailsResponseGetFetchedUrlDetailsResult(FetchedUrlDetails value) {
        return new JAXBElement<FetchedUrlDetails>(_GetFetchedUrlDetailsResponseGetFetchedUrlDetailsResult_QNAME, FetchedUrlDetails.class, GetFetchedUrlDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "IsDeepLaunchSupported", scope = ConnectedSite.class)
    public JAXBElement<Boolean> createConnectedSiteIsDeepLaunchSupported(Boolean value) {
        return new JAXBElement<Boolean>(_ConnectedSiteIsDeepLaunchSupported_QNAME, Boolean.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "ActualMasterSite", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteActualMasterSite(String value) {
        return new JAXBElement<String>(_ConnectedSiteActualMasterSite_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "AppId", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteAppId(String value) {
        return new JAXBElement<String>(_ConnectedSiteAppId_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "IsBlocked", scope = ConnectedSite.class)
    public JAXBElement<Boolean> createConnectedSiteIsBlocked(Boolean value) {
        return new JAXBElement<Boolean>(_ConnectedSiteIsBlocked_QNAME, Boolean.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "Url", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteUrl(String value) {
        return new JAXBElement<String>(_PagePreviewUrl_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "AppName", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteAppName(String value) {
        return new JAXBElement<String>(_ConnectedSiteAppName_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "IsDeepLaunchSupportedLastModified", scope = ConnectedSite.class)
    public JAXBElement<XMLGregorianCalendar> createConnectedSiteIsDeepLaunchSupportedLastModified(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ConnectedSiteIsDeepLaunchSupportedLastModified_QNAME, XMLGregorianCalendar.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "IsBlockedLastModified", scope = ConnectedSite.class)
    public JAXBElement<XMLGregorianCalendar> createConnectedSiteIsBlockedLastModified(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ConnectedSiteIsBlockedLastModified_QNAME, XMLGregorianCalendar.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "Market", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteMarket(String value) {
        return new JAXBElement<String>(_DeepLinkBlockMarket_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Shared.DataContracts", name = "RequestedMasterSite", scope = ConnectedSite.class)
    public JAXBElement<String> createConnectedSiteRequestedMasterSite(String value) {
        return new JAXBElement<String>(_ConnectedSiteRequestedMasterSite_QNAME, String.class, ConnectedSite.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDeepLinkBlock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetDeepLinkBlocksResult", scope = GetDeepLinkBlocksResponse.class)
    public JAXBElement<ArrayOfDeepLinkBlock> createGetDeepLinkBlocksResponseGetDeepLinkBlocksResult(ArrayOfDeepLinkBlock value) {
        return new JAXBElement<ArrayOfDeepLinkBlock>(_GetDeepLinkBlocksResponseGetDeepLinkBlocksResult_QNAME, ArrayOfDeepLinkBlock.class, GetDeepLinkBlocksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "queryParameter", scope = EnableDisableQueryParameter.class)
    public JAXBElement<String> createEnableDisableQueryParameterQueryParameter(String value) {
        return new JAXBElement<String>(_RemoveQueryParameterQueryParameter_QNAME, String.class, EnableDisableQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = EnableDisableQueryParameter.class)
    public JAXBElement<String> createEnableDisableQueryParameterSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, EnableDisableQueryParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UrlSubmissionQuota }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetUrlSubmissionQuotaResult", scope = GetUrlSubmissionQuotaResponse.class)
    public JAXBElement<UrlSubmissionQuota> createGetUrlSubmissionQuotaResponseGetUrlSubmissionQuotaResult(UrlSubmissionQuota value) {
        return new JAXBElement<UrlSubmissionQuota>(_GetUrlSubmissionQuotaResponseGetUrlSubmissionQuotaResult_QNAME, UrlSubmissionQuota.class, GetUrlSubmissionQuotaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetPageStats.class)
    public JAXBElement<String> createGetPageStatsSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetPageStats.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetRelatedKeywordsResult", scope = GetRelatedKeywordsResponse.class)
    public JAXBElement<ArrayOfKeyword> createGetRelatedKeywordsResponseGetRelatedKeywordsResult(ArrayOfKeyword value) {
        return new JAXBElement<ArrayOfKeyword>(_GetRelatedKeywordsResponseGetRelatedKeywordsResult_QNAME, ArrayOfKeyword.class, GetRelatedKeywordsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteRole", scope = RemoveSiteRole.class)
    public JAXBElement<SiteRoles> createRemoveSiteRoleSiteRole(SiteRoles value) {
        return new JAXBElement<SiteRoles>(_RemoveSiteRoleSiteRole_QNAME, SiteRoles.class, RemoveSiteRole.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = RemoveSiteRole.class)
    public JAXBElement<String> createRemoveSiteRoleSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, RemoveSiteRole.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueryStats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "GetQueryStatsResult", scope = GetQueryStatsResponse.class)
    public JAXBElement<ArrayOfQueryStats> createGetQueryStatsResponseGetQueryStatsResult(ArrayOfQueryStats value) {
        return new JAXBElement<ArrayOfQueryStats>(_GetQueryStatsResponseGetQueryStatsResult_QNAME, ArrayOfQueryStats.class, GetQueryStatsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "link", scope = GetUrlLinks.class)
    public JAXBElement<String> createGetUrlLinksLink(String value) {
        return new JAXBElement<String>(_GetUrlLinksLink_QNAME, String.class, GetUrlLinks.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Microsoft.Bing.Webmaster.Api", name = "siteUrl", scope = GetUrlLinks.class)
    public JAXBElement<String> createGetUrlLinksSiteUrl(String value) {
        return new JAXBElement<String>(_FetchUrlSiteUrl_QNAME, String.class, GetUrlLinks.class, value);
    }

}
