package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.GameStartedCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.GameState;

/**
 * Created by samuel on 3/2/17.
 */

public class ClientGameStartedCommand extends GameStartedCommand {
    public ClientGameStartedCommand(GameState gameState) {
        super(gameState);
    }

    @Override
    public Result execute() {
        ClientFacade facade = ClientFacade.getInstance();
        facade.setGameState(getGameState());
        //TODO: draw initial cards
        return null;
    }
}
