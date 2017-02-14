package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class AbstractJoinGameCommand extends GameCommand {
    private String color;
    /**
     * @param userName    username of client
     * @param password    password of client
     * @param gameID      id of the game for which this command operates
     * @param color
     */
    public AbstractJoinGameCommand(String userName, String password, int gameID, String color) {
        super("JoinGame", userName, password, gameID);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Result execute() {
        return null;
    }
}
