package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/13/17.
 */

public abstract class GetNextChangeCommand extends GameCommand {
    public GetNextChangeCommand(String playerName, int gameId) {
        super("GetNextChange", playerName, gameId);
    }
}
