import com.sun.net.httpserver.*;

import java.io.*;
import java.util.*;

public class SumHandler implements HttpHandler {
    Map<Long, Transaction> transactions;

    public SumHandler(Map<Long, Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        long transactionId = Integer.parseInt(path.split("/")[2]);

        Transaction t = transactions.get(transactionId);

        double sum = t.getAmount();
        String response = "{\"sum\": " + sum + "}";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
