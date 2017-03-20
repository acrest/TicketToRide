package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.CreateGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerCreateGameCommand extends CreateGameCommand {
    /**
     * @param gameName        name of game to be created
     * @param numberOfPlayers number of players in game to be created
     * @param hostName
     * @param hostColor
     */
    public ServerCreateGameCommand(String gameName, int numberOfPlayers, String hostName, String hostColor) {
        super(gameName, numberOfPlayers, hostName, hostColor);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().createGame(getHostName(), getNumberOfPlayers(), getGameName(), getHostColor()));
        return result;
    }
}
