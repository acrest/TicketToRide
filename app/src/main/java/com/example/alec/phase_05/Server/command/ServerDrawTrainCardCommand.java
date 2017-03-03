package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.Server;
import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawTrainCardCommand extends DrawTrainCardCommand {

    public ServerDrawTrainCardCommand(String userName, String password, int gameID, int cardIndex) {
        super(userName, password, gameID, cardIndex);
    }

    @Override
    public Result execute() {
        ServerFacade facade = ServerFacade.get_instance();
        TrainCard card = facade.drawTrainCard(getUserName(), getPassword(), getGameID(), getCardIndex());
        Result result = new ServerResult();
        result.setResultObject(card);
        return result;
    }
}
