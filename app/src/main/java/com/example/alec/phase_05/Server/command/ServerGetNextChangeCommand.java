package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetNextChangeCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/13/17.
 */

public class ServerGetNextChangeCommand extends GetNextChangeCommand implements Serializable {
    public ServerGetNextChangeCommand(String playerName, int gameId) {
        super(playerName, gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getNextCommand(getPlayerName(), getGameId()));
        return result;
    }
}
