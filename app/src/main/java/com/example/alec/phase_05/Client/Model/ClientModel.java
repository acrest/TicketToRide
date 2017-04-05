package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Presenter.UpdateIndicator;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public static String NUM_TRAIN_CARDS = "num train cards";
    public static String PLAYER_DESTINATION_CARDS = "player destination cards";
    public static String GAME_MAP = "game map";
    public static String CHAT = "chat";
    public static String PLAYER_POINTS = "player points";
    public static String PLAYER_TRAIN_COUNT = "player train count";
    public static String GAME_START = "game start";
    public static String DISPLAY_HAND = "display hand";
    public static String INIT_DISPLAY_HAND = "init display hand";
    public static String PLAYER_TURN_START = "player turn start";
    public static String GAME_FINISHED = "game finished";
    public static String GAME_FINISHED_REQUEST = "game finished request";
    public static String GAME_STATE = "game state";
    public static String ROUTE = "route";
    public static String BONUS_POINTS = "bonus points";

    public int longestRoad;
    public Player playerWithLongestRoute;

    private static ClientModel instance = null;

    public static ClientModel getInstance() {
        if (instance == null)
            instance = new ClientModel();
        return instance;
    }

    private List<GameDescription> gameList;
    private List<Chat> chats;
    private List<DestinationCard> cardChoices;
    private IClientGame currentGame;
    private String currentPlayerName;
    private boolean isHost;
    private Map<IPlayer, Integer> longestPath;
    private Map<String, Integer> bonusPoints;
    private boolean firstCardDraw;
    private boolean isLastTurns;
    private int expectedCardsInHand;
    TrainType RouteAnyCardType;

    public ClientModel() {
        currentGame = null;
        currentPlayerName = null;
        gameList = null;
        chats = new ArrayList<>();
        cardChoices = new ArrayList<>();
        isHost = false;
        firstCardDraw = true;
        isLastTurns = false;
        bonusPoints = new HashMap<>();
        expectedCardsInHand = 3;
    }

    public IPlayer getCurrentPlayer() {
        if (currentGame == null) return null;
        return currentGame.getPlayerByName(currentPlayerName);
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
        if (currentGame == null) return 0;
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

    public IPlayer getPlayerByName(String playerName) {
        if (currentGame == null) return null;
        return currentGame.getPlayerByName(playerName);
    }

    public void addTrainCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        System.out.println("addTrainCard with player: " + player.getName());
        otherPlayer.setTrainCardCount(otherPlayer.getTrainCardCount() + 1);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void addTrainCard(TrainCard card) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        System.out.println("addTrainCard with currentPlayer: " + currentPlayer.getName());
        ((Player) currentPlayer).addTrainCard(card);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void setPlayerTrainCardCount(String playerName, int count) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setTrainCardCount(count);
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public void setVisibleCard(int index, TrainCard card) {
        if (currentGame == null) return;
        currentGame.setVisibleCard(index, card);
        notifyPropertyChanges(VISIBLE_TRAIN_CARDS);
    }

    public TrainCard getVisibleTrainCard(int index) {
        if (currentGame == null) return null;
        return currentGame.getVisibleCard(index);
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
        currentGame.decNumberOfDestinationCards();
        notifyPropertyChanges(NUM_DESTINATION_CARDS);
    }

    public void incNumOfDestinationCards() {
        if (currentGame == null) return;
        currentGame.incNumberOfDestinationCards();
        notifyPropertyChanges(NUM_DESTINATION_CARDS);
    }

    public void decNumOfTrainCards() {
        if (currentGame == null) return;
        currentGame.decNumberOfTrainCards();
        notifyPropertyChanges(NUM_TRAIN_CARDS);
    }

    public void setNumberOfTrainCards(int num) {
        if (currentGame == null) return;
        currentGame.setNumberOfTrainCards(num);
    }

    public void setNumberOfDestinationCards(int num) {
        if (currentGame == null) return;
        currentGame.setNumberOfDestinationCards(num);
    }

    public int getNumberOfTrainCards() {
        if(currentGame == null) return 0;
        return currentGame.getNumberOfTrainCards();
    }

    public int getNumberOfDestinationCards() {
        if(currentGame == null) return 0;
        return currentGame.getNumberOfDestinationCards();
    }

    public void addDestinationCard(String playerName) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        System.out.println("addDestinationCard with player: " + player.getName());
        otherPlayer.setDestinationCardCount(otherPlayer.getDestinationCardCount() + 1);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public void addDestinationCard(DestinationCard card) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null || !(currentPlayer instanceof Player)) return;
        System.out.println("addDestinationCard with currentPlayer: " + currentPlayer.getName());
        ((Player) currentPlayer).addDestinationCard(card);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    public List<DestinationCard> getDestinationCards() {
        IPlayer player = getCurrentPlayer();
        if (player == null || !(player instanceof Player)) return null;
        return ((Player) player).getDestinationCards();
    }

    public int getTrainCount() {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return 0;
        return currentPlayer.getTrainCount();
    }

    public int getTrainCount(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return 0;
        return player.getTrainCount();
    }

    public void setTrainCount(int count) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;
        currentPlayer.setTrainCount(count);
        notifyPropertyChanges(PLAYER_TRAIN_COUNT);
    }

    public void setTrainCount(String playerName, int count) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return;
        player.setTrainCount(count);
        notifyPropertyChanges(PLAYER_TRAIN_COUNT);
    }

    public int getNumberPlayers() {
        if (currentGame == null) return 0;
        return currentGame.getNumberPlayers();
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
//        OtherPlayer player = currentGame.getPlayerByName(playerName);
//        if (player == null) return;
//        player.setPoints(player.getPoints() + points);
//        notifyPropertyChanges(PLAYER_POINTS);
//    }


    public void setPlayerPoints(int points) {
        IPlayer currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) return;
        currentPlayer.setPoints(points);
        System.out.println("player is "+currentPlayer.getName());
        System.out.println("new points are "+currentPlayer.getPoints());
        notifyPropertyChanges(PLAYER_POINTS);
    }

    public void setPlayerPoints(String playerName, int points) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
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
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return 0;
        return player.getPoints();
    }

    public void setRouteOwner(String playerName, int routeId) {
        if (currentGame == null) return;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return;
        currentGame.getRouteByID(routeId).setOwner(player);
        setLongestPath();
        notifyPropertyChanges(GAME_MAP);
    }

    public int getDestinationCardCount(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return 0;

        return player.getDestinationCardCount();
    }

    public int getTrainCardCount(String playerName) {
        if (currentGame == null) return 0;
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null) return 0;
        return player.getTrainCardCount();
    }

    public void setGameStarted() {
        if (currentGame == null) return;
        currentGame.setGameStarted();
        notifyPropertyChanges(GAME_START);
    }

    public boolean isGameStarted() {
        if (currentGame == null) return false;
        return currentGame.isGameStarted();
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
        IPlayer player = currentGame.getPlayerByName(playerName);
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
        IPlayer player = currentGame.getPlayerByName(playerName);
        if (player == null || !(player instanceof OtherPlayer)) return;
        OtherPlayer otherPlayer = (OtherPlayer) player;
        otherPlayer.setDestinationCardCount(otherPlayer.getDestinationCardCount() - 1);
        notifyPropertyChanges(PLAYER_DESTINATION_CARDS);
    }

    //bonus points could be negative. it accounts for destination cards met and not met
    public int getBonusPoints(String playerName) {
        if (bonusPoints.containsKey(playerName)) {
            return bonusPoints.get(playerName);
        } else {
            return 0;
        }
    }

    public void setBonusPoints(String playerName, int bonusPoints) {
        this.bonusPoints.put(playerName, bonusPoints);
        notifyPropertyChanges(BONUS_POINTS);
    }

    public void endTurn(String playerName) {
        if (currentGame == null) return;
        currentGame.endTurn(playerName);
        if (currentGame.isGameFinished()) {
            notifyPropertyChanges(GAME_FINISHED_REQUEST);
        } else if (currentGame.getCurrentPlayerTurn().equals(currentPlayerName)) {
            //just switched to current player's turn
            notifyPropertyChanges(PLAYER_TURN_START);
        }
    }

    public void notifyFinishGame() {
        notifyPropertyChanges(GAME_FINISHED);
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

    public GameMap getMap() {
        if (currentGame == null) return null;
        return currentGame.getMap();
    }

    public void setMap(GameMap map) {
        if (currentGame == null) return;
        currentGame.setMap(map);
    }

    public void setHost(boolean host) {
        this.isHost = host;
    }

    public List<DestinationCard> getCardChoices() {
        return cardChoices;
    }

    public void addCardToChoices(DestinationCard card) {
        cardChoices.add(card);
        if(cardChoices.size() == expectedCardsInHand) {
            displayHand();
        }
    }

    // Tells the model how many cards to get before showing the hand.
    // This will usually be 3, but it could be less.
    public void setExpectedHandSize(int cards) {
        expectedCardsInHand = cards;
    }

    // Tells the presenter to tell the GUI to display card choices.
    private void displayHand() {
        if (firstCardDraw) {
            //there is a race condition, and this is here to avoid it
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            notifyPropertyChanges(INIT_DISPLAY_HAND);
            firstCardDraw = false;
        } else {
            notifyPropertyChanges(DISPLAY_HAND);
        }
    }

    public void updateTrainCardDisplay() {
        notifyPropertyChanges(PLAYER_TRAIN_CARDS);
    }

    public GameState getGameState() {
        if (currentGame == null) return null;
        return currentGame.getTurnState();
    }

    public void notifyGameStateChange() {
        if (currentGame == null) return;
        notifyPropertyChanges(GAME_STATE);
    }

    public void clearCardChoices() {
        cardChoices.clear();
    }

    public void doDrawTrainCardFromDeck() throws StateWarning {
        if (currentGame == null) return;
        currentGame.doDrawTrainCardFromDeck();
    }

    public void doPickTrainCard(int cardIndex) throws StateWarning {
        if (currentGame == null) return;
        currentGame.doPickTrainCard(cardIndex);
    }

    public void doDrawDestinationCard() throws StateWarning {
        if (currentGame == null) return;
        currentGame.doDrawDestinationCard();
    }

    public void doPutBackDestinationCard(DestinationCard card) throws StateWarning {
        if (currentGame == null) return;
        currentGame.doPutBackDestinationCard(card);
    }

    public void doClaimRoute(int routeId) throws StateWarning {
        if (currentGame == null) return;
        currentGame.doClaimRoute(routeId, null);
    }

    public void doClaimRoute(int routeId, TrainType type) throws StateWarning {
        if (currentGame == null) return;
        currentGame.doClaimRoute(routeId, type);
    }

    public void doEndTurn() throws StateWarning {
        if (currentGame == null) return;
        currentGame.doEndTurn();
    }

    private void notifyPropertyChanges(String... properties) {
        UpdateIndicator u = new UpdateIndicator();
        for (String property : properties) {
            u.addProperty(property);
        }
        setChanged();
        notifyObservers(u);
    }


    public Map<IPlayer, Integer> getLongestRoute() {
        return longestPath;
    }

    public void setLongestPath() {
        longestPath = currentGame.getMap().findLongestRoute();
        notifyPropertyChanges(ROUTE);
    }

    public TrainType getRouteAnyCardType() {
        return RouteAnyCardType;
    }

    public void setRouteAnyCardType(TrainType routeAnyCardType) {
        RouteAnyCardType = routeAnyCardType;
    }

    public boolean isLastTurns() {
        return isLastTurns;
    }

    public void setLastTurns(boolean lastTurns) {
        isLastTurns = lastTurns;
    }
}
