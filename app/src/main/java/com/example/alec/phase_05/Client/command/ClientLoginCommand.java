package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Holds data needed by the corresponding ServerLoginCommand.
 * Created by samuel on 2/4/17.
 */
public class ClientLoginCommand extends AbstractLoginCommand
{
    /**
     * @param userName username of client
     * @param password password of client
     */
    public ClientLoginCommand(String userName, String password)
    {
        super(userName, password);
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
