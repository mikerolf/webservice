import com.fasterxml.jackson.databind.*;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;

public class Program {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new TestHandler());
        server.createContext("/transaction", new TransactionHandler());
        server.setExecutor(null);
        server.start();
    }

    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Up and running!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class TransactionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String body = convertStreamToString(httpExchange.getRequestBody());

            ObjectMapper mapper = new ObjectMapper();
            Transaction t = mapper.readValue(body, Transaction.class);

            //Do something with transaction

            String response = "{ \"status\": \"ok\" }";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        static String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }

    static class Transaction {
        int amount;
        String type;

        public void setAmount(int amount) {
            this.amount = amount;
        }
        public void setType(String type) {
            this.type = type;
        }
    }

}
