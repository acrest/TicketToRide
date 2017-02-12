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
     */
    public ClientCreateGameCommand(String userName, String password, String gameName, int numberOfPlayers, String hostColor) {
        super(userName, password, gameName, numberOfPlayers, hostColor);
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
