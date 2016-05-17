package fogetti.phish.storm.client;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public interface IRequest {

    Request Get(String query) throws IOException;
    Response execute() throws IOException;
}