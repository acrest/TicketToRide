package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerBank implements IServerBank {

    private static final int NUM_VISIBLE_TRAIN_CARDS = 5;

    private List<TrainCard> trainCardDeck;
    private List<TrainCard> visibleTrainCards;
    private List<DestinationCard> destinationCardDeck;

    public ServerBank(List<TrainCard> trainCardDeck, List<DestinationCard> destinationCardDeck) {
        this.trainCardDeck = trainCardDeck;
        this.destinationCardDeck = destinationCardDeck;
        visibleTrainCards = new ArrayList<>();
        initCards();
    }

    private void initCards() {
        Collections.shuffle(trainCardDeck);
        Collections.shuffle(destinationCardDeck);
    }

    private void fillVisibleTrainCards() {
        for(int i = 0; i < NUM_VISIBLE_TRAIN_CARDS; ++i) {
            if(i >= visibleTrainCards.size()) {
                visibleTrainCards.add(drawTrainCard());
            } else if(visibleTrainCards.get(i) == null) {
                visibleTrainCards.set(i, drawTrainCard());
            }
        }
    }

    @Override
    public TrainCard getVisibleCard(int index) {
        return visibleTrainCards.get(index);
    }

    @Override
    public int getNumberOfTrainCards() {
        return trainCardDeck.size();
    }

    @Override
    public int getNumberOfDestinationCards() {
        return destinationCardDeck.size();
    }

    @Override
    public DestinationCard drawDestinationCard() {
        if(destinationCardDeck.isEmpty()) {
            return null;
        }
        return destinationCardDeck.remove(destinationCardDeck.size() - 1);
    }

    @Override
    public void addDestinationCardToBottom(DestinationCard card) {
        destinationCardDeck.add(0, card);
    }

    @Override
    public TrainCard drawTrainCard() {
        if(trainCardDeck.isEmpty()) {
            return null;
        }
        return trainCardDeck.remove(trainCardDeck.size() - 1);
    }

    @Override
    public TrainCard drawVisibleTrainCard(int index) {
        if(index < visibleTrainCards.size()) {
            TrainCard card = visibleTrainCards.get(index);
            visibleTrainCards.set(index, null);
            fillVisibleTrainCards();
            return card;
        }
        return null;
    }
}
