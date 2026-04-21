/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpresponder;
/**
 *
 * @author alvin.wijaya
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EchoHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                .lines()
                .reduce("", (a, b) -> a + b);

        String transDateTime = extractValue(request, "transDateTime");
        String rc = ResponseUtil.generateResponseCode();

        String response = String.format(
                "{\"transDateTime\":\"%s\",\"responseCode\":\"%s\"}",
                transDateTime, rc
        );

        sendResponse(exchange, response);
    }

    private String extractValue(String json, String key) {
        return json.replaceAll(".*\"" + key + "\":\"([^\"]+)\".*", "$1");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}