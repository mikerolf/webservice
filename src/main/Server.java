import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    static void start() throws IOException {
        TestHandler testHandler = new TestHandler();

        Map<Long, Transaction> transactions = new ConcurrentHashMap<Long, Transaction>();
        TransactionHandler transactionHandler = new TransactionHandler(transactions);

        TypesApi typesApi = new TypesApi(transactions);
        TypesHandler typesHandler = new TypesHandler(typesApi);

        SumHandler sumHandler = new SumHandler(transactions);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", testHandler);
        server.createContext("/transaction", transactionHandler);
        server.createContext("/types", typesHandler);
        server.createContext("/sum", sumHandler);
        server.setExecutor(null);
        server.start();
    }
}