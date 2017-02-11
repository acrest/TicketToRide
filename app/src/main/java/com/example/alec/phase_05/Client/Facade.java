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

    public void login(String username, String password) {
        Player player = proxy.login(username, password);
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    public void registerUser(String username, String password) {
        Player player = proxy.registerUser(username, password);
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    public void createGame(int numOfPlayers, String gameName) {
        Player player = ClientModel.getInstance().getCurrentPlayer();
        GameDescription newGame = proxy.createGame(player, numOfPlayers, gameName);
    }

    public void joinGame(int gameID) {
        Player player = ClientModel.getInstance().getCurrentPlayer();
        String joinedGame = proxy.joinGame(player, gameID);
    }

    public void getGames(Player player) {
        List<GameDescription> gameList = proxy.getGames(player.getName(), player.getPassword());
        ClientFacade.getInstance().updateGameList(gameList);
    }

    public void getGame(Player player, int gameID) {
        GameState game = proxy.getGame(player.getName(), player.getPassword(), gameID);
    }
}
