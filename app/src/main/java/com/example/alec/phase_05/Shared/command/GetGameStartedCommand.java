package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/8/17.
 */

public abstract class GetGameStartedCommand extends GameCommand {
    public GetGameStartedCommand(String playerName, int gameID) {
        super("GetGameStarted", playerName, gameID);
    }
}
