package com.example.alec.phase_05.Client.States;

import com.example.alec.phase_05.Shared.model.EachGameState;

/**
 * Created by clarkpathakis on 3/15/17.
 */

public abstract class ClientPlayerTurnState implements EachGameState{
    private String playerName;

    public ClientPlayerTurnState() {}

    public String getPlayerName() {
        return playerName;
    }

}
