package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.DrawnDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerDrawnDestinationsCardsCommand extends DrawnDestinationCardsCommand implements Serializable {
    public ServerDrawnDestinationsCardsCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}