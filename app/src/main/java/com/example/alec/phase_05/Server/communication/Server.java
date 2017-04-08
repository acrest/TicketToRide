package com.example.alec.phase_05.Server.communication;

import com.example.alec.phase_05.DAO.DAO_User;
import com.example.alec.phase_05.Server.command_line.ServerCommandLine;
import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.model.User;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

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

    private static void instantiateTables()
    {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            System.out.println("Opened database successfully");
            DAO_User.getInstance().createTableUser(c);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    private static void loadUsers(){
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            ArrayList<User> users = DAO_User.getInstance().getUsers(c);
            ServerFacade.getInstance().loadUsers(users);
            System.out.println("Loaded users.");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: run_server <port>");
            System.exit(1);
        }
        String port = args[0];
        //System.out.println("SERVER MAIN " + args[0]);

        instantiateTables();
        DAO_User.getInstance().addUser("Andrew", "programming_wizard");
        loadUsers();

        new Server().run(port);
        new ServerCommandLine().start();
    }
}
