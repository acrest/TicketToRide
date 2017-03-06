package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

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
    public static String CURRENT_GAME = "current game";
    public static String PLAYER_IN_GAME = "player in game";
    public static String PLAYER_TRAIN_CARDS = "player train cards";
    public static String VISIBLE_TRAIN_CARDS = "visible train cards";
    public static String NUM_DESTINATION_CARDS = "num destination cards";
    public static String PLAYER_DESTINATION_CARDS = "player destination cards";
    public static String GAME_MAP = "game map";

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

//    public IClientGame getCurrentGame() {
//        return currentGame;
//    }

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
        notifyPropertyChanges(CURRENT_GAME);
    }

    public boolean hasCurrentGame() {
        return currentGame != null;
    }

    public int getGameID() {
        return currentGame.getID();
    }

    public String getGameName() {
        return currentGame.getName();
    }

    public int getGameMaxPlayers() {
        return currentGame.getMaxPlayers();
    }

    public void setPlayer(int index, Player player) {
        if(currentGame == null) return;
        currentGame.setPlayer(index, player);
        notifyPropertyChanges(PLAYER_IN_GAME);
    }

    public Player getPlayer(int index) {
        if(currentGame == null) return null;
        return currentGame.getPlayer(index);
    }

    public void addTrainCard(String playerName, TrainCard card) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.addTrainCard(card);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void setVisibleCard(int index, TrainCard card) {
        if(currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        bank.setVisibleCard(index, card);
        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
    }

    public void decNumOfDestinationCards() {
        if(currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        bank.decNumberOfDestinationCards();
        notifyPropertyChanges(NUM_DESTINATION_CARDS);
    }

    public void addDestinationCard(String playerName, DestinationCard card) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.addDestinationCard(card);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public int getNumberPlayers() {
        if(currentGame == null) return 0;
        return currentGame.getNumberPlayers();
    }

    public void setMap(GameMap map) {
        if(currentGame == null) return;
        currentGame.setMap(map);
        notifyPropertyChanges(GAME_MAP);
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
