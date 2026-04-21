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
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class HTTPResponder {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/echo", new EchoHandler());
        server.createContext("/inquiry", new InquiryHandler());

        server.setExecutor(null);
        System.out.println("Server started on port 8080...");
        server.start();
    }
}