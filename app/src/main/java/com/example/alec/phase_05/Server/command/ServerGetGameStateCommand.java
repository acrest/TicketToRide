package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.GetGameStateCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameState;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerGetGameStateCommand extends GetGameStateCommand {

    public ServerGetGameStateCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        ServerFacade facade = ServerFacade.get_instance();
        GameState state = facade.getGameState(getUserName(), getPassword(), getGameID());
        Result result = new ServerResult();
        result.setResultObject(state);
        return result;
    }
}
