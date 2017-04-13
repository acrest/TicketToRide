package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Shared.command.FinishedTurnCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/16/17.
 */

public class ServerFinishedTurnCommand extends FinishedTurnCommand implements Serializable {
    public ServerFinishedTurnCommand(String playerName) {
        super(playerName);
    }

    @Override
    public Result execute() {
        return null;
    }
}
