package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractGetGameDescriptionCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameDescription;

/**
 * Created by samuel on 2/14/17.
 */

public class ClientGetGameDescriptionCommand extends AbstractGetGameDescriptionCommand {
    public ClientGetGameDescriptionCommand(String username, String password, int gameID) {
        super(username, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
