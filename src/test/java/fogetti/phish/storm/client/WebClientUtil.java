package fogetti.phish.storm.client;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WebClientUtil {

    public static WebClient getMockedWebClient(String file) throws Exception {
        URL resource = WebClientUtil.class.getClassLoader().getResource(file);
        WebClient client = mock(WebClient.class);
        WebClientOptions options = mock(WebClientOptions.class);
        when(client.getOptions()).thenReturn(options);
        HtmlPage htmlPage = mock(HtmlPage.class);
        when(htmlPage.isHtmlPage()).thenReturn(true);
        when(client.getPage(anyString())).thenReturn(htmlPage);
        String contentStr = new String(Files.readAllBytes(Paths.get(new File(resource.toURI()).getAbsolutePath())));
        when(htmlPage.asXml()).thenReturn(contentStr);
        return client;
    }
    
    public static OkHttpClient getMockedClient(String file) throws Exception {
        URL resource = WebClientUtil.class.getClassLoader().getResource(file);
        OkHttpClient client = mock(OkHttpClient.class);
        Call call = mock(Call.class);
        when(client.newCall((Request)anyObject())).thenReturn(call);
        Response response = mock(Response.class);
        when(call.execute()).thenReturn(response);
        ResponseBody body = mock(ResponseBody.class);
        when(response.body()).thenReturn(body);
        String contentStr = new String(Files.readAllBytes(Paths.get(new File(resource.toURI()).getAbsolutePath())));
        when(body.string()).thenReturn(contentStr);
        return client;
    }

    @SuppressWarnings("unchecked")
    public static WebClient getMockedWebClient(String file, Class<? extends Exception> clazz) throws Exception {
        WebClient client = getMockedWebClient(file);
        when(client.getPage(anyString())).thenThrow(clazz);
        return client;
    }

}