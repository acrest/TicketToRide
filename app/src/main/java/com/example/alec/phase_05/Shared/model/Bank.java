package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/23/17.
 */

public class Bank implements IBank {

    private List<TrainCard> trainCardDeck;
    private List<TrainCard> visibleTrainCards;
    private List<DestinationCard> destinationCardDeck;

    public Bank() {
        trainCardDeck = new ArrayList<>();
        visibleTrainCards = new ArrayList<>();
        destinationCardDeck = new ArrayList<>();
    }

    public void initCards() {

    }
}
