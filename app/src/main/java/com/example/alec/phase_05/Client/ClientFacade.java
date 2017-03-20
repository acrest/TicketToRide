package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.Model.ClientGameFactory;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

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
        if (gameInfo != null) {
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

    public void drawDestinationCard(String playerName) {
        model.decNumOfDestinationCards();
        model.addDestinationCard(playerName);
    }

    public void pickTrainCard(String playerName, int index, TrainCard nextCardInDeck) {
        model.setVisibleCard(index, nextCardInDeck);
        model.addTrainCard(playerName);
    }

    public void drawTrainCard(String playerName) {
        model.decNumOfTrainCards();
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
            }
        }
        TrainCard[] visibleTrainCards = gameInfo.getVisibleTrainCards();
        for (int i = 0; i < players.length; ++i) {
            model.setVisibleCard(i, visibleTrainCards[i]);
        }
        model.setMap(gameInfo.getMap());
    }

    public void startGame() {
        Facade facade = Facade.getInstance();
        facade.drawDestinationCard();
        facade.drawDestinationCard();
        facade.drawDestinationCard();
        facade.drawTrainCard();
        facade.drawTrainCard();
        facade.drawTrainCard();
        facade.drawTrainCard();
        model.setGameStarted();
    }

    public void addTrainCard(TrainCard card) {
        model.addTrainCard(card);
    }

    public void addDestinationCard(DestinationCard card) {
        model.addDestinationCard(card);
    }

    public void claimRoute(String playerName, int routeId) {
        model.setRouteOwner(playerName, routeId);
    }

    public void finishTurn(String playerName) {
        //TODO
    }

    public void returnDestinationCard(String playerName) {
        model.removeDestinationCard(playerName);
        model.incNumOfDestinationCards();
    }

    public void executeCommand(ICommand command) {
        command.execute();
    }
}
