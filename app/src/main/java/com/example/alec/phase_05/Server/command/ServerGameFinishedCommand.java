package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.GameFinishedCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.Map;

/**
 * Created by samuel on 3/27/17.
 */

public class ServerGameFinishedCommand extends GameFinishedCommand {
    public ServerGameFinishedCommand(Map<String, Integer> bonusPoints) {
        super(bonusPoints);
    }

    @Override
    public Result execute() {
        return null;
    }
}
