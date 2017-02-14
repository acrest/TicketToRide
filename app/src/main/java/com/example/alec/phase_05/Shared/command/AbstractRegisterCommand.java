package com.example.alec.phase_05.Shared.command;

/**
 * Holds data for the corresponding client and server commands.
 * Created by samuel on 2/3/17.
 */
public abstract class AbstractRegisterCommand extends AuthorizedCommand
{
    /**
     * @param userName username of register request
     * @param password password of register request
     */
    public AbstractRegisterCommand(String userName, String password)
    {
        super("Register", userName, password);
    }
}
