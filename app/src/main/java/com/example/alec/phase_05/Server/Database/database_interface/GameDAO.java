package com.example.alec.phase_05.Server.Database.database_interface;

import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

/**
 * Created by samuel on 4/10/17.
 */

public interface GameDAO {
    void saveGame(IServerGame game);

    IServerGame getGame();

    boolean hasGame();

    void deleteGame();

    void addCommand(ICommand command);

    ICommand getCommand(int index);

    int getNumberOfCommands();

    void clearCommands();
}
