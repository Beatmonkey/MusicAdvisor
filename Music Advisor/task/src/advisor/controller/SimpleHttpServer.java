package advisor.controller;


import advisor.common.AuthCallback;
import advisor.model.ApplicationSettings;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    HttpServer server;
    ApplicationSettings applicationSettings;

    SimpleHttpServer(ApplicationSettings applicationSettings) {
        this.applicationSettings = applicationSettings;
    }

    void requestCode(AuthCallback onCodeReceived) throws IOException, InterruptedException {

        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        server.start();

        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        String query = exchange.getRequestURI().getQuery();
                        String result = "";

                        int status;
                        if (query == null) {
                            status = 0; //Empty response
                            result = "Not found authorization code. Try again.";
                        } else if (query.contains("code")) {
                            status = 1; //OK
                            result = "Got the code. Return back to your program.";
                            applicationSettings.code = query.replaceAll("code=", "");
                        } else {
                            status = 2;// Code isn't received
                            result = "Not found authorization code. Try again.";
                        }
                        exchange.sendResponseHeaders(200, result.length());
                        //output result string to the browser
                        exchange.getResponseBody().write(result.getBytes());
                        exchange.getResponseBody().close();
                        onCodeReceived.call(status);


                    }
                }

        );
        while (applicationSettings.code.equals("")) {
            Thread.sleep(10);
        }
        server.stop(10);


    }
}
