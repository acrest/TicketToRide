package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetCommandListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/1/17.
 */

public class ClientGetCommandListCommand extends GetCommandListCommand {

    public ClientGetCommandListCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
