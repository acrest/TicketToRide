package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.IPlayer;

/**
 * Created by samuel on 2/25/17.
 */

public interface IClientGame extends IGame {
    IPlayer findPlayerByName(String playerName);
}
