package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/1/17.
 */

public abstract class GetCommandListCommand extends GameCommand {

    public GetCommandListCommand(String commandName, String userName, String password, int gameID) {
        super(commandName, userName, password, gameID);
    }
}
