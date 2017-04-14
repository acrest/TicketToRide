package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.ReturnedDestinationCardsCommand;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerReturnedDestinationCardsCommand extends ReturnedDestinationCardsCommand implements Serializable {
    public ServerReturnedDestinationCardsCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
