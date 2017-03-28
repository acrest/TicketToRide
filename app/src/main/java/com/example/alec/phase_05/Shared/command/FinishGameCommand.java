package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.command.GameCommand;

/**
 * Created by samuel on 3/27/17.
 */

public abstract class FinishGameCommand extends GameCommand {
    public FinishGameCommand(String playerName, int gameID) {
        super("FinishGame", playerName, gameID);
    }
}
