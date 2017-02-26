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
        visibleTrainCards = null;
        numberOfTrainCards = 0;
        numberOfDestinationCards = 0;
    }

    @Override
    public List<TrainCard> getVisibleCards() {
        return visibleTrainCards;
    }

    @Override
    public int getNumberOfTrainCards() {
        return numberOfTrainCards;
    }

    @Override
    public int getNumberOfDestinationCards() {
        return numberOfDestinationCards;
    }

    public void setVisibleTrainCards(List<TrainCard> visibleTrainCards) {
        this.visibleTrainCards = visibleTrainCards;
    }

    public void setNumberOfTrainCards(int numberOfTrainCards) {
        this.numberOfTrainCards = numberOfTrainCards;
    }

    public void setNumberOfDestinationCards(int numberOfDestinationCards) {
        this.numberOfDestinationCards = numberOfDestinationCards;
    }
}
