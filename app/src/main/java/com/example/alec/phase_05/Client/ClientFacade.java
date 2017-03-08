package com.example.alec.phase_05.Client;

import android.util.Log;

import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientGameFactory;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.IClientBank;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by Molly on 2/6/2017.
 */

public class ClientFacade {

    private static ClientFacade instance;

    public static ClientFacade getInstance() {
        if(instance == null) instance = new ClientFacade();
        return instance;
    }

    public void updateGameList(List<GameDescription> games)
    {
        ClientModel.getInstance().setGameList(games);
    }

    public void createGame(GameState gameState) {

        if(gameState != null) {
            ClientModel.getInstance().setCreateGameSuccess(true);
            IClientGame game = ClientGameFactory.createGame(gameState);
            ClientModel.getInstance().setCurrentGame(game);
        } else {
            ClientModel.getInstance().setCreateGameSuccess(false);
        }

    }

    public void joinGame(GameState gameState) {
        if(gameState != null) {
            ClientModel.getInstance().setJoinGameSuccess(true);
            IClientGame game = ClientGameFactory.createGame(gameState);
            ClientModel.getInstance().setCurrentGame(game);
        } else {
            ClientModel.getInstance().setJoinGameSuccess(false);
        }
    }

    public void setCurrentPlayer(Player player) {
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    public void setCurrentGameDescription(GameDescription description) {
        ClientModel model = ClientModel.getInstance();
        List<Player> players = description.getPlayers();
        for(int i = 0; i < model.getGameMaxPlayers(); ++i) {
            model.setPlayer(i, null);
        }
        for(int i = 0; i < players.size(); ++i) {
            model.setPlayer(i, players.get(i));
        }
    }

    public void drawDestinationCard(String playerName, DestinationCard card) {
        ClientModel model = ClientModel.getInstance();
        model.decNumOfDestinationCards();
        model.addDestinationCard(playerName, card);
    }

    public void drawTrainCard(String playerName, int index, TrainCard card, TrainCard nextCardInDeck) {
        ClientModel model = ClientModel.getInstance();
        model.setVisibleCard(index, nextCardInDeck);
        model.addTrainCard(playerName, card);
    }

    public void setGameState(GameState gameState) {
        ClientModel model = ClientModel.getInstance();
        if(!model.hasCurrentGame()) {
            ClientModel.getInstance().setCurrentGame(ClientGameFactory.createGame(gameState));
            return;
        }
        if(model.getGameID() != gameState.getId() ||
                !model.getGameName().equals(gameState.getName()) ||
                model.getGameMaxPlayers() != gameState.getMaxPlayers()) {
            throw new IllegalArgumentException("gameState must have the same name, id, and max players as the current game");
        }
        Player[] players = gameState.getPlayers();
        for(int i = 0; i < players.length; ++i) {
            model.setPlayer(i, players[i]);
        }
        TrainCard[] visibleTrainCards = gameState.getVisibleTrainCards();
        for(int i = 0; i < players.length; ++i) {
            model.setVisibleCard(i,visibleTrainCards[i]);
        }
        model.setMap(gameState.getMap());
    }

    public void startGame() {
        Facade facade = Facade.getInstance();
        facade.drawDestinationCard();
        facade.drawDestinationCard();
        facade.drawDestinationCard();
        ClientModel.getInstance().setGameStarted();
    }

    public void addTrainCard(TrainCard card) {
        ClientModel.getInstance().addTrainCard(card);
    }

    public void addDestinationCard(DestinationCard card) {
        ClientModel.getInstance().addDestinationCard(card);
    }

    public void executeCommands(List<ICommand> commands) {
        Log.d("ClientFacade", "called executeCommands with size of " + commands.size());
        for(ICommand command : commands) {
            command.execute();
        }
    }
}
