package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.DrawInitialDestinationCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.io.Serializable;

/**
 * Created by Alec on 4/15/17.
 */

public class ServerDrawInitialDestinationCardsCommand extends DrawInitialDestinationCommand implements Serializable {
    public ServerDrawInitialDestinationCardsCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().drawInitialDestinationCards(getPlayerName(), getGameId()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().drawInitialDestinationCards(getPlayerName(), getGameId());
    }
}
