package com.example.alec.phase_05.Server.communication;

import com.example.alec.phase_05.Server.DAO.DAO_User;
import com.example.alec.phase_05.Server.Database.DAO_File;
import com.example.alec.phase_05.Server.Database.DAO_Sqlite;
import com.example.alec.phase_05.Server.command_line.ServerCommandLine;
import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Server.model.ServerModel;
import com.example.alec.phase_05.Shared.model.User;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Created by samuel on 2/9/17.
 */

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String port) {
        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(port)),
                    MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/command", new CommandHandler());

        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: run_server <port>");
            System.exit(1);
        }
        String port = args[0];
        //System.out.println("SERVER MAIN " + args[0]);

        if(args[1].equals("sqlite")){
            ServerModel.getInstance().setDatabase(DAO_Sqlite.getInstance());
            DAO_Sqlite.getInstance().instantiateTables();
            DAO_Sqlite.getInstance().loadUsers();
        }
        else{
            ServerModel.getInstance().setDatabase(DAO_File.getInstance());
            DAO_File.getInstance().loadUsers();
        }

        new Server().run(port);
        new ServerCommandLine().start();
    }
}
