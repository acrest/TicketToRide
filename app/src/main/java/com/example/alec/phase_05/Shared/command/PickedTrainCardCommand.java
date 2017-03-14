package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/11/17.
 */

public abstract class PickedTrainCardCommand extends GameCommand {
    private int cardIndex;
    private TrainCard nextCard;

    public PickedTrainCardCommand(String playerName, int gameID, int cardIndex, TrainCard nextCard) {
        super("PickedTrainCard", playerName, gameID);
        this.cardIndex = cardIndex;
        this.nextCard = nextCard;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public TrainCard getNextCard() {
        return nextCard;
    }
}
