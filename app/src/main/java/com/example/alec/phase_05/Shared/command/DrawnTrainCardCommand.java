package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnTrainCardCommand extends BaseCommand {

    private String playerName;
    private int cardIndex;
    private TrainCard card;
    private TrainCard nextCard;

    public DrawnTrainCardCommand(String playerName, int cardIndex, TrainCard card, TrainCard nextCard) {
        super("DrawnTrainCard");
        this.playerName = playerName;
        this.cardIndex = cardIndex;
        this.card = card;
        this.nextCard = nextCard;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public TrainCard getCard() {
        return card;
    }

    public TrainCard getNextCard() {
        return nextCard;
    }
}
