package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.command.AbstractGetGameListCommand;
import com.example.alec.phase_05.Shared.command.AuthorizedCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerGetGameListCommand extends AbstractGetGameListCommand {

    public ServerGetGameListCommand(String username, String password) {
        super(username, password);
    }

    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        List<Game> games = sf.getGames(getUserName(), getPassword());
        Result result = new ServerResult();
        result.setResultObject(games);
        return result;
    }
}
