package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/1/17.
 */

public abstract class GetCommandListCommand extends GameCommand {

    public GetCommandListCommand(String userName, String password, int gameID) {
        super("GetCommandList", userName, password, gameID);
    }
}