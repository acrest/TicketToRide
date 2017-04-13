package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.SetServerTrainCountCommand;

/**
 * Created by samuel on 3/29/17.
 */

public class ServerSetServerTrainCountCommand extends SetServerTrainCountCommand implements Serializable {
    public ServerSetServerTrainCountCommand(int count) {
        super(count);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().setServerTrainCount(getCount()));
        return result;
    }
}
