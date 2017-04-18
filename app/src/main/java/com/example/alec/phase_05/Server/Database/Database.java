package com.example.alec.phase_05.Server.Database;

import com.example.alec.phase_05.Server.Database.database_interface.DatabaseFactory;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;
import com.example.alec.phase_05.Server.communication.Server;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Server.model.ServerModel;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.model.PlayerCredentials;
import com.example.alec.phase_05.Shared.model.User;
import com.example.alec.phase_05.Server.Database.sqlite.SQLitePlayerDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samuel on 4/10/17.
 */

public class Database {
    private static PlayerDAO playerDAO;
    private static GameDAO gameDAO;

    public static PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public static GameDAO getGameDAO() {
        return gameDAO;
    }

    public static boolean init(String persistence, boolean clearDatabase) {
        Map<String, String> registry = loadRegistry();
        if (registry == null) {
            return false;
        }
        if (registry.containsKey(persistence)) {
            DatabaseFactory factory = loadFactory(registry.get(persistence));
            if (factory != null) {
                init(factory, clearDatabase);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static void init(DatabaseFactory factory, boolean clearDatabase) {
        playerDAO = factory.createPlayerDAO();
        gameDAO = factory.createGameDAO();
        if (clearDatabase) {
            playerDAO.clear();
            gameDAO.clearAll();
            playerDAO.setUp();
            gameDAO.setUp();
        } else {
            // Load memory from database.
            playerDAO.setUp();
            gameDAO.setUp();
            loadAllDataToMemory();
        }
    }

    private static void loadAllDataToMemory() {
        ServerModel model = ServerModel.getInstance();

        // Player data
        ArrayList<User> users = Database.getPlayerDAO().getUsers();
        for (User user : users) {
            model.addPlayer(new PlayerCredentials(user.getUsername(), user.getPassword()));
        }

        // Game data
        int gameId = 0;
        ServerGame game;
        do {
            game = (ServerGame) Database.getGameDAO().getGame(gameId);
            if (game != null) {
                // Add game to model.
                model.addGame(game);

                // Delta
                for (int i = 0, numCommands = Database.getGameDAO().getNumberOfCommands(gameId); i < numCommands; i++) {
                    GameCommand command = (GameCommand) Database.getGameDAO().getCommand(gameId, i);
                    command.reExecute(); // Should go to the ServerFacade and change the game just added.
                }
            }
            gameId++;
        } while (game != null);
    }

    private static Map<String, String> loadRegistry() {
        try {
            Map<String, String> registry = new HashMap<>();
            BufferedReader input = new BufferedReader(new FileReader("plugin_registry.txt"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] split = line.split("\\s+");
                registry.put(split[0], split[1]);
            }
            return registry;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DatabaseFactory loadFactory(String className) {
        try {
            Class<?> c = Class.forName(className);
            return (DatabaseFactory) c.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
