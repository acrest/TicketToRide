package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.ClaimRouteCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerClaimRouteCommand extends ClaimRouteCommand implements Serializable {
    public ServerClaimRouteCommand(String playerName, int gameID, int routeId, TrainType type) {
        super(playerName, gameID, routeId, type);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().claimRoute(getPlayerName(), getGameId(), getRouteId(), getRouteType()));
        return result;
    }
}
