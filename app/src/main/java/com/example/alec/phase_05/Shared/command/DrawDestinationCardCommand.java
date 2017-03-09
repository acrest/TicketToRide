package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class DrawDestinationCardCommand extends GameCommand {
    public DrawDestinationCardCommand(String userName, String password, int gameID) {
        super("DrawDestinationCard", userName, password, gameID);
    }
}
