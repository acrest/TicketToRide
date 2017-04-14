package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.JoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public class ClientJoinGameCommand extends JoinGameCommand {
    public ClientJoinGameCommand(String playerName, int gameID, String color) {
        super(playerName, gameID, color);
    }

    /**
     * Does nothing.
     * This method should never be called.
     * It is only here to make the class non-abstract.
     * @return null
     */
    @Override
    public Result execute()
    {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
