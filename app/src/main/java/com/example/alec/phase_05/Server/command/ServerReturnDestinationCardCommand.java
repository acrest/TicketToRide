package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.ReturnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerReturnDestinationCardCommand extends ReturnDestinationCardCommand implements Serializable {
    public ServerReturnDestinationCardCommand(String playerName, int gameID, DestinationCard card) {
        super(playerName, gameID, card);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().returnDestinationCard(getPlayerName(), getGameId(), getCard()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().returnDestinationCard(getPlayerName(), getGameId(), getCard());
    }
}
