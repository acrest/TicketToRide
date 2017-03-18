package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.ClaimedRouteCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerClaimedRouteCommand extends ClaimedRouteCommand {
    public ServerClaimedRouteCommand(String playerName, int routeId) {
        super(playerName, routeId);
    }

    @Override
    public Result execute() {
        return null;
    }
}
