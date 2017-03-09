package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
import com.example.alec.phase_05.Shared.command.PutBackDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ServerPutBackDestinationCardCommand extends PutBackDestinationCardCommand {

    public ServerPutBackDestinationCardCommand(String userName, String password, int gameID, DestinationCard card) {
        super(userName, password, gameID, card);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.get_instance().putBackDestinationCard(getUserName(), getGameID(), getCard()));
        return result;
    }
}
