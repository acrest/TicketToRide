package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public abstract class ReturnDestinationCardCommand extends GameCommand {
    private DestinationCard card;

    public ReturnDestinationCardCommand(String playerName, int gameID, DestinationCard card) {
        super("PutBackDestinationCard", playerName, gameID);
        this.card = card;
    }

    public DestinationCard getCard() {
        return card;
    }
}
