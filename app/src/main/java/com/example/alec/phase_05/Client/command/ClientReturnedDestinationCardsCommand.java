package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.ReturnedDestinationCardsCommand;

/**
 * Created by samuel on 3/16/17.
 */

public class ClientReturnedDestinationCardsCommand extends ReturnedDestinationCardsCommand {
    public ClientReturnedDestinationCardsCommand(String playerName, int playerCards, int remainingCards) {
        super(playerName, playerCards, remainingCards);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().returnDestinationCard(getPlayerName(), getPlayerCards(), getRemainingCards());
        return null;
    }
}
