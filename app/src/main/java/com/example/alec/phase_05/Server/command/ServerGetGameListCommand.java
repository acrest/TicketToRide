package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.command.GetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerGetGameListCommand extends GetGameListCommand implements Serializable {

    public ServerGetGameListCommand(String name) {
        super(name);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(new GameDescriptionHolder(ServerFacade.getInstance().getGames(getPlayerName())));
        return result;
    }
}
