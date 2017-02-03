package com.example.alec.phase_05.Server;

import android.util.Log;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import com.example.alec.phase_05.Server.TrimHandler;

/**
 * Created by Alec on 1/21/17.
 */
public class Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String portNumber) {
        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor

        System.out.println("Creating contexts");
        server.createContext("/trim", new TrimHandler());
        server.createContext("/parse", new ParseIntergerHandler());
        server.createContext("/lower", new ToLowerCaseHandler());
        server.createContext("/exec", new ExecCommandHandler());

        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args) {
        String portNumber = "8080";
        new Server().run(portNumber);
    }

}
