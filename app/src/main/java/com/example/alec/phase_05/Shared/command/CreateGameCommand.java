package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public abstract class CreateGameCommand extends BaseCommand {
    private String gameName;
    private int numberOfPlayers;
    private String hostName;
    private String hostColor;

    /**
     * @param gameName          name of game to be created
     * @param numberOfPlayers   number of players in game to be created
     * @param hostColor
     */
    public CreateGameCommand(String gameName, int numberOfPlayers, String hostName, String hostColor) {
        super("CreateGame");
        this.gameName = gameName;
        this.numberOfPlayers = numberOfPlayers;
        this.hostName = hostName;
        this.hostColor = hostColor;
    }

    public String getGameName() {
        return gameName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getHostName() {
        return hostName;
    }

    public String getHostColor() {
        return hostColor;
    }
}
