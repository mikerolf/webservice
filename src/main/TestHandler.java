import com.sun.net.httpserver.*;

import java.io.*;

class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "Up and running!";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
