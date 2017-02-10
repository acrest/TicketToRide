package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.command.AbstractJoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerJoinGameCommand  extends AbstractJoinGameCommand {

    public ServerJoinGameCommand(String username, String password, int gameID) {
        super(username, password, gameID);
    }

    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        Player player = sf.getPlayerByName(getUserName());
        String gameName = sf.joinGame(player, getGameID());
        Result result = new ServerResult();
        result.setResultObject(gameName);
        return result;
    }
}
