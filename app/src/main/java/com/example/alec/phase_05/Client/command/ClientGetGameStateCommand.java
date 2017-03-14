package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientGetGameStateCommand extends GetGameStateCommand {
    public ClientGetGameStateCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        return null;
    }
}
