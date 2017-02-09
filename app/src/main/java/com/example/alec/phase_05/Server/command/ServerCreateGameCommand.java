package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;
import com.example.alec.phase_05.Shared.command.*;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerCreateGameCommand extends AbstractCreateGameCommand
{
    public ServerCreateGameCommand(String username, String password, String gameName, int numberOfPlayers)
    {
        super(username, password, gameName, numberOfPlayers);
    }

    public Result execute()
    {
        ServerFacade sf = ServerFacade.get_instance();
        Player hostPlayer = sf.getPlayerByName(getUserName());
        Game game = sf.createGame(hostPlayer, getNumberOfPlayers(), getGameName());
        Result result = new ServerResult();
        result.setResultObject(game);
        return result;
    }
}
