package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.SetServerTrainCountCommand;

/**
 * Created by samuel on 3/29/17.
 */

public class ClientSetServerTrainCountCommand extends SetServerTrainCountCommand {
    public ClientSetServerTrainCountCommand(int count) {
        super(count);
    }

    @Override
    public Result execute() {
        return null;
    }
}
