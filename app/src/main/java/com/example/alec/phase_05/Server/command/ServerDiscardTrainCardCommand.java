package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.DiscardTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerDiscardTrainCardCommand extends DiscardTrainCardCommand {
    public ServerDiscardTrainCardCommand(String userName, String password, int gameID, TrainCard card) {
        super(userName, password, gameID, card);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.get_instance().discardTrainCard(getUserName(), getGameID(), getCard()));
        return result;
    }
}
