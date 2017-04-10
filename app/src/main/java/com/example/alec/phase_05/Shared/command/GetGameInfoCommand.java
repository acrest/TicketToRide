package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 4/10/17.
 */

public abstract class GetGameInfoCommand extends BaseCommand {
    private int gameId;

    public GetGameInfoCommand(int gameId) {
        super("GetGameInfo");
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
