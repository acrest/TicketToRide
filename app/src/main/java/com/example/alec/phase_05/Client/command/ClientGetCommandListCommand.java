package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetCommandListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/1/17.
 */

public class ClientGetCommandListCommand extends GetCommandListCommand {
    public ClientGetCommandListCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
