package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.AbstractGetGameUpdatesCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/13/17.
 */

public class ClientGetGameUpdatesCommand extends AbstractGetGameUpdatesCommand {
    public ClientGetGameUpdatesCommand(String username, String password, int gameID, int lastUpdate) {
        super(username, password, gameID, lastUpdate);
    }

    @Override
    public Result execute() {
        return null;
    }
}
