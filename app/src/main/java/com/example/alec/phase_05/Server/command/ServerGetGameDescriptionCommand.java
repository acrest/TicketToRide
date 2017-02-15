package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.AbstractGetGameDescriptionCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameDescription;

/**
 * Created by samuel on 2/14/17.
 */

public class ServerGetGameDescriptionCommand extends AbstractGetGameDescriptionCommand {
    public ServerGetGameDescriptionCommand(String username, String password, int gameID) {
        super(username, password, gameID);
    }

    @Override
    public Result execute() {
        ServerFacade facade = ServerFacade.get_instance();
        GameDescription gameDescription = facade.getGameDescription(getUserName(), getPassword(), getGameID());
        Result result = new ServerResult();
        result.setResultObject(gameDescription);
        return result;
    }
}
