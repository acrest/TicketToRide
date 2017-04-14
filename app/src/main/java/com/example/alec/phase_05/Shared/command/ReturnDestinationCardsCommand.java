package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public abstract class ReturnDestinationCardsCommand extends GameCommand {
    private DestinationCard[] cards;

    public ReturnDestinationCardsCommand(String playerName, int gameID, DestinationCard[] cards) {
        super("ReturnDestinationCard", playerName, gameID);
        this.cards = cards;
    }

    public DestinationCard[] getCards() {
        return cards;
    }
}
