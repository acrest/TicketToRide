package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.PutBackDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;

/**
 * Created by samuel on 3/8/17.
 */

public class ClientPutBackDestinationCardCommand extends PutBackDestinationCardCommand {
    public ClientPutBackDestinationCardCommand(String playerName, int gameID, DestinationCard card) {
        super(playerName, gameID, card);
    }

    @Override
    public Result execute() {
        return null;
    }
}
