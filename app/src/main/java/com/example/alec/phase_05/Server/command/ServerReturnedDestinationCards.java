package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.ReturnedDestinationCardsCommand;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerReturnedDestinationCards extends ReturnedDestinationCardsCommand implements Serializable {
    public ServerReturnedDestinationCards(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
