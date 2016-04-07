package proj;

import com.sun.net.httpserver.*;

import java.io.*;

public class SumHandler implements HttpHandler {
    private SumApi api;

    public SumHandler(SumApi api) {
        this.api = api;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        long transactionId = Integer.parseInt(path.split("/")[3]);

        double sum = api.getSum(transactionId);

        String response = "{\"sum\": " + sum + "}";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }



}
