package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.DrawDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientDrawDestinationCardsCommand extends DrawDestinationCardsCommand {
    public ClientDrawDestinationCardsCommand(String playerName, int gameID) {
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
