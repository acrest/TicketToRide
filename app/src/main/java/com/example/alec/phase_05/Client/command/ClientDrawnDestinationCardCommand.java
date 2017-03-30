package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.DrawnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientDrawnDestinationCardCommand extends DrawnDestinationCardCommand {
    public ClientDrawnDestinationCardCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().drawDestinationCard(getPlayerName(), getRemainingCards());
        return null;
    }
}
