package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameInfoCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 4/10/17.
 */

public class ClientGetGameInfoCommand extends GetGameInfoCommand {
    public ClientGetGameInfoCommand(int gameId) {
        super(gameId);
    }

    @Override
    public Result execute() {
        return null;
    }
}
