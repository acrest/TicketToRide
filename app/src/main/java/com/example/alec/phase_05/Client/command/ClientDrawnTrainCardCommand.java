package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.DrawnTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientDrawnTrainCardCommand extends DrawnTrainCardCommand {
    public ClientDrawnTrainCardCommand(String playerName) {
        super(playerName);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().drawTrainCard(getPlayerName());
        return null;
    }
}
