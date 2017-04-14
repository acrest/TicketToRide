package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.RegisterCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerRegisterCommand extends RegisterCommand implements Serializable {
    /**
     * @param username username of client
     * @param password password of client
     */
    public ServerRegisterCommand(String username, String password) {
        super(username, password);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().registerUser(getUsername(), getPassword()));
        return result;
    }
}
