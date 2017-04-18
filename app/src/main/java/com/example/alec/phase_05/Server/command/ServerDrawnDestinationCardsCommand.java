package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.DrawnDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerDrawnDestinationCardsCommand extends DrawnDestinationCardsCommand implements Serializable {
    public ServerDrawnDestinationCardsCommand(String playerName, int playerCards, int remainingCards) {
        super(playerName, playerCards, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
