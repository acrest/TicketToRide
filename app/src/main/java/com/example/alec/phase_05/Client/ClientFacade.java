package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.Model.ClientGameFactory;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by Molly on 2/6/2017.
 */

public class ClientFacade {

    private static ClientFacade instance;

    public static ClientFacade getInstance() {
        if(instance == null) instance = new ClientFacade();
        return instance;
    }

    public void updateGameList(List<GameDescription> games)
    {
        ClientModel.getInstance().setGameList(games);
    }

    public void createGame(GameDescription gameDescription) {

        if(gameDescription != null) {
            ClientModel.getInstance().setCreateGameSuccess(true);
            IClientGame game = ClientGameFactory.createGame(gameDescription);
            ClientModel.getInstance().setCurrentGame(game);
        } else {
            ClientModel.getInstance().setCreateGameSuccess(false);
        }

    }

    public void joinGame(GameDescription gameDescription) {
        if(gameDescription != null) {
            ClientModel.getInstance().setJoinGameSuccess(true);
            IClientGame game = ClientGameFactory.createGame(gameDescription);
            ClientModel.getInstance().setCurrentGame(game);
        } else {
            ClientModel.getInstance().setJoinGameSuccess(false);
        }
    }

    public void setCurrentPlayer(Player player) {
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    public void setCurrentGameDescription(GameDescription description) {
        ClientModel.getInstance().setCurrentGameDescription(description);
    }
}
