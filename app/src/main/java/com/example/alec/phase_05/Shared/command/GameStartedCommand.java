package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.GameState;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class GameStartedCommand extends BaseCommand {
    private GameState gameState;

    public GameStartedCommand(GameState gameState) {
        super("GameStarted");
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
