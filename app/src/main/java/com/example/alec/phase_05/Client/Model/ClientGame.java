package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.EachGameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    private EachGameState gameState;

    public ClientGame(int id, String name, int maxPlayers, IBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
    }

    @Override
    public IPlayer findPlayerByName(String playerName) {
        for (int i = 0; i < getMaxPlayers(); ++i) {
            IPlayer player = getPlayer(i);
            if (player != null && player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }
}
