package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.ReturnedDestinationCardCommand;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerReturnedDestinationCard extends ReturnedDestinationCardCommand {
    public ServerReturnedDestinationCard(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
