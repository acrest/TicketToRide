package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class AbstractGetGameListCommand extends AuthorizedCommand {
    /**
     * @param commandName command name from BaseCommand
     * @param userName    username of client
     * @param password    password of client
     */
    public AbstractGetGameListCommand(String commandName, String userName, String password) {
        super(commandName, userName, password);
    }

    @Override
    public Result execute() {
        return null;
    }
}
