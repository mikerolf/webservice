import com.fasterxml.jackson.databind.*;
import com.sun.net.httpserver.*;

import java.io.*;
import java.util.*;

class TransactionHandler implements HttpHandler {
    Map<Integer, Transaction> transactions;

    public TransactionHandler(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String path = httpExchange.getRequestURI().getPath();
        int transactionId = Integer.parseInt(path.split("/")[2]);

        String method = httpExchange.getRequestMethod();

        String response = null;

        if (method.equals("POST")) {
            String body = convertStreamToString(httpExchange.getRequestBody());
            Transaction transaction = mapper.readValue(body, Transaction.class);

            transactions.put(transactionId, transaction);
            response = "{ \"status\": \"ok\" }";
        }
        else {
            Transaction t = transactions.get(transactionId);
            response = mapper.writeValueAsString(t);
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}