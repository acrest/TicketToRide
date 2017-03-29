package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/11/17.
 */

public abstract class PickedTrainCardCommand extends BaseCommand {
    private String playerName;
    private int cardIndex;
    private TrainCard nextCard;
    private int remainingCards;

    public PickedTrainCardCommand(String playerName, int cardIndex, TrainCard nextCard, int remainingCards) {
        super("PickedTrainCard");
        this.playerName = playerName;
        this.cardIndex = cardIndex;
        this.nextCard = nextCard;
        this.remainingCards = remainingCards;
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

    public int getRemainingCards() {
        return remainingCards;
    }
}
