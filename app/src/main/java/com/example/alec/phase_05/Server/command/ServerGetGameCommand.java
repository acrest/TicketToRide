package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.AbstractGetGameCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Game;

/**
 * Created by samuel on 2/14/17.
 */

public class ServerGetGameCommand extends AbstractGetGameCommand {
    /**
     * @param userName username of client
     * @param password password of client
     * @param gameID   id of the game for which this command operates
     */
    public ServerGetGameCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        ServerFacade facade = ServerFacade.get_instance();
        Game gameState = facade.getGame(getUserName(), getPassword(), getGameID());
        Result result = new ServerResult();
        result.setResultObject(gameState);
        return result;
    }
}
