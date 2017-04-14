package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 4/13/17.
 */

public abstract class RejoinGameCommand extends GameCommand {
    public RejoinGameCommand(String playerName, int gameId) {
        super("RejoinGame", playerName, gameId);
    }
}
