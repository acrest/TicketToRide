package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawDestinationCardsCommand extends DrawDestinationCardsCommand implements Serializable {
    public ServerDrawDestinationCardsCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().drawDestinationCards(getPlayerName(), getGameId()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().drawDestinationCards(getPlayerName(), getGameId());
    }
}
