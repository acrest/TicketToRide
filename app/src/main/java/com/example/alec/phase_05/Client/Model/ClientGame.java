package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
    }

    @Override
    public IPlayer getPlayerByName(String playerName) {
        for (int i = 0; i < getMaxPlayers(); ++i) {
            IPlayer player = getPlayer(i);
            if (player != null && player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void decNumberOfDestinationCards() {
        ((IClientBank) getBank()).decNumberOfDestinationCards();
    }

    @Override
    public void incNumberOfDestinationCards() {
        ((IClientBank) getBank()).incNumberOfDestinationCards();
    }

    @Override
    public void decNumberOfTrainCards() {
        ((IClientBank) getBank()).decNumberOfTrainCards();
    }

    @Override
    public void setVisibleCard(int index, TrainCard card) {
        ((IClientBank) getBank()).setVisibleCard(index, card);
    }

    @Override
    public GameMap getMap() {
        return getGameMap();
    }

    @Override
    public void setMap(GameMap map) {
        setGameMap(map);
    }
}
