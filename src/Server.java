import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    static void start() throws IOException {
        TestHandler testHandler = new TestHandler();

        Map<Integer, Transaction> transactions = new ConcurrentHashMap<Integer, Transaction>();
        TransactionHandler transactionHandler = new TransactionHandler(transactions);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", testHandler);
        server.createContext("/transaction", transactionHandler);
        server.setExecutor(null);
        server.start();
    }
}