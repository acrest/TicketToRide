package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.JoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerJoinGameCommand  extends JoinGameCommand {
    public ServerJoinGameCommand(String playerName, int gameID, String color) {
        super(playerName, gameID, color);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().joinGame(getPlayerName(), getGameId(), getColor()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().joinGame(getPlayerName(), getGameId(), getColor());
    }
}
