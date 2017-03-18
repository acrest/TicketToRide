package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.GameStartedCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameInfo;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerGameStartedCommand extends GameStartedCommand {
    public ServerGameStartedCommand(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public Result execute() {
        return null;
    }
}
