package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.DrawnTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerDrawnTrainCardCommand extends DrawnTrainCardCommand implements Serializable {
    public ServerDrawnTrainCardCommand(String playerName, int remainingCards) {
        super(playerName, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
