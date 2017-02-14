package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Holds data needed by the corresponding ServerRegisterCommand.
 * Created by samuel on 2/4/17.
 */
public class ClientRegisterCommand extends AbstractRegisterCommand
{
    /**
     * @param userName username of client
     * @param password password of client
     */
    public ClientRegisterCommand(String userName, String password)
    {
        super(userName, password);
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
