package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
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
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().claimRoute(getPlayerName(), getGameId(), getRouteId()));
        return result;
    }
}
