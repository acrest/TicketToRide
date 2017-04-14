package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.JoinGameCommand;
import com.example.alec.phase_05.Shared.command.RejoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by Alec on 4/10/17.
 */

public class ServerReJoinGameCommand extends RejoinGameCommand {
    public ServerReJoinGameCommand(String playerName, int gameId) {
        super(playerName, gameId);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().reJoinGame(getPlayerName(), getGameId()));
        return result;
    }
}
