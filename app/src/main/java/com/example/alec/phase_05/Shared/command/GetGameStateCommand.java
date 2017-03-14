package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/1/17.
 */

public abstract class GetGameStateCommand extends BaseCommand {
    private int gameId;

    public GetGameStateCommand(int gameId) {
        super("GetGameState");
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
