package fogetti.phish.storm.client;

import java.io.IOException;

import org.apache.http.client.fluent.Request;

public interface IRequest {

    Request Get(String query) throws IOException;
}