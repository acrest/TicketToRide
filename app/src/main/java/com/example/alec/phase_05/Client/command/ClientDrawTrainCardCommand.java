package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientDrawTrainCardCommand extends DrawTrainCardCommand {

    public ClientDrawTrainCardCommand(String userName, String password, int gameID, int cardIndex) {
        super(userName, password, gameID, cardIndex);
    }

    public Result execute() {
        return null; //TODO
    }
}
