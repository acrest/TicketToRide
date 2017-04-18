package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class ReturnedDestinationCardsCommand extends BaseCommand {
    private String playerName;
    private int playerCards;
    private int remainingCards;

    public ReturnedDestinationCardsCommand(String playerName, int playerCards, int remainingCards) {
        super("ReturnedDestinationCards");
        this.playerName = playerName;
        this.playerCards = playerCards;
        this.remainingCards = remainingCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public int getPlayerCards() {
        return playerCards;
    }
}
