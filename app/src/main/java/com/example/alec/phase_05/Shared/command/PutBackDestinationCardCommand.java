package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public abstract class PutBackDestinationCardCommand extends GameCommand {

    private DestinationCard card;

    public PutBackDestinationCardCommand(String userName, String password, int gameID, DestinationCard card) {
        super("PutBackDestinationCard", userName, password, gameID);
        this.card = card;
    }

    public DestinationCard getCard() {
        return card;
    }
}
