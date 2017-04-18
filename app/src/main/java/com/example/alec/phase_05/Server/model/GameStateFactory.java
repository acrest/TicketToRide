package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameMapInfo;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class GameStateFactory {

    public static GameInfo gameToGameState(IServerGame game) {
        int maxPlayers = game.getMaxPlayers();
        Player[] players = new Player[maxPlayers];
        TrainCard[] visibleTrainCards = new TrainCard[IGame.NUM_VISIBLE_CARDS];

        for(int i = 0; i < players.length; ++i) {
            players[i] = (Player) game.getPlayer(i);
        }
        for(int i = 0; i < visibleTrainCards.length; ++i) {
            visibleTrainCards[i] = game.getVisibleCard(i);
        }
        System.out.println("in gamestatefactory the game status is "+game.getTurnStatus());
        return new GameInfo(game.getID(), game.getName(), maxPlayers, players, visibleTrainCards, game.getNumberOfTrainCards(), game.getNumberOfDestinationCards(), new GameMapInfo(game.getMap()), game.getPlayerTurnIndex(), game.getTurnStatus(), game.getHaveDrawnInitialDestinationCards(), game.getLastPlayerTurn());
    }

    private GameStateFactory() {}
}
