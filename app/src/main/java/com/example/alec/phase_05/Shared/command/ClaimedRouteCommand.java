package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class ClaimedRouteCommand extends BaseCommand {
    private String playerName;
    private int routeId;
    private int remainingTrainCards;
    private int playerRemainingTrainCards;
    private int playerRemainingTrains;

    public ClaimedRouteCommand(String playerName, int routeId, int remainingTrainCards, int playerRemainingTrainCards, int playerRemainingTrains) {
        super("ClaimedRoute");
        this.playerName = playerName;
        this.routeId = routeId;
        this.remainingTrainCards = remainingTrainCards;
        this.playerRemainingTrainCards = playerRemainingTrainCards;
        this.playerRemainingTrains = playerRemainingTrains;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getRemainingTrainCards() {
        return remainingTrainCards;
    }

    public int getPlayerRemainingTrainCards() {
        return playerRemainingTrainCards;
    }

    public int getPlayerRemainingTrains() {
        return playerRemainingTrains;
    }
}
