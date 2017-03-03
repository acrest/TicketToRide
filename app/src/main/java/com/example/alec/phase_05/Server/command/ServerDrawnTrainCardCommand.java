package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.DrawnTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerDrawnTrainCardCommand extends DrawnTrainCardCommand {
    public ServerDrawnTrainCardCommand(String playerName, int cardIndex, TrainCard card, TrainCard nextCard) {
        super(playerName, cardIndex, card, nextCard);
    }

    @Override
    public Result execute() {
        return null;
    }
}
