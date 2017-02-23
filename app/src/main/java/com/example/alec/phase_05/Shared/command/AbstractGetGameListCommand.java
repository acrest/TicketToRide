package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public abstract class AbstractGetGameListCommand extends AuthorizedCommand {
    /**
     * @param userName    username of client
     * @param password    password of client
     */
    public AbstractGetGameListCommand(String userName, String password) {
        super("GetGameList", userName, password);
    }
}
