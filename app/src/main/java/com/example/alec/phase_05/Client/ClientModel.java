package com.example.alec.phase_05.Client;

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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addGameToList(GameDescription game) {
        gameList.add(game);
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
    }

    public void removeGameFromList(int gameID) {
        GameDescription dummyGame = new GameDescription(gameID, null, 0, null, null);
        removeGameFromList(dummyGame);
    }

    public void setGameList(List<GameDescription> gameList) {
        this.gameList = gameList ;
    }
}
