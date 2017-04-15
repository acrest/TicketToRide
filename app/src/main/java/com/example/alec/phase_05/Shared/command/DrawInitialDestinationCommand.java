package com.example.alec.phase_05.Shared.command;

/**
 * Created by Alec on 4/15/17.
 */

public abstract class DrawInitialDestinationCommand extends GameCommand {
    public DrawInitialDestinationCommand(String playerName, int gameID) {
        super("DrawInitialDestinationCards", playerName, gameID);
    }
}
