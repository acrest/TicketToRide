package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class ReturnedDestinationCardCommand extends BaseCommand {
    private String playerName;

    public ReturnedDestinationCardCommand(String playerName) {
        super("ReturnedDestinationCard");
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
