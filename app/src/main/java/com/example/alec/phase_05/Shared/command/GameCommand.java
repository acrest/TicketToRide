package com.example.alec.phase_05.Shared.command;

import java.io.Serializable;

/**
 * Holds a game id so the server knows which game the client is in.
 * Created by samuel on 2/3/17.
 */
public abstract class GameCommand extends BaseCommand implements Serializable {
    private int gameId;
    private String playerName;

    /**
     * @param commandName command name from BaseCommand
     * @param gameID id of the game for which this command operates
     */
    public GameCommand(String commandName, String playerName, int gameID) {
        super(commandName);
        this.playerName = playerName;
        this.gameId = gameID;
    }

    /**
     * @return id of the game for which this command operates
     */
    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public abstract void reExecute();
}
