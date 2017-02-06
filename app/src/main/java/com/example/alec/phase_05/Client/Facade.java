package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private Game game;
    private static Facade _instance;

    public Facade() {

        this.game = new Game();
    }

    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    private void login(String username, String password) {

    }

    private void registerUser(String username, String password) {


    }

    private void createGame(Player hostPlayer, int numOfPlayers, String gameName) {


    }

    private void joinGame(Player newPlayer, int gameID) {

    }

    private void getGames() {

    }
}
