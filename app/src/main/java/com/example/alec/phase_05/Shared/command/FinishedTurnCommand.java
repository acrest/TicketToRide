package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class FinishedTurnCommand extends BaseCommand {
    private String playerName;

    public FinishedTurnCommand(String playerName) {
        super("FinishedTurn");
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
