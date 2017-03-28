package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.FinishGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/27/17.
 */

public class ServerFinishGameCommand extends FinishGameCommand {
    public ServerFinishGameCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().finishGame(getPlayerName(), getGameId()));
        return result;
    }
}
