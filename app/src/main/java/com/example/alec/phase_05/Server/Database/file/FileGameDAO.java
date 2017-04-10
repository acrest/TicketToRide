package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

/**
 * Created by samuel on 4/10/17.
 */

public class FileGameDAO implements GameDAO {
    public FileGameDAO() {

    }

    @Override
    public void saveGame(IServerGame game) {

    }

    @Override
    public IServerGame getGame(int gameId) {
        return null;
    }

    @Override
    public boolean hasGame(int gameId) {
        return false;
    }

    @Override
    public void clearGame(int gameId) {

    }

    @Override
    public void clearAllGames() {

    }

    @Override
    public void addCommand(int gameId, ICommand command) {

    }

    @Override
    public ICommand getCommand(int gameId, int index) {
        return null;
    }

    @Override
    public int getNumberOfCommands(int gameId) {
        return 0;
    }

    @Override
    public void clearCommands(int gameId) {

    }

    @Override
    public void clearAll() {

    }
}
