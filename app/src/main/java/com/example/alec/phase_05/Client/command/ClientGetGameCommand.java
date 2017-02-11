package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractGetGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/10/17.
 */

public class ClientGetGameCommand extends AbstractGetGameCommand {
    /**

     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     */
    public ClientGetGameCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    /**
     * Does nothing.
     * This method should never be called.
     * It is only here to make the class non-abstract.
     * @return null
     */
    public Result execute()
    {
        return null;
    }
}
