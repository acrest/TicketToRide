package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.AbstractRegisterCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerRegisterCommand extends AbstractRegisterCommand {

    public ServerRegisterCommand(String username, String password) {
        super(username, password);
    }

    @Override
    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        boolean success = sf.registerUser(getUserName(), getPassword());
        Result result = new ServerResult();
        result.setResultObject(success);
        return result;
    }
}
