package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerDrawDestinationCardCommand extends DrawDestinationCardCommand {

    public ServerDrawDestinationCardCommand(String userName, String password, int gameID) {
        super(userName, password, gameID);
    }

    @Override
    public Result execute() {
        ServerFacade facade = ServerFacade.get_instance();
        DestinationCard card = facade.drawDestinationCard(getUserName(), getPassword(), getGameID());
        Result result = new ServerResult();
        result.setResultObject(card);
        return result;
    }
}
