package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Holds data needed by the corresponding ClientLoginResponseCommand.
 * Created by samuel on 2/4/17.
 */
public class ServerLoginResponseCommand extends AbstractLoginResponseCommand
{
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
