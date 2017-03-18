package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.GameStartedCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameInfo;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientGameStartedCommand extends GameStartedCommand {
    public ClientGameStartedCommand(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public Result execute() {
        ClientFacade facade = ClientFacade.getInstance();
        facade.setGameInfo(getGameInfo());
        facade.startGame();
        return null;
    }
}
