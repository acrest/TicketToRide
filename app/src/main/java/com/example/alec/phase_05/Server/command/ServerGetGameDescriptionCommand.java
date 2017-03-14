package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameDescriptionCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameDescription;

/**
 * Created by samuel on 2/14/17.
 */

public class ServerGetGameDescriptionCommand extends GetGameDescriptionCommand {
    public ServerGetGameDescriptionCommand(int gameID) {
        super(gameID);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().getGameDescription(getGameId()));
        return result;
    }
}
