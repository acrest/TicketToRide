package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawDestinationCardCommand extends DrawDestinationCardCommand implements Serializable {
    public ServerDrawDestinationCardCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().drawDestinationCard(getPlayerName(), getGameId()));
        return result;
    }
}
