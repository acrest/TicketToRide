package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameStartedCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerGetGameStartedCommand extends GetGameStartedCommand {
    public ServerGetGameStartedCommand(String playerName, int gameID) {
        super(playerName, gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getGameStartedCommand(getPlayerName(), getGameId()));
        return result;
    }
}
