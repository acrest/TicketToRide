package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.PickTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/11/17.
 */

public class ClientPickTrainCardCommand extends PickTrainCardCommand {
    public ClientPickTrainCardCommand(String playerName, int gameID, int cardIndex) {
        super(playerName, gameID, cardIndex);
    }

    @Override
    public Result execute() {
        return null;
    }


    @Override
    public void reExecute() {

    }
}
