package com.example.alec.phase_05.Server.Database.database_interface;

/**
 * Created by samuel on 4/10/17.
 */

public interface DatabaseFactory {
    GameDAO createGameDAO();

    PlayerDAO createPlayerDAO();

    void clear();
}
