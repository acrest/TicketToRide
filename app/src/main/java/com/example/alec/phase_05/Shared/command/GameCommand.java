package com.example.alec.phase_05.Shared.command;

/**
 * Holds a game id so the server knows which game the client is in.
 * Created by samuel on 2/3/17.
 */
public abstract class GameCommand extends AuthorizedCommand
{
    private int gameID;
    private int playerId;

    /**
     * @param commandName command name from BaseCommand
     * @param userName username of client
     * @param password password of client
     * @param gameID id of the game for which this command operates
     */
    public GameCommand(String commandName, String userName, String password, int gameID)
    {
        super(commandName, userName, password);
        this.gameID = gameID;
    }

    /**
     * @return id of the game for which this command operates
     */
    public int getGameID()
    {
        return gameID;
    }

    /**
     *
     * @param g id of the game for which this command operates
     */
    public void setGameID(int g)
    {
        gameID = g;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
