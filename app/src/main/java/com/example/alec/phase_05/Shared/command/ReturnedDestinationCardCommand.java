package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class ReturnedDestinationCardCommand extends BaseCommand {
    private String playerName;
    private int remainingCards;

    public ReturnedDestinationCardCommand(String playerName, int remainingCards) {
        super("ReturnedDestinationCard");
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
