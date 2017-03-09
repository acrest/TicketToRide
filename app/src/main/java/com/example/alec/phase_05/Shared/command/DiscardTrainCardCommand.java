package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/8/17.
 */

public abstract class DiscardTrainCardCommand extends GameCommand {

    private TrainCard card;

    public DiscardTrainCardCommand(String userName, String password, int gameID, TrainCard card) {
        super("DiscardTrainCard", userName, password, gameID);
        this.card = card;
    }

    public TrainCard getCard() {
        return card;
    }
}
