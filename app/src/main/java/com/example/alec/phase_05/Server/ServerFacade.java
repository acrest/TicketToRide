package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

/**
 * Created by Alec on 2/5/17.
 */
public class ServerFacade {



    private static ServerFacade _instance;

    ServerFacade() {

    }

    public static ServerFacade get_instance(){
        if(_instance == null){
            _instance = new ServerFacade();
        }
        return _instance;
    }

    public void addPlayerToGame(Player newPlayer, Game currentGame){

    }



}
