package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnDestinationCardCommand extends BaseCommand {
    private String playerName;

    public DrawnDestinationCardCommand(String playerName) {
        super("DrawnDestinationCard");
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
