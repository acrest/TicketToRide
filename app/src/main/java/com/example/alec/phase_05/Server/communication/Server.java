package com.example.alec.phase_05.Server.communication;

import com.example.alec.phase_05.Server.Database.Database;
import com.example.alec.phase_05.Server.Database.file.FilePlayerDAO;
import com.example.alec.phase_05.Server.Database.sqlite.SQLitePlayerDAO;
import com.example.alec.phase_05.Server.command_line.ServerCommandLine;
import com.example.alec.phase_05.Server.model.ServerModel;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

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
        if (args.length < 2) {
            System.out.println("Usage: run_server <port> <persistence>");
            System.exit(1);
        }
        String port = args[0];
        //System.out.println("SERVER MAIN " + args[0]);

//        if(args[1].equals("sqlite")){
//            ServerModel.getInstance().setDatabase(SQLitePlayerDAO.getInstance());
//            SQLitePlayerDAO.getInstance().instantiateTables();
//            SQLitePlayerDAO.getInstance().loadUsers();
//        }
//        else{
//            ServerModel.getInstance().setDatabase(FilePlayerDAO.getInstance());
//            FilePlayerDAO.getInstance().loadUsers();
//        }
        if (!Database.init(args[1])) {
            System.out.println("Invalid persistence");
            System.exit(1);
        }

        new Server().run(port);
        new ServerCommandLine().start();
    }
}
