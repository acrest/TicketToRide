package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class DrawTrainCardCommand extends GameCommand {

    private int cardIndex;

    public DrawTrainCardCommand(String userName, String password, int gameID, int cardIndex) {
        super("DrawTrainCard", userName, password, gameID);
        this.cardIndex = cardIndex;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}
