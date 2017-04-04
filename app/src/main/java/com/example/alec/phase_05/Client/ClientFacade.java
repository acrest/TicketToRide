package com.example.alec.phase_05.Client;

import android.graphics.Point;

import com.example.alec.phase_05.Client.Model.ClientGameFactory;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;
import java.util.Map;

/**
 * Created by Molly on 2/6/2017.
 */

public class ClientFacade {
    private static ClientFacade instance;

    public static ClientFacade getInstance() {
        if (instance == null) instance = new ClientFacade();
        return instance;
    }

    private ClientModel model;

    public ClientFacade() {
        model = ClientModel.getInstance();
    }

    public void updateGameList(List<GameDescription> games) {
        model.setGameList(games);
    }

    public void createGame(GameInfo gameInfo) {
        System.out.println("ISDONF");
        if (gameInfo != null) {
            System.out.println("in clientfacade");
            model.setCreateGameSuccess(true);
            model.setCurrentGame(ClientGameFactory.createGame(gameInfo));
            model.setHost(true);
            Poller.getInstance().setPlayerWatingPolling();
        } else {
            model.setCreateGameSuccess(false);
        }

    }

    public void joinGame(GameInfo gameInfo) {
        if (gameInfo != null) {
            model.setJoinGameSuccess(true);
            model.setCurrentGame(ClientGameFactory.createGame(gameInfo));
            model.setHost(false);
            Poller.getInstance().setPlayerWatingPolling();
        } else {
            model.setJoinGameSuccess(false);
        }
    }

    public void setCurrentGameDescription(GameDescription description) {
        for (int i = 0; i < model.getGameMaxPlayers(); ++i) {
            model.setPlayer(i, null);
        }
        List<Player> players = description.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player player = players.get(i);
            if (player != null) {
                if (player.getName().equals(model.getCurrentPlayerName())) {
                    model.setPlayer(i, player);
                } else {
                    model.setPlayer(i, new OtherPlayer(player));
                }
            }
        }
    }

    public void login(String username, boolean success) {
        model.setLoginSuccess(success);
        if (success) {
            model.setCurrentPlayerName(username);
            Poller poller = Poller.getInstance();
            poller.setListGamePolling();
            poller.start();
        }
    }

    public void register(String username, boolean success) {
        model.setRegisterSuccess(success);
        if (success) {
            model.setCurrentPlayerName(username);
            Poller poller = Poller.getInstance();
            poller.setListGamePolling();
            poller.start();
        }
    }

    public void drawDestinationCard(String playerName, int remainingCards) {
        model.setNumberOfDestinationCards(remainingCards);
        model.addDestinationCard(playerName);
        // Display the destination card choices the player has drawn 3 cards or there are no more cards.
        // The second case occurs when the deck is out and they have only drawn 1 or 2 cards.
        if (remainingCards == 0 || model.getCardChoices().size() == 3) {
            model.displayHand();
        }
    }

    public void pickTrainCard(String playerName, int index, TrainCard nextCardInDeck, int remainingCards) {
        model.setVisibleCard(index, nextCardInDeck);
        model.setNumberOfTrainCards(remainingCards);
        model.addTrainCard(playerName);
    }

    public void drawTrainCard(String playerName, int remainingCards) {
        model.setNumberOfTrainCards(remainingCards);
        model.addTrainCard(playerName);
    }

    public void setGameInfo(GameInfo gameInfo) {
        if (!model.hasCurrentGame()) {
            model.setCurrentGame(ClientGameFactory.createGame(gameInfo));
            return;
        }
        if (model.getGameID() != gameInfo.getId() ||
                !model.getGameName().equals(gameInfo.getName()) ||
                model.getGameMaxPlayers() != gameInfo.getMaxPlayers()) {
            throw new IllegalArgumentException("gameInfo must have the same name, id, and max players as the current game");
        }
        Player[] players = gameInfo.getPlayers();
        for (int i = 0; i < players.length; ++i) {
            Player player = players[i];
            if (player != null) {
                if (player.getName().equals(model.getCurrentPlayerName())) {
                    model.setPlayer(i, player);
                } else {
                    model.setPlayer(i, new OtherPlayer(player));
                }
//                model.getPlayer(i).setTrainCount(Facade.getInstance().getTrainCount());
            }
        }
        TrainCard[] visibleTrainCards = gameInfo.getVisibleTrainCards();
        for (int i = 0; i < players.length; ++i) {
            model.setVisibleCard(i, visibleTrainCards[i]);
        }
        model.setNumberOfDestinationCards(gameInfo.getDestinationCardsRemaining());
        model.setNumberOfTrainCards(gameInfo.getTrainCardsRemaining());
        model.setMap(gameInfo.getMap());
    }

    public void startGame() {
        Facade.getInstance().drawTrainCard();
        Facade.getInstance().drawTrainCard();
        Facade.getInstance().drawTrainCard();
        Facade.getInstance().drawTrainCard();
        Facade.getInstance().drawDestinationCard();
        Facade.getInstance().drawDestinationCard();
        Facade.getInstance().drawDestinationCard();
        model.setGameStarted(); //make sure this is called at the end
    }

    public void addTrainCard(TrainCard card) {
        model.addTrainCard(card);
    }

    public void addDestinationCard(DestinationCard card) {
        model.addCardToChoices(card);
    }

    public void claimRoute(String playerName, int routeId, int remainingTrainCards, int playerRemainingTrainCards, int playerRemainingTrains, int playerPoints) {
        model.setRouteOwner(playerName, routeId);
        model.setNumberOfTrainCards(remainingTrainCards);
        model.setPlayerTrainCardCount(playerName, playerRemainingTrainCards);
        model.setTrainCount(playerName, playerRemainingTrains);
        model.setPlayerPoints(playerName, playerPoints);
    }



    public void finishTurn(String playerName) {
        model.endTurn(playerName);
    }

    public void finishGame(Map<String, Integer> bonusPoints) {
        for(Map.Entry<String, Integer> entry : bonusPoints.entrySet()) {
            model.setBonusPoints(entry.getKey(), entry.getValue());
        }
        model.notifyFinishGame();
    }

    public void returnDestinationCard(String playerName, int remainingCards) {
        model.removeDestinationCard(playerName);
        model.incNumOfDestinationCards();
    }

    public void chatSent(Chat chat) {
        model.addChat(chat);
    }

    public void executeCommand(ICommand command) {
        command.execute();
    }
}
