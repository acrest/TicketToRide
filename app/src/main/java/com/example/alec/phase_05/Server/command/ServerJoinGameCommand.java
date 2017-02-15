package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.command.AbstractJoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerJoinGameCommand  extends AbstractJoinGameCommand {

    public ServerJoinGameCommand(String username, String password, int gameID, String color) {
        super(username, password, gameID, color);
    }

    @Override
    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        Player player = sf.getPlayerByName(getUserName());
        GameDescription game = sf.joinGame(player, getGameID(), getColor());
        //String gameName = sf.joinGame(player, getGameID(), getColor());
        Result result = new ServerResult();
        result.setResultObject(game);
        return result;
    }
}
