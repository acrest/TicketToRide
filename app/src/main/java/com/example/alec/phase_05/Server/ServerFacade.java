package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

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

    public boolean login(String username, String password) {
        return false;
    }

    public boolean poller() {
        return false;
    }



}
