package com.example.alec.phase_05.Client.States.TrainCardStates;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class RainbowCardPickedState implements TrainCardState {

    public RainbowCardPickedState() {}

    @Override
    public TrainCard getCard() {
        // must end turn.
        return null;
    }
}
