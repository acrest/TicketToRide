package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Molly on 2/6/2017.
 */

public class ClientModel extends Observable {

    public static String GAME_LIST = "game list";
    public static String CREATE_GAME_SUCCESS = "create game success";
    public static String CREATE_GAME_FAILURE = "create game failure";
    public static String JOIN_GAME_SUCCESS = "join game success";
    public static String JOIN_GAME_FAILURE = "join game failure";
    public static String NUM_PLAYERS_IN_GAME = "num players in game";

    private static ClientModel instance = null;

    public static ClientModel getInstance() {
        if(instance == null)
            instance = new ClientModel();
        return instance;
    }

    private List<GameDescription> gameList;
    private GameState currentGame;
    private Player currentPlayer;

    public ClientModel() {
        currentGame = null;
        currentPlayer = null;
        gameList = new ArrayList<>();
    }

    public GameState getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(GameState currentGame) {
        this.currentGame = currentGame;
    }

    public void addPlayerToGame(Player player, String color) {
        currentGame.addPlayer(player);
        currentGame.setPlayerColor(player, color);
        notifyPropertyChanges(NUM_PLAYERS_IN_GAME);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addGameToList(GameDescription game) {
        gameList.add(game);
        notifyPropertyChanges(GAME_LIST);
    }

    public boolean hasGameInList(GameDescription game) {
        return gameList.contains(game);
    }

    public boolean hasGameInList(int gameID) {
        GameDescription dummyGame = new GameDescription(gameID, null, 0, null, null);
        return gameList.contains(dummyGame );
    }

    public void removeGameFromList(GameDescription game) {
        gameList.remove(game);
        notifyPropertyChanges(GAME_LIST);
    }

    public void removeGameFromList(int gameID) {
        GameDescription dummyGame = new GameDescription(gameID, null, 0, null, null);
        removeGameFromList(dummyGame);
    }

    public List<GameDescription> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDescription> gameList) {
        this.gameList = gameList;
        notifyPropertyChanges(GAME_LIST);
    }

    public void createGame(GameDescription gameDescription) {
        //TODO: implement this method

        if(gameDescription != null) {
            notifyPropertyChanges(CREATE_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(CREATE_GAME_FAILURE);
        }
    }

    public void joinGame(String gameName) {
        //TODO: implements this method

        if(gameName != null) {
            notifyPropertyChanges(JOIN_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(JOIN_GAME_FAILURE);
        }
    }

    private void notifyPropertyChanges(String... properties) {
        UpdateIndicator u = new UpdateIndicator();
        for(String property : properties) {
            u.addProperty(property);
        }
        setChanged();
        notifyObservers(u);
    }
}
