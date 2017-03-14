package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class FinishTurnCommand extends GameCommand {
    public FinishTurnCommand(String playerName, int gameID) {
        super("FinishTurn", playerName, gameID);
    }
}
