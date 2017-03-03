package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 3/2/17.
 */

public final class GameStateFactory {

    public static GameState gameToGameState(IServerGame game) {
        int id = game.getID();
        String name = game.getName();
        int maxPlayers = game.getMaxPlayers();
        Player[] players = new Player[maxPlayers];
        TrainCard[] visibleTrainCards = new TrainCard[IGame.NUM_VISIBLE_CARDS];
        GameMap map = game.getMap();

        for(int i = 0; i < players.length; ++i) {
            players[i] = game.getPlayer(i);
        }
        for(int i = 0; i < visibleTrainCards.length; ++i) {
            visibleTrainCards[i] = game.getBank().getVisibleCard(i);
        }

        return new GameState(id, name, maxPlayers, players, visibleTrainCards, map);
    }

    private GameStateFactory() {}
}
