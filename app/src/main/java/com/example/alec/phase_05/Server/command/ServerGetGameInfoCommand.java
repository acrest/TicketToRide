package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameInfoCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 4/10/17.
 */

public class ServerGetGameInfoCommand extends GetGameInfoCommand {
    public ServerGetGameInfoCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getGameInfo(getGameId()));
        return result;
    }
}
