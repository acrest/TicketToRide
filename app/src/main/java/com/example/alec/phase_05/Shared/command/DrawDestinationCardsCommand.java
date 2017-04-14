package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class DrawDestinationCardsCommand extends GameCommand {
    public DrawDestinationCardsCommand(String playerName, int gameID) {
        super("DrawDestinationCards", playerName, gameID);
    }
}
