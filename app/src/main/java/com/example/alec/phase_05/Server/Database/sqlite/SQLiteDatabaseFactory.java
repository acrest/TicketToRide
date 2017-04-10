package com.example.alec.phase_05.Server.Database.sqlite;

import com.example.alec.phase_05.Server.Database.database_interface.DatabaseFactory;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;

/**
 * Created by samuel on 4/10/17.
 */

public class SQLiteDatabaseFactory implements DatabaseFactory {
    @Override
    public GameDAO createGameDAO() {
        return new SQLiteGameDAO();
    }

    @Override
    public PlayerDAO createPlayerDAO() {
        return new SQLitePlayerDAO();
    }
}
