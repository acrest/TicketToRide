package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.JoinGameCommand;
import com.example.alec.phase_05.Shared.command.RejoinGameCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by Alec on 4/10/17.
 */

public class ClientReJoinGameCommand extends RejoinGameCommand {
    public ClientReJoinGameCommand(String playerName, int gameId) {
        super(playerName, gameId);
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
