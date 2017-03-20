package com.example.alec.phase_05.Client.States.TrainCardStates;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class TwoDrawnCardState implements TrainCardState {

    public TwoDrawnCardState() {}

    @Override
    public TrainCard getCard() {
        // must end turn.
        return null;
    }
}