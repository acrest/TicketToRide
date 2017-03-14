package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.ClaimRouteCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Route;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerClaimRouteCommand extends ClaimRouteCommand {
    public ServerClaimRouteCommand(String playerName, int gameID, int routeId) {
        super(playerName, gameID, routeId);
    }

    @Override
    public Result execute() {
        return null; //TODO
    }
}
