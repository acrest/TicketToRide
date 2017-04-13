package com.example.alec.phase_05.Server.Database;

import com.example.alec.phase_05.Server.Database.database_interface.DatabaseFactory;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static boolean init(String persistence) {
        Map<String, String> registry = loadRegistry();
        if (registry == null) {
            return false;
        }
        if (registry.containsKey(persistence)) {
            DatabaseFactory factory = loadFactory(registry.get(persistence));
            if (factory != null) {
                init(factory);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static void init(DatabaseFactory factory) {
        playerDAO = factory.createPlayerDAO();
        gameDAO = factory.createGameDAO();
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
