package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.DrawInitialDestinationCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by Alec on 4/15/17.
 */

public class ClientDrawInitialDestinationCardsCommand extends DrawInitialDestinationCommand {
    public ClientDrawInitialDestinationCardsCommand(String playerName, int gameID) {
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
