package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.ReturnDestinationCardsCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerReturnDestinationCardsCommand extends ReturnDestinationCardsCommand implements Serializable {
    public ServerReturnDestinationCardsCommand(String playerName, int gameID, DestinationCard[] cards) {
        super(playerName, gameID, cards);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().returnDestinationCards(getPlayerName(), getGameId(), getCards()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().returnDestinationCards(getPlayerName(), getGameId(), getCards());
    }
}
