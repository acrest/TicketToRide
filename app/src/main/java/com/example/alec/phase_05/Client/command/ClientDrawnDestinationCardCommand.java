package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.DrawnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientDrawnDestinationCardCommand extends DrawnDestinationCardCommand {
    public ClientDrawnDestinationCardCommand(String playerName, DestinationCard card) {
        super(playerName, card);
    }

    @Override
    public Result execute() {
        ClientFacade facade = ClientFacade.getInstance();
        facade.drawDestinationCard(getPlayerName(), getCard());
        return null;
    }
}
