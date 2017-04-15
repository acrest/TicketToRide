package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.DrawInitialTrainCommand;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by Alec on 4/15/17.
 */

public class ClientDrawInitialTrainCardCommand extends DrawInitialTrainCommand {
    public ClientDrawInitialTrainCardCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
