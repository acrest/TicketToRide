package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.GameInfo;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class GameStartedCommand extends BaseCommand {
    private GameInfo gameInfo;

    public GameStartedCommand(GameInfo gameInfo) {
        super("GameStarted");
        this.gameInfo = gameInfo;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }
}
