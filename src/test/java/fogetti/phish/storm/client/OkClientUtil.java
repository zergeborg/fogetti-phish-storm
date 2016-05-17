package fogetti.phish.storm.client;

import static org.mockito.Matchers.anyObject;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkClientUtil {

    public static OkHttpClient getMockedClient(String file) throws Exception {
        URL resource = OkClientUtil.class.getClassLoader().getResource(file);
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

}