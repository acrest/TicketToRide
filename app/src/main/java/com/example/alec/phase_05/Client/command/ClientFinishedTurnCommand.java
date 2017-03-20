package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.FinishedTurnCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ClientFinishedTurnCommand extends FinishedTurnCommand {
    public ClientFinishedTurnCommand(String playerName) {
        super(playerName);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().finishTurn(getPlayerName());
        return null;
    }
}
