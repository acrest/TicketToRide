package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractCreateGameCommand;
import com.example.alec.phase_05.Shared.command.AbstractGetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientCreateGameCommand extends AbstractCreateGameCommand {
    /**
     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     */
    public ClientCreateGameCommand(String userName, String password, int gameID) {
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
