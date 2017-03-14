package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.command.GetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerGetGameListCommand extends GetGameListCommand {
    public ServerGetGameListCommand() {}

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(new GameDescriptionHolder(ServerFacade.getInstance().getGames()));
        return result;
    }
}
