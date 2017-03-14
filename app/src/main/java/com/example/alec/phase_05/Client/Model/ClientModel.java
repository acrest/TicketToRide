package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Molly on 2/6/2017.
 */

public class ClientModel extends Observable {

    public static String LOGIN_SUCCESS = "login success";
    public static String LOGIN_FAILURE = "login failure";
    public static String REGISTER_SUCCESS = "register success";
    public static String REGISTER_FAILURE = "register failure";
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
        if (instance == null)
            instance = new ClientModel();
        return instance;
    }

    private List<GameDescription> gameList;
    private List<Chat> chats;
    private IClientGame currentGame;
    private String currentPlayerName;
    private boolean isHost;

    public ClientModel() {
        currentGame = null;
        currentPlayerName = null;
        gameList = null;
        chats = new ArrayList<>();
        isHost = false;
    }

    public IPlayer getCurrentPlayer() {
        if (currentGame == null) return null;
        return currentGame.findPlayerByName(currentPlayerName);
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

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

    public IClientGame getGame() {
        return currentGame;
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

    public void setPlayer(int index, IPlayer player) {
        if (currentGame == null) return;
        currentGame.setPlayer(index, player);
        notifyPropertyChanges(PLAYER_IN_GAME, NUM_PLAYERS_IN_GAME);
    }

    public IPlayer getPlayer(int index) {
        if (currentGame == null) return null;
        return currentGame.getPlayer(index);
    }

    public IPlayer findPlayerByName(String playerName) {
        if (currentGame == null) return null;
        return currentGame.findPlayerByName(playerName);
    }

    public void addTrainCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setTrainCardCount(otherPlayer.getTrainCardCount() + 1);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void addTrainCard(TrainCard card) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        ((Player) currentPlayer).addTrainCard(card);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void setVisibleCard(int index, TrainCard card) {
        if (currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        bank.setVisibleCard(index, card);
        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
    }

    public TrainCard getVisibleTrainCard(int index) {
        if (currentGame == null) return null;
        IClientBank bank = (IClientBank) currentGame.getBank();
        return bank.getVisibleCard(index);
    }

//    public void setVisibleCards(List<TrainCard> cards) {
//        if (currentGame == null) return;
//        IClientBank bank = (IClientBank) currentGame.getBank();
//        for (int i = 0; i < cards.size(); ++i) {
//            bank.setVisibleCard(i, cards.get(i));
//        }
//        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
//    }

    public void decNumOfDestinationCards() {
        if (currentGame == null) return;
        IClientBank bank = (IClientBank) currentGame.getBank();
        bank.decNumberOfDestinationCards();
        notifyPropertyChanges(NUM_DESTINATION_CARDS);
    }

    public void addDestinationCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setDestinationCardCount(otherPlayer.getDestinationCardCount() + 1);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void addDestinationCard(DestinationCard card) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        ((Player) currentPlayer).addDestinationCard(card);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public List<DestinationCard> getDestinationCards() {
        IPlayer player = getCurrentPlayer();
        if (player == null || !(player instanceof Player)) return null;
        return ((Player) player).getDestinationCards();
    }

    public void setTrainCount(int count) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;
        currentPlayer.setTrainCount(count);
    }

    public void setTrainCount(String playerName, int count) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return;
        player.setTrainCount(count);
        notifyPropertyChanges(PLAYER_TRAIN_COUNT);
    }

    public int getNumberPlayers() {
        if (currentGame == null) return 0;
        return currentGame.getNumberPlayers();
    }

    public void setMap(GameMap map) {
        if (currentGame == null) return;
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

//    public void addPlayerPoints(int points) {
//        if (currentGame == null) return;
//        getCurrentPlayer().addPoints(points);
//    }

//    public void addPlayerPoints(String playerName, int points) {
//        if (currentGame == null) return;
//        OtherPlayer player = currentGame.findPlayerByName(playerName);
//        if (player == null) return;
//        player.setPoints(player.getPoints() + points);
//        notifyPropertyChanges(PLAYER_POINTS);
//    }

    public void setPlayerPoints(int points) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;
        currentPlayer.setPoints(points);
    }

    public void setPlayerPoints(String playerName, int points) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return;
        player.setPoints(points);
        notifyPropertyChanges(PLAYER_POINTS);
    }

    public int getPlayerPoints() {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return 0;
        return currentPlayer.getPoints();
    }

    public int getPlayerPoints(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return 0;
        return player.getPoints();
    }

    public void setRouteOwner(String playerName, int routeId) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return;
        currentGame.getMap().getRoutes().get(routeId).setOwner(player);
        notifyPropertyChanges(GAME_MAP);
    }

    public GameMap getGameMap() {
        if (currentGame == null) return null;
        return currentGame.getMap();
    }

    public int getDestinationCardCount(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return 0;
        return player.getDestinationCardCount();
    }

    public int getTrainCardCount(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null) return 0;
        return player.getTrainCardCount();
    }

    public void setGameStarted() {
        if (currentGame == null) return;
        currentGame.setGameStarted();
        notifyPropertyChanges(GAME_START);
    }

    public void removeTrainCard(int index) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        ((Player) currentPlayer).removeTrainCard(index);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public List<TrainCard> getTrainCards() {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return null;
        return ((Player) currentPlayer).getTrainCards();
    }

    public void removeTrainCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setTrainCardCount(otherPlayer.getTrainCardCount() - 1);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void removeDestinationCard(int index) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        ((Player) currentPlayer).removeDestinationCard(index);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void removeDestinationCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.findPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setDestinationCardCount(otherPlayer.getDestinationCardCount() - 1);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void setCreateGameSuccess(boolean success) {
        if (success) {
            isHost = true;
            notifyPropertyChanges(CREATE_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(CREATE_GAME_FAILURE);
        }
    }

    public void setJoinGameSuccess(boolean success) {
        if (success) {
            isHost = false;
            notifyPropertyChanges(JOIN_GAME_SUCCESS);
        } else {
            notifyPropertyChanges(JOIN_GAME_FAILURE);
        }
    }

    public boolean isHost() {
        return isHost;
    }

    public void setLoginSuccess(boolean success) {
        if (success) {
            notifyPropertyChanges(LOGIN_SUCCESS);
        } else {
            notifyPropertyChanges(LOGIN_FAILURE);
        }
    }

    public void setRegisterSuccess(boolean success) {
        if (success) {
            notifyPropertyChanges(REGISTER_SUCCESS);
        } else {
            notifyPropertyChanges(REGISTER_FAILURE);
        }
    }

    public void setHost(boolean host) {
        this.isHost = host;
    }

    private void notifyPropertyChanges(String... properties) {
        UpdateIndicator u = new UpdateIndicator();
        for (String property : properties) {
            u.addProperty(property);
        }
        setChanged();
        notifyObservers(u);
    }
}
