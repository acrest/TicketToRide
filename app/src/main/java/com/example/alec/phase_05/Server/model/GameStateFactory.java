package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class GameStateFactory {

    public static GameInfo gameToGameState(IServerGame game) {
        int id = game.getID();
        String name = game.getName();
        int maxPlayers = game.getMaxPlayers();
        Player[] players = new Player[maxPlayers];
        TrainCard[] visibleTrainCards = new TrainCard[IGame.NUM_VISIBLE_CARDS];
        GameMap map = game.getMap();

        for(int i = 0; i < players.length; ++i) {
            players[i] = (Player) game.getPlayer(i);
        }
        for(int i = 0; i < visibleTrainCards.length; ++i) {
            visibleTrainCards[i] = game.getVisibleCard(i);
        }

        return new GameInfo(id, name, maxPlayers, players, visibleTrainCards, map);
    }

    private GameStateFactory() {}
}
