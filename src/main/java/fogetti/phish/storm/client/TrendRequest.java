package fogetti.phish.storm.client;

import java.io.IOException;
import java.io.Serializable;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public final class TrendRequest implements IRequest, Serializable {

    private static final long serialVersionUID = -4330455403672234886L;

    @Override
    public Request Get(String query) throws IOException {
        return Request.Get(query);
    }

    @Override
    public Response execute() throws ClientProtocolException, IOException {
        throw new UnsupportedOperationException();
    }
    
}