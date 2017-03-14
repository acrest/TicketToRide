package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public abstract class JoinGameCommand extends GameCommand {
    private String color;

    public JoinGameCommand(String playerName, int gameID, String color) {
        super("JoinGame", playerName, gameID);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
