package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.GameFinishedCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/27/17.
 */

public class ClientGameFinishedCommand extends GameFinishedCommand {
    @Override
    public Result execute() {
        ClientFacade.getInstance().finishGame();
        return null;
    }
}
