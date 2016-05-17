package fogetti.phish.storm.client;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Request;
import okhttp3.Response;

public final class WrappedRequest implements IRequest, Serializable {

    private static final long serialVersionUID = -4330455403672234886L;

    @Override
    public Request Get(String query) throws IOException {
        return new Request.Builder().url(query).build();
    }

    @Override
    public Response execute() throws IOException {
        throw new UnsupportedOperationException();
    }
    
}