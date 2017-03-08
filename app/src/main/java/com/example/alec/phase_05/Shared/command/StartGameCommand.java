package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/7/17.
 */

public abstract class StartGameCommand extends GameCommand {
    public StartGameCommand(String userName, String password, int gameID) {
        super("StartGame", userName, password, gameID);
        setPlayerId(-1);
    }
}
