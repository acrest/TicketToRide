package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 4/13/17.
 */

public abstract class RejoinGameCommand extends BaseCommand {
    private int gameId;
    private String playerName;

    public RejoinGameCommand(String playerName, int gameId) {
        super("RejoinGame");
        this.playerName = playerName;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
