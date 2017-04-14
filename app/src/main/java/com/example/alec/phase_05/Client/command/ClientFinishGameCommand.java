package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.FinishGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/27/17.
 */

public class ClientFinishGameCommand extends FinishGameCommand {
    public ClientFinishGameCommand(String playerName, int gameID) {
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
