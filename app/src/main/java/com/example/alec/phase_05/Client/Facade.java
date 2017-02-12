package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private static Facade _instance;
    private ServerProxy proxy = null;


    public Facade() {
        proxy = new ServerProxy(null, null);
    }

    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    public void login(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = proxy.login(username, password);
                ClientModel.getInstance().setCurrentPlayer(player);
            }
        }).start();
    }

    public void registerUser(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = proxy.registerUser(username, password);
                ClientModel.getInstance().setCurrentPlayer(player);
            }
        }).start();
    }

    public void createGame(final int numOfPlayers, final String gameName, final String hostColor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                GameDescription newGame = proxy.createGame(player, numOfPlayers, gameName, hostColor);
            }
        }).start();
    }

    public void joinGame(final int gameID, final String color) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                String joinedGame = proxy.joinGame(player, gameID, color);
            }
        }).start();
    }

    public void getGames(final Player player) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<GameDescription> gameList = proxy.getGames(player.getName(), player.getPassword());
                ClientFacade.getInstance().updateGameList(gameList);
            }
        }).start();
    }

    public void getGame(final Player player, final int gameID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameState game = proxy.getGame(player.getName(), player.getPassword(), gameID);
            }
        }).start();
    }
}
