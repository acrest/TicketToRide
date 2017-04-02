package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.ReturnedDestinationCardCommand;

/**
 * Created by samuel on 3/16/17.
 */

public class ClientReturnedDestinationCardCommand extends ReturnedDestinationCardCommand {
    public ClientReturnedDestinationCardCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().returnDestinationCard(getPlayerName(), getRemainingCards());
        return null;
    }
}
