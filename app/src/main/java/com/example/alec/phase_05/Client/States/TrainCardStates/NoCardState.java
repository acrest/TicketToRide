package com.example.alec.phase_05.Client.States.TrainCardStates;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class NoCardState implements TrainCardState {

    public NoCardState() {}

    @Override
    public TrainCard getCard() {
        return null;
    }
}
