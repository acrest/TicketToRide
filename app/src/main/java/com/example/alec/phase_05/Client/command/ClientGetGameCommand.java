package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/10/17.
 */

public class ClientGetGameCommand extends GetGameCommand {
    public ClientGetGameCommand(int gameId) {
        super(gameId);
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
