package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.Route;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class ClaimRouteCommand extends GameCommand {

    private int routeId;

    public ClaimRouteCommand(String playerName, int gameID, int routeId) {
        super("ClaimRoute", playerName, gameID);
        this.routeId = routeId;
    }

    public int getRouteId() {
        return routeId;
    }
}
