package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetNextChangeCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/13/17.
 */

public class ClientGetNextChangeCommand extends GetNextChangeCommand {
    public ClientGetNextChangeCommand(String playerName, int gameId) {
        super(playerName, gameId);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
