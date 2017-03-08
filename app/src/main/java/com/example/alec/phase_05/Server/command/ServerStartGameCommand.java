package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.StartGameCommand;

/**
 * Created by samuel on 3/7/17.
 */

public class ServerStartGameCommand extends StartGameCommand {
    public ServerStartGameCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
//        Result result = new ServerResult();
//        result.setResultObject(ServerFacade.get_instance().startGame());
//        return new ServerResult();
        return null;
    }
}
