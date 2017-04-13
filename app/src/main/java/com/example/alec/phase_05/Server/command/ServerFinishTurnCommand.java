package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.FinishTurnCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerFinishTurnCommand extends FinishTurnCommand {
    public ServerFinishTurnCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().finishTurn(getPlayerName(), getGameId()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().finishTurn(getPlayerName(), getGameId());
    }
}
