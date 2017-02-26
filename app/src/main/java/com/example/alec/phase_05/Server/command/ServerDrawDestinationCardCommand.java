package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawDestinationCardCommand extends DrawDestinationCardCommand {

    public ServerDrawDestinationCardCommand(String commandName, String userName, String password, int gameID) {
        super(commandName, userName, password, gameID);
    }

    @Override
    public Result execute() {
        return null;
    }
}
