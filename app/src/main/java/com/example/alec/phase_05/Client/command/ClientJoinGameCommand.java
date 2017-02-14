package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractJoinGameCommand;
import com.example.alec.phase_05.Shared.command.AbstractLoginCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientJoinGameCommand extends AbstractJoinGameCommand {

    /**
     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     */
    public ClientJoinGameCommand(String userName, String password, int gameID, String color) {
        super(userName, password, gameID, color);
    }

    /**
     * Does nothing.
     * This method should never be called.
     * It is only here to make the class non-abstract.
     * @return null
     */
    @Override
    public Result execute()
    {
        return null;
    }
}
