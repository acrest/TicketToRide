package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientGetGameStateCommand extends GetGameStateCommand {

    public ClientGetGameStateCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
