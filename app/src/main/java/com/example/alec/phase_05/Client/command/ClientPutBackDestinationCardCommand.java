package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.PutBackDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ClientPutBackDestinationCardCommand extends PutBackDestinationCardCommand {

    public ClientPutBackDestinationCardCommand(String userName, String password, int gameID, DestinationCard card) {
        super(userName, password, gameID, card);
    }

    @Override
    public Result execute() {
        return null;
    }
}
