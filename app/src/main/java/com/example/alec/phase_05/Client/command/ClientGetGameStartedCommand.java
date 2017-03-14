package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameStartedCommand;
import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/8/17.
 */

public class ClientGetGameStartedCommand extends GetGameStartedCommand {
    public ClientGetGameStartedCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
