package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.model.GameDescription;

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

}
