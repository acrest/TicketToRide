package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.CreateGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientCreateGameCommand extends CreateGameCommand {
    /**
     * @param gameName        name of game to be created
     * @param numberOfPlayers number of players in game to be created
     * @param hostName
     * @param hostColor
     */
    public ClientCreateGameCommand(String gameName, int numberOfPlayers, String hostName, String hostColor) {
        super(gameName, numberOfPlayers, hostName, hostColor);

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
