package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.ReturnDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ClientReturnDestinationCardsCommand extends ReturnDestinationCardsCommand {
    public ClientReturnDestinationCardsCommand(String playerName, int gameID, DestinationCard[] cards) {
        super(playerName, gameID, cards);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
