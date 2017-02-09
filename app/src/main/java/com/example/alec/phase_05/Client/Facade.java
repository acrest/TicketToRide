package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;
import com.example.alec.phase_05.Shared.command.AbstractLoginCommand;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private Game game;
    private Player player;
    private static Facade _instance;
    ServerProxy proxy = null;

    public Facade() {

        this.game = new Game();
        proxy = new ServerProxy(null, null);
    }

    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    private void login(String username, String password) {
        boolean loginSuccess = proxy.login(username, password);
    }

    private void registerUser(String username, String password) {
        boolean registerSuccess = proxy.registerUser(username, password);

    }

    private void createGame(Player hostPlayer, int numOfPlayers, String gameName) {
        player = hostPlayer;
        Game newGame = proxy.createGame(hostPlayer, numOfPlayers, gameName);


    }

    private void joinGame(Player newPlayer, int gameID) {
        player = newPlayer;
        String joinedGame = proxy.joinGame(newPlayer, gameID);
    }

    private void getGames(Player player) {
        List<Game> gameList = proxy.getGames(player.getName(), player.getPassword());

    }
}
