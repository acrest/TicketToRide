package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Submits a login request to the server facade.
 * Created by samuel on 2/4/17.
 */
public class ServerLoginCommand extends AbstractLoginCommand
{
    /**
     * Should not be used.
     * Instances of this class will be created by deserialization of the corresponding client class.
     */
    public ServerLoginCommand()
    {
        super(null, null);
    }

    /**
     * Submits a login request to the server facade.
     * @return Result object containing a ServerLoginResponseCommand
     */
    public Result execute()
    {
        boolean success = ServerFacade.getInstance().login(getUserName(), getPassword());
        ServerLoginResponseCommand cmd = new ServerLoginResponseCommand();
        cmd.setSuccess(success);
        Result result = new Result();
        result.setResultObject(cmd);
        return result;
    }
}