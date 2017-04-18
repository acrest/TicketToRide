package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.DrawnDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientDrawnDestinationCardsCommand extends DrawnDestinationCardsCommand {
    public ClientDrawnDestinationCardsCommand(String playerName, int playerCards, int remainingCards) {
        super(playerName, playerCards, remainingCards);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().drawDestinationCard(getPlayerName(), getPlayerCards(), getRemainingCards());
        return null;
    }
}
