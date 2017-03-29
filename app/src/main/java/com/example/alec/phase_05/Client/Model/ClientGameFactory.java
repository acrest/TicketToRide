package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class ClientGameFactory {

    public static IClientGame createGame(GameInfo gameInfo) {

        System.out.println("inside client game factory");
        IClientBank bank = createBank();
        TrainCard[] cards = gameInfo.getVisibleTrainCards();
        for (int i = 0; i < cards.length; ++i) {
            bank.setVisibleCard(i, cards[i]);
        }
        IClientGame game = new ClientGame(gameInfo.getId(), gameInfo.getName(), gameInfo.getMaxPlayers(),
                bank, gameInfo.getMap());
        Player[] players = gameInfo.getPlayers();
        for (int i = 0; i < players.length; ++i) {
            Player player = players[i];
            if (player != null) {
                if (player.getName().equals(ClientModel.getInstance().getCurrentPlayerName())) {
                    game.setPlayer(i, player);
                } else {
                    game.setPlayer(i, new OtherPlayer(players[i]));
                }
                //game.getPlayer(i).setTrainCount(Facade.getInstance().getTrainCount());
            }
        }
        for (Player player : players) {
            if (player != null) {
                game.setCurrentPlayerTurn(player.getName());
                break;
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
