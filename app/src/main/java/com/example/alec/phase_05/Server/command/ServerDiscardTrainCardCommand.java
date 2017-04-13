package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.DiscardTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerDiscardTrainCardCommand extends DiscardTrainCardCommand {
    public ServerDiscardTrainCardCommand(String playerName, int gameID, TrainCard card) {
        super(playerName, gameID, card);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().discardTrainCard(getPlayerName(), getGameId(), getCard()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().discardTrainCard(getPlayerName(), getGameId(), getCard());
    }
}
