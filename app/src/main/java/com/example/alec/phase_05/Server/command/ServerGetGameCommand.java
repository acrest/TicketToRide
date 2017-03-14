package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Game;

/**
 * Created by samuel on 2/14/17.
 */

public class ServerGetGameCommand extends GetGameCommand {
    public ServerGetGameCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getGame(getGameId()));
        return result;
    }
}
