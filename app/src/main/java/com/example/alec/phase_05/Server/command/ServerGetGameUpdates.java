package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.Server;
import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.AbstractGetGameUpdatesCommand;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.CommandHolder;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by samuel on 2/13/17.
 */

public class ServerGetGameUpdates extends AbstractGetGameUpdatesCommand {

    public ServerGetGameUpdates(String username, String password, int gameID, int lastUpdate) {
        super(username, password, gameID, lastUpdate);
    }

    @Override
    public Result execute() {
        ServerFacade sf = ServerFacade.get_instance();
        List<BaseCommand> commands = sf.getGameUpdates(sf.getPlayerByName(getUserName()), getGameID());
        Result r = new ServerResult();
        r.setResultObject(new CommandHolder(commands));
        return r;
    }
}
