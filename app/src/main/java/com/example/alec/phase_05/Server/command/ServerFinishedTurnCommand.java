package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.FinishedTurnCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerFinishedTurnCommand extends FinishedTurnCommand {
    public ServerFinishedTurnCommand(String playerName) {
        super(playerName);
    }

    @Override
    public Result execute() {
        return null;
    }
}
