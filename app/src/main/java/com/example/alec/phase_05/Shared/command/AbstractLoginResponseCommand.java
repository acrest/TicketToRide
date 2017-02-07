package com.example.alec.phase_05.Shared.command;

/**
 * Holds data needed by the corresponding client and server commands.
 * Created by samuel on 2/3/17.
 */
public abstract class AbstractLoginResponseCommand extends BaseCommand
{
    private boolean success;

    /**
     * Sets the command name.
     */
    public AbstractLoginResponseCommand()
    {
        super("LoginResponse");
    }

    /**
     * @return success of login attempt
     */
    public boolean getSuccess()
    {
        return success;
    }

    /**
     * @param s success of login attempt
     */
    public void setSuccess(boolean s)
    {
        success = s;
    }
}
