package com.example.alec.phase_05.Client;

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
        ClientModel.getInstance().setCurrentGameDescription(description);
    }

    public void drawDestinationCard(String playerName, DestinationCard card) {
        ClientModel model = ClientModel.getInstance();
        IClientGame game = model.getCurrentGame();
        IClientBank bank = (IClientBank) game.getBank();
        bank.decNumberOfDestinationCards();
        Player player = game.findPlayerByName(playerName);
        player.addDestinationCard(card);
    }

    public void drawTrainCard(String playerName, int index, TrainCard card, TrainCard nextCardInDeck) {
        ClientModel model = ClientModel.getInstance();
        IClientGame game = model.getCurrentGame();
        IClientBank bank = (IClientBank) game.getBank();
        bank.setVisibleCard(index, nextCardInDeck);
        Player player = game.findPlayerByName(playerName);
        player.addTrainCard(card);
    }

    public void setGameState(GameState gameState) {
        IClientGame game = ClientModel.getInstance().getCurrentGame();
        if(game == null) {
            ClientModel.getInstance().setCurrentGame(ClientGameFactory.createGame(gameState));
            return;
        }
        if(game.getID() != gameState.getId() ||
                !game.getName().equals(gameState.getName()) ||
                game.getMaxPlayers() != gameState.getMaxPlayers()) {
            throw new IllegalArgumentException("gameState must have the same name, id, and max players as the current game");
        }
        IClientBank bank = (IClientBank) game.getBank();
        Player[] players = gameState.getPlayers();
        for(int i = 0; i < players.length; ++i) {
            game.setPlayer(i, players[i]);
        }
        TrainCard[] visibleTrainCards = gameState.getVisibleTrainCards();
        for(int i = 0; i < players.length; ++i) {
            bank.setVisibleCard(i,visibleTrainCards[i]);
        }
        game.setMap(gameState.getMap());
    }

    public void executeCommands(List<ICommand> commands) {
        for(ICommand command : commands) {
            command.execute();
        }
    }
}
