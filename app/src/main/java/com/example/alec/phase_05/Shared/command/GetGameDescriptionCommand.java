package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.GameDescription;

/**
 * Created by samuel on 2/14/17.
 */

public abstract class GetGameDescriptionCommand extends BaseCommand {
    private int gameId;

    public GetGameDescriptionCommand(int gameID) {
        super("GetGameDescription");
        this.gameId = gameID;
    }

    public int getGameId() {
        return gameId;
    }
}
