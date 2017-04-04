package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class ClaimRouteCommand extends GameCommand {

    private int routeId;
    private TrainType routeType;

    public ClaimRouteCommand(String playerName, int gameID, int routeId, TrainType type) {
        super("ClaimRoute", playerName, gameID);
        this.routeId = routeId;
        this.routeType = type;
    }

    public int getRouteId() {
        return routeId;
    }

    public TrainType getRouteType() {
        return routeType;
    }
}
