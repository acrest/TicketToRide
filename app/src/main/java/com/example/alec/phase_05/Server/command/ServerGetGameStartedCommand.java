package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameStartedCommand;
import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerGetGameStartedCommand extends GetGameStartedCommand {
    public ServerGetGameStartedCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.get_instance().getGameStartedCommand(getUserName(), getGameID()));
        return result;
    }
}
