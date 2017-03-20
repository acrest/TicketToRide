package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/13/17.
 */

public abstract class GetNextChangeCommand extends BaseCommand {
    private String playerName;
    private int gameId;

    public GetNextChangeCommand(String playerName, int gameId) {
        super("GetNextChange");
        this.playerName = playerName;
        this.gameId = gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGameId() {
        return gameId;
    }
}
