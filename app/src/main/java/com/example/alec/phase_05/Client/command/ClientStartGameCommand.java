package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.StartGameCommand;

/**
 * Created by samuel on 3/7/17.
 */

public class ClientStartGameCommand extends StartGameCommand {
    public ClientStartGameCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        return null;
    }
}
