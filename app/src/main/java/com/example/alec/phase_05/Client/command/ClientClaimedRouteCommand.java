package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.ClaimedRouteCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ClientClaimedRouteCommand extends ClaimedRouteCommand {
    public ClientClaimedRouteCommand(String playerName, int routeId) {
        super(playerName, routeId);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().claimRoute(getPlayerName(), getRouteId());
        return null;
    }
}
