package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnTrainCardCommand extends BaseCommand {
    private String playerName;
    private int remainingCards;

    public DrawnTrainCardCommand(String playerName, int remainingCards) {
        super("DrawnTrainCard");
        this.playerName = playerName;
        this.remainingCards = remainingCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRemainingCards() {
        return remainingCards;
    }
}
