package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.StartGameCommand;

/**
 * Created by samuel on 3/7/17.
 */

public class ServerStartGameCommand extends StartGameCommand {
    public ServerStartGameCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().startGame(getGameId()));
        return result;
    }
}
