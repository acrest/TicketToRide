package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.GetGameDescriptionCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/14/17.
 */

public class ClientGetGameDescriptionCommand extends GetGameDescriptionCommand {
    public ClientGetGameDescriptionCommand(int gameID) {
        super(gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
