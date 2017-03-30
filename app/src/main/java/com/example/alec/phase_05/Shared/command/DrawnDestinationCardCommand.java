package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnDestinationCardCommand extends BaseCommand {
    private String playerName;
    private int remainingCards;

    public DrawnDestinationCardCommand(String playerName, int remainingCards) {
        super("DrawnDestinationCard");
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
