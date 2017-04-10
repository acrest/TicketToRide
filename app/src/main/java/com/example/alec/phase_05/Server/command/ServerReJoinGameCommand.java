package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.JoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by Alec on 4/10/17.
 */

public class ServerReJoinGameCommand extends JoinGameCommand {
    public ServerReJoinGameCommand(String playerName, int gameID, String color) {
        super(playerName, gameID, color);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().reJoinGame(getPlayerName(), getGameId(), getColor()));
        return result;
    }
}
