package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.Route;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class ClaimRouteCommand extends GameCommand {

    private Route route;

    public ClaimRouteCommand(String userName, String password, int gameID, Route route) {
        super("ClaimRoute", userName, password, gameID);
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
