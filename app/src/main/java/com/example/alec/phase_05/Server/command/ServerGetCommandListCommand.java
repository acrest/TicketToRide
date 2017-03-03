package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.CommandHolder;
import com.example.alec.phase_05.Shared.command.GetCommandListCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/2/17.
 */

public class ServerGetCommandListCommand extends GetCommandListCommand {

    public ServerGetCommandListCommand(String commandName, String userName, String password, int gameID) {
        super(commandName, userName, password, gameID);
    }

    @Override
    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        CommandHolder commands = sf.getCommandsForClient(getUserName(), getPassword(), getGameID());
        Result result = new ServerResult();
        result.setResultObject(commands);
        return result;
    }
}
