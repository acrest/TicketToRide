package com.example.alec.phase_05.Server.Database.database_interface;

import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

/**
 * Created by samuel on 4/10/17.
 */

public interface GameDAO {
    // Saves game.
    void saveGame(IServerGame game);

    // Gets the blob with gameId.
    IServerGame getGame(int gameId);

    // Checks to see if a game with gameId exists in the database.
    boolean hasGame(int gameId);

    // Removes blob with gameId.
    void clearGame(int gameId);

    // Clears all game blobs.
    void clearAllGames();

    void addCommand(int gameId, ICommand command);

    ICommand getCommand(int gameId, int index);

    int getNumberOfCommands(int gameId);

    void clearCommands(int gameId);

    void clearAll();

    void setUp();
}
