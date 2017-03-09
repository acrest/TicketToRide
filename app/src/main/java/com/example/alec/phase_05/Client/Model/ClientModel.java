package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

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
    public static String CURRENT_GAME = "current game";
    public static String PLAYER_IN_GAME = "player in game";
    public static String PLAYER_TRAIN_CARDS = "player train cards";
    public static String VISIBLE_TRAIN_CARDS = "visible train cards";
    public static String NUM_DESTINATION_CARDS = "num destination cards";
    public static String PLAYER_DESTINATION_CARDS = "player destination cards";
    public static String GAME_MAP = "game map";
    public static String CHAT = "chat";
    public static String PLAYER_POINTS = "player points";
    public static String PLAYER_TRAIN_COUNT = "player train count";
    public static String GAME_START = "game start";

    private static ClientModel instance = null;

    public static ClientModel getInstance() {
        if(instance == null)
            instance = new ClientModel();
        return instance;
    }

    private List<GameDescription> gameList;
    private List<Chat> chats;
    private IClientGame currentGame;
    private Player currentPlayer;

    public ClientModel() {
        currentGame = null;
        currentPlayer = null;
        gameList = null;
        chats = new ArrayList<>();
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
        currentPlayer = currentGame.findPlayerByName(currentPlayer.getName());
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
//        if(currentPlayer.getName().equals(player.getName())) {
//            currentPlayer = player;
//        }
        notifyPropertyChanges(PLAYER_IN_GAME, NUM_PLAYERS_IN_GAME);
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

    public void addTrainCard(TrainCard card) {
        if(currentPlayer == null) return;
        currentPlayer.addTrainCard(card);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void setVisibleCard(int index, TrainCard card) {
        if(currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        bank.setVisibleCard(index, card);
        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
    }

    public void setVisibleCards(List<TrainCard> cards) {
        if(currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        for(int i = 0; i < cards.size(); ++i) {
            bank.setVisibleCard(i, cards.get(i));
        }
        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
    }

    public TrainCard getVisibleTrainCard(int index) {
        if(currentGame == null) return null;
        IClientBank bank = (IClientBank) currentGame.getBank();
        return bank.getVisibleCard(index);
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

    public void addDestinationCard(DestinationCard card) {
        if(currentPlayer == null) return;
        currentPlayer.addDestinationCard(card);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void setTrainCount(String playerName, int count) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.setTrainCount(count);
        notifyPropertyChanges(PLAYER_TRAIN_COUNT);
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

    public void addChat(Chat chat) {
        chats.add(chat);
        notifyPropertyChanges(CHAT);
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void addPlayerPoints(String playerName, int points) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.addPoints(points);
        notifyPropertyChanges(PLAYER_POINTS);
    }

    public int getPlayerPoints(String playerName) {
        if(currentGame == null) return 0;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return 0;
        return player.getPointCount();
    }

    public void setRouteOwner(String playerName, int routeId) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        currentGame.getMap().getRoutes().get(routeId).setOwner(player);
        notifyPropertyChanges(GAME_MAP);
    }

    public GameMap getGameMap() {
        if(currentGame == null) return null;
        return currentGame.getMap();
    }

    public int getNumberOfDestinationCards(String playerName) {
        if(currentGame == null) return 0;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return 0;
        return player.getDestinationCards().size(); //TODO: not sure if this is a good idea (requires seeing other player's destination cards
    }

    public int getNumberOfTrainCards(String playerName) {
        if(currentGame == null) return 0;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return 0;
        return player.getTrainCards().size(); //TODO: not sure if this is a good idea (requires seeing other player's train cards
    }

    public void setGameStarted() {
        System.out.println("setGameStarted called in ClientModel");
        if(currentGame == null) return;
        currentGame.setGameStarted();
        notifyPropertyChanges(GAME_START);
    }

    public void removeTrainCard(int index) {
        if(currentPlayer == null) return;
        currentPlayer.getTrainCards().remove(index);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void removeTrainCard(String playerName, int index) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.getTrainCards().remove(index);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }
    public void removeDestinationCard(int index) {
        if(currentPlayer == null) return;
        currentPlayer.getDestinationCards().remove(index);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void removeDestinationCard(String playerName, int index) {
        if(currentGame == null) return;
        Player player = currentGame.findPlayerByName(playerName);
        if(player == null) return;
        player.getDestinationCards().remove(index);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void setCreateGameSuccess(boolean success) {
        if(success) {
            currentPlayer.setHost(true);
            notifyPropertyChanges(CREATE_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(CREATE_GAME_FAILURE);
        }
    }
    public void setJoinGameSuccess(boolean success) {
        if(success) {
            currentPlayer.setHost(false);
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
