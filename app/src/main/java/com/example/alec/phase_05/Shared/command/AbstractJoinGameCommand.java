package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class AbstractJoinGameCommand extends GameCommand {
    /**
     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     */
    public AbstractJoinGameCommand(String userName, String password, int gameID) {
        super("JoinGame", userName, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
