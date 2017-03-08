package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.StartGameCommand;

/**
 * Created by samuel on 3/7/17.
 */

public class ClientStartGameCommand extends StartGameCommand {
    public ClientStartGameCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
