package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.database_interface.DatabaseFactory;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;

/**
 * Created by samuel on 4/10/17.
 */

public class FileDatabaseFactory implements DatabaseFactory {
    @Override
    public GameDAO createGameDAO() {
        return new FileGameDAO();
    }

    @Override
    public PlayerDAO createPlayerDAO() {
        return new FilePlayerDAO();
    }
}
