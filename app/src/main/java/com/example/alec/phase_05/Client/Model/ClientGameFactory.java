package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class ClientGameFactory {

    public static IClientGame createGame(GameState gameState) {
        IClientBank bank = createBank();
        TrainCard[] cards = gameState.getVisibleTrainCards();
        for (int i = 0; i < cards.length; ++i) {
            bank.setVisibleCard(i, cards[i]);
        }
        IClientGame game = new ClientGame(gameState.getId(), gameState.getName(), gameState.getMaxPlayers(),
                bank, gameState.getMap());
        Player[] players = gameState.getPlayers();
        for (int i = 0; i < players.length; ++i) {
            Player player = players[i];
            if (player != null) {
                if (player.getName().equals(ClientModel.getInstance().getCurrentPlayerName())) {
                    game.setPlayer(i, player);
                } else {
                    game.setPlayer(i, new OtherPlayer(players[i]));
                }
            }
        }
        return game;
    }

    private static IClientBank createBank() {
        return new ClientBank();
    }

    private void ClientGameFactory() {
    }
}
