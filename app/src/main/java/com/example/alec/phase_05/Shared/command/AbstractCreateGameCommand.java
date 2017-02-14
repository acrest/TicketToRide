package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public abstract class AbstractCreateGameCommand extends AuthorizedCommand {
    private String gameName;
    private int numberOfPlayers;
    private String hostColor;

    /**
     * @param userName          username of client
     * @param password          password of client
     * @param gameName          name of game to be created
     * @param numberOfPlayers   number of players in game to be created
     * @param hostColor
     */
    public AbstractCreateGameCommand(String userName, String password, String gameName, int numberOfPlayers, String hostColor) {
        super("CreateGame", userName, password);
        this.hostColor = hostColor;
        this.gameName = gameName;
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getHostColor() {
        return hostColor;
    }

    public void setHostColor(String hostColor) {
        this.hostColor = hostColor;
    }
}
