package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.DiscardTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ClientDiscardTrainCardCommand extends DiscardTrainCardCommand {
    public ClientDiscardTrainCardCommand(String playerName, int gameID, TrainCard card) {
        super(playerName, gameID, card);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
