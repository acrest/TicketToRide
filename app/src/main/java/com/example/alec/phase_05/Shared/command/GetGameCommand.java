package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 2/14/17.
 */

public abstract class GetGameCommand extends BaseCommand {
    private int gameId;

    public GetGameCommand(int gameId) {
        super("GetGameCommand");
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
