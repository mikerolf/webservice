package proj;

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

        SumApi sumApi = new SumApi(transactions);
        SumHandler sumHandler = new SumHandler(sumApi);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/transactionservice/test", testHandler);
        server.createContext("/transactionservice/transaction", transactionHandler);
        server.createContext("/transactionservice/types", typesHandler);
        server.createContext("/transactionservice/sum", sumHandler);
        server.setExecutor(null);
        server.start();
    }
}