package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawTrainCardCommand extends DrawTrainCardCommand implements Serializable {
    public ServerDrawTrainCardCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().drawTrainCard(getPlayerName(), getGameId()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().drawTrainCard(getPlayerName(), getGameId());
    }
}
