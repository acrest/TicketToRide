package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.PickedTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerPickedTrainCardCommand extends PickedTrainCardCommand {
    public ServerPickedTrainCardCommand(String playerName, int cardIndex, TrainCard nextCard, int remainingCards) {
        super(playerName, cardIndex, nextCard, remainingCards);
    }

    @Override
    public Result execute() {
        return null;
    }
}
