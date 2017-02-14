package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.command.AbstractGetGameListCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerGetGameListCommand extends AbstractGetGameListCommand {
    public ServerGetGameListCommand(String username, String password) {
        super(username, password);
    }

    @Override
    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        List<GameDescription> games = sf.getGames(getUserName(), getPassword());
        Result result = new ServerResult();
        result.setResultObject(games);
        return result;
    }
}
