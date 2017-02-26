package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IBank;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {

    public ClientGame(int id, String name, int maxPlayers, IBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
    }
}
