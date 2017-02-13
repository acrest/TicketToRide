package com.example.alec.phase_05.Server;

import java.io.*;
import java.net.*;

import com.example.alec.phase_05.Server.command.CommandHandler;
import com.sun.net.httpserver.*;

/**
 * Created by samuel on 2/9/17.
 */

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String port)
    {
        System.out.println("Initializing HTTP Server");
        try
        {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(port)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/command", new CommandHandler());

        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Usage: run_server <port>");
            System.exit(1);
        }
        String port = args[0];
        //System.out.println("SERVER MAIN " + args[0]);
        new Server().run(port);
    }
}
