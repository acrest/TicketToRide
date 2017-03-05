package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;

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
    private IClientGame currentGame;
    private Player currentPlayer;

    public ClientModel() {
        currentGame = null;
        currentPlayer = null;
        gameList = null;
    }

    public IClientGame getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(ClientGame currentGame) {
        this.currentGame = currentGame;
    }

//    public void addPlayerToGame(Player player, String color) {
//        player.setColor(color);
//        currentGame.addPlayerAtNextPosition(player);
//        notifyPropertyChanges(NUM_PLAYERS_IN_GAME);
//    }

//    public GameDescription getCurrentGameDescription() {
//        return currentGameDescription;
//    }

//    public void setCurrentGameDescription(GameDescription gameDescription) {
//        currentGameDescription = gameDescription;
//        notifyPropertyChanges(NUM_PLAYERS_IN_GAME);
//    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

//    public void addGameToList(GameDescription game) {
//        gameList.add(game);
//        notifyPropertyChanges(GAME_LIST);
//    }

//    public boolean hasGameInList(GameDescription game) {
//        return gameList.contains(game);
//    }

//    public boolean hasGameInList(int gameID) {
//        GameDescription dummyGame = new GameDescription(gameID, null, 0, null);
//        return gameList.contains(dummyGame );
//    }

//    public void removeGameFromList(GameDescription game) {
//        gameList.remove(game);
//        notifyPropertyChanges(GAME_LIST);
//    }

//    public void removeGameFromList(int gameID) {
//        GameDescription dummyGame = new GameDescription(gameID, null, 0, null);
//        removeGameFromList(dummyGame);
//    }

    public List<GameDescription> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDescription> gameList) {
        this.gameList = gameList;
        notifyPropertyChanges(GAME_LIST);
    }

    public void setCurrentGame(IClientGame currentGame) {
        this.currentGame = currentGame;
    }

    public void setCreateGameSuccess(boolean success) {
        if(success) {
            notifyPropertyChanges(CREATE_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(CREATE_GAME_FAILURE);
        }
    }
    public void setJoinGameSuccess(boolean success) {
        if(success) {
            notifyPropertyChanges(JOIN_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(JOIN_GAME_FAILURE);
        }
    }

//    public void joinGame(GameDescription gameDescription) {
//        if(gameDescription != null) {
//            currentGame = GameComponentFactory.createGame(gameDescription.getID(), gameDescription.getName(), gameDescription.getMaxPlayers());
//            currentGame.setPlayers(gameDescription.getPlayers());
//            notifyPropertyChanges(JOIN_GAME_SUCCESS);
//        } else {
//            notifyPropertyChanges(JOIN_GAME_FAILURE);
//        }
//    }

    private void notifyPropertyChanges(String... properties) {
        UpdateIndicator u = new UpdateIndicator();
        for(String property : properties) {
            u.addProperty(property);
        }
        setChanged();
        notifyObservers(u);
    }
}
