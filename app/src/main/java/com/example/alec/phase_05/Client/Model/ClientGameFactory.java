package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class ClientGameFactory {

    public static IClientGame createGame(GameState gameState) {
        int id = gameState.getId();
        String name = gameState.getName();
        int maxPlayers = gameState.getMaxPlayers();
        IClientBank bank = createBank();
        TrainCard[] cards = gameState.getVisibleTrainCards();
        for(int i = 0; i < cards.length; ++i) {
            bank.setVisibleCard(i, cards[i]);
        }
        GameMap map = gameState.getMap();
        IClientGame game = new ClientGame(id, name, maxPlayers, bank, map);
        Player[] players = gameState.getPlayers();
        for(int i = 0; i < players.length; ++i) {
            game.setPlayer(i, players[i]);
        }
        return game;
    }

    private static IClientBank createBank() {
        return new ClientBank();
    }

    private void ClientGameFactory() {}
}
