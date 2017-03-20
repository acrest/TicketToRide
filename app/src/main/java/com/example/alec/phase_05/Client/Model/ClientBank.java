package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientBank implements IClientBank {

    private List<TrainCard> visibleTrainCards;
    private int numberOfTrainCards;
    private int numberOfDestinationCards;

    public ClientBank() {
        visibleTrainCards = new ArrayList<>();
        numberOfTrainCards = 0;
        numberOfDestinationCards = 0;
    }

    @Override
    public TrainCard getVisibleCard(int index) {
        return visibleTrainCards.get(index);
    }

    @Override
    public int getNumberOfTrainCards() {
        return numberOfTrainCards;
    }

    @Override
    public int getNumberOfDestinationCards() {
        return numberOfDestinationCards;
    }

    @Override
    public void setVisibleCard(int index, TrainCard card) {
        while(index >= visibleTrainCards.size()) {
            visibleTrainCards.add(null);
        }
        visibleTrainCards.set(index, card);
    }

    @Override
    public void decNumberOfDestinationCards() {
        numberOfTrainCards--;
    }

    @Override
    public void incNumberOfDestinationCards() {
        numberOfDestinationCards++;
    }

    @Override
    public void decNumberOfTrainCards() {
        numberOfTrainCards--;
    }
}
