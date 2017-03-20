package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/7/17.
 */

public abstract class StartGameCommand extends BaseCommand {
    private int gameId;

    public StartGameCommand(int gameId) {
        super("StartGame");
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
