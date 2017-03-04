package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/1/17.
 */

public abstract class GetGameStateCommand extends GameCommand {

    public GetGameStateCommand(String userName, String password, int gameID) {
        super("GetGameState", userName, password, gameID);
    }
}
