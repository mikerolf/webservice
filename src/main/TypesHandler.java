import com.fasterxml.jackson.databind.*;
import com.sun.net.httpserver.*;

import java.io.*;
import java.util.*;

public class TypesHandler implements HttpHandler {
    TypesApi api;

    public TypesHandler(TypesApi api) {
        this.api = api;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        String type = path.split("/")[2];

        Set<Integer> ids = api.get(type);

        ObjectMapper mapper = new ObjectMapper();

        String response = mapper.writeValueAsString(ids);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
