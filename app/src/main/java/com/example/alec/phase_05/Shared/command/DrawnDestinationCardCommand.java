package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnDestinationCardCommand extends BaseCommand {

    private String playerName;
    private DestinationCard card;

    public DrawnDestinationCardCommand(String playerName, DestinationCard card) {
        super("DrawnDestinationCard");
        this.playerName = playerName;
        this.card = card;
    }

    public String getPlayerName() {
        return playerName;
    }

    public DestinationCard getCard() {
        return card;
    }
}
