package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerGetGameStateCommand extends GetGameStateCommand implements Serializable {
    public ServerGetGameStateCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getGameState(getGameId()));
        return result;
    }
}
