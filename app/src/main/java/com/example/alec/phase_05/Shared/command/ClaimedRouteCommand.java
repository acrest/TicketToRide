package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/16/17.
 */

public abstract class ClaimedRouteCommand extends BaseCommand {
    private String playerName;
    private int routeId;

    public ClaimedRouteCommand(String playerName, int routeId) {
        super("ClaimedRoute");
        this.playerName = playerName;
        this.routeId = routeId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRouteId() {
        return routeId;
    }
}
