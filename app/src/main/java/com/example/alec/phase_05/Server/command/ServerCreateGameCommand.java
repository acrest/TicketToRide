package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.*;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerCreateGameCommand extends AbstractCreateGameCommand
{
    public ServerCreateGameCommand(String username, String password, int gameID)
    {
        super(username, password, gameID);
    }

    public Result execute()
    {
        ServerFacade.get_instance();
    }
}
