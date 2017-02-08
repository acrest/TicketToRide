package com.example.alec.phase_05.Shared.command;

/**
 * Holds data for the corresponding client and server commands.
 * Created by samuel on 2/3/17.
 */
public abstract class AbstractLoginCommand extends AuthorizedCommand
{
    /**
     * @param userName username of client
     * @param password password of client
     */
    public AbstractLoginCommand(String userName, String password)
    {
        super("Login", userName, password);
    }


}
