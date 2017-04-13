package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.FinishGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/27/17.
 */

public class ServerFinishGameCommand extends FinishGameCommand implements Serializable {
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
