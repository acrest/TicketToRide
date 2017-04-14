package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.FinishTurnCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ClientFinishTurnCommand extends FinishTurnCommand {
    public ClientFinishTurnCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
