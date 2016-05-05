package fogetti.phish.storm.client;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class ResponseRequest implements IRequest {
    
    private final String contentStr;
    private final Request request;
    private final Response response;
    private final Content content;
    
    public ResponseRequest(String file) throws IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource(file);
        this.contentStr = new String(Files.readAllBytes(Paths.get(new File(resource.toURI()).getAbsolutePath())));
        this.request = mock(Request.class);
        this.response = mock(Response.class);
        this.content = mock(Content.class);
    }
    
    @Override
    public Request Get(String query) throws IOException {
        when(request.viaProxy((HttpHost)anyObject())).thenReturn(request);
        when(request.connectTimeout(anyInt())).thenReturn(request);
        when(request.socketTimeout(anyInt())).thenReturn(request);
        when(request.execute()).thenReturn(response);
        when(response.returnContent()).thenReturn(content);
        when(content.asString()).thenReturn(contentStr);
        return request;
    }

    @Override
    public Response execute() throws ClientProtocolException, IOException { return null; }        
}