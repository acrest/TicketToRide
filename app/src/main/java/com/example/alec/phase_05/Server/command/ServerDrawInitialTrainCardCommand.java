package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.DrawInitialTrainCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.io.Serializable;

/**
 * Created by Alec on 4/15/17.
 */

public class ServerDrawInitialTrainCardCommand extends DrawInitialTrainCommand implements Serializable {
    public ServerDrawInitialTrainCardCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().drawInitialTrainCard(getPlayerName(), getGameId()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().drawInitialTrainCard(getPlayerName(), getGameId());
    }
}
