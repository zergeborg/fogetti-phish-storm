package fogetti.phish.storm.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public interface IRequest {

    Request Get(String query) throws IOException;
    Response execute() throws ClientProtocolException, IOException;
}