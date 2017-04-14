package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.*;

/**
 * Submits a login request to the server facade.
 * Created by samuel on 2/4/17.
 */
public class ServerLoginCommand extends LoginCommand implements Serializable {
    /**
     * @param username username of client
     * @param password password of client
     */
    public ServerLoginCommand(String username, String password) {
        super(username, password);
    }

    /**
     * Submits a login request to the server facade.
     * @return Result object containing a ServerLoginResponseCommand
     */
    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().login(getUsername(), getPassword()));
        return result;
    }
}
