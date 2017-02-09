package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractGetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientGetGameListCommand extends AbstractGetGameListCommand {
    /**
     * @param commandName command name from BaseCommand
     * @param userName    username of client
     * @param password    password of client
     */
    public ClientGetGameListCommand(String commandName, String userName, String password) {
        super(commandName, userName, password);
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
