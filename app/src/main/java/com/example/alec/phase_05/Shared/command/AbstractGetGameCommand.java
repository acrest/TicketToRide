package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 2/14/17.
 */

public abstract class AbstractGetGameCommand extends GameCommand {
    /**
     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     */
    public AbstractGetGameCommand(String userName, String password, int gameID) {
        super("GetGame", userName, password, gameID);
    }
}
