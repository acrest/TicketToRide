package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.DrawnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerDrawnDestinationCardCommand extends DrawnDestinationCardCommand {
    public ServerDrawnDestinationCardCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
