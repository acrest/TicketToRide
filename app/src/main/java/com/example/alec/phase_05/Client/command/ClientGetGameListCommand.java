package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientGetGameListCommand extends GetGameListCommand {

    public ClientGetGameListCommand(String name) {
        super(name);
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
