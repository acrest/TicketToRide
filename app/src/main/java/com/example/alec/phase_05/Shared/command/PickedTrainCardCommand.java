package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/11/17.
 */

public abstract class PickedTrainCardCommand extends BaseCommand {
    private String playerName;
    private int cardIndex;
    private TrainCard nextCard;

    public PickedTrainCardCommand(String playerName, int cardIndex, TrainCard nextCard) {
        super("PickedTrainCard");
        this.playerName = playerName;
        this.cardIndex = cardIndex;
        this.nextCard = nextCard;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public TrainCard getNextCard() {
        return nextCard;
    }
}
