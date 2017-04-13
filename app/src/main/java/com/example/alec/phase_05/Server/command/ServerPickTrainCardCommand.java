package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.PickTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;

/**
 * Created by samuel on 3/27/17.
 */

public class ServerPickTrainCardCommand extends PickTrainCardCommand {


    public ServerPickTrainCardCommand(String playerName, int gameID, int cardIndex) {
        super(playerName, gameID, cardIndex);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().pickTrainCard(getPlayerName(), getGameId(), getCardIndex()));
        return result;
    }

    @Override
    public void reExecute(){
        ServerFacade.getInstance().pickTrainCard(getPlayerName(), getGameId(), getCardIndex());
    }


}
