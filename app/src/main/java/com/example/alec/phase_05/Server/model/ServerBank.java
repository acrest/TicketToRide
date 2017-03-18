package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.DestinationCardDeck;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainCardDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerBank implements IServerBank {

    private static final int NUM_VISIBLE_TRAIN_CARDS = 5;

    private TrainCardDeck trainCardDeck;
    private TrainCardDeck discardDeck;
    private List<TrainCard> visibleTrainCards;
    private DestinationCardDeck destinationCardDeck;

    public ServerBank(TrainCardDeck trainCardDeck, DestinationCardDeck destinationCardDeck) {
        this.trainCardDeck = trainCardDeck;
        this.destinationCardDeck = destinationCardDeck;
        visibleTrainCards = new ArrayList<>();
        discardDeck = new TrainCardDeck();
        initCards();
    }


    private void initCards() {
        trainCardDeck.shuffle();
        destinationCardDeck.shuffle();
    }

    private void fillVisibleTrainCards() {
        for (int i = 0; i < NUM_VISIBLE_TRAIN_CARDS; ++i) {
            if (i >= visibleTrainCards.size()) {
                visibleTrainCards.add(drawTrainCard());
            } else if (visibleTrainCards.get(i) == null) {
                visibleTrainCards.set(i, drawTrainCard());
            }
        }
    }

    @Override
    public TrainCard getVisibleCard(int index) {
        while (index >= visibleTrainCards.size()) {
            visibleTrainCards.add(null);
        }
        return visibleTrainCards.get(index);
    }

    @Override
    public int getNumberOfTrainCards() {
        return trainCardDeck.getSize();
    }

    @Override
    public int getNumberOfDestinationCards() {
        return destinationCardDeck.getSize();
    }

    @Override
    public DestinationCard drawDestinationCard() {
        return destinationCardDeck.drawCard();
    }

    @Override
    public void addDestinationCardToBottom(DestinationCard card) {
        destinationCardDeck.addCardToBottom(card);
    }

    @Override
    public TrainCard drawTrainCard() {
        if (trainCardDeck.getSize() == 0) fillTrainDeck();
        return trainCardDeck.drawCard();
    }

    @Override
    public void discardTrainCard(TrainCard card) {
        discardDeck.addCard(card);
    }

    @Override
    public TrainCard drawVisibleTrainCard(int index) {
        if (index < visibleTrainCards.size()) {
            TrainCard card = visibleTrainCards.get(index);
            visibleTrainCards.set(index, null);
            fillVisibleTrainCards();
            return card;
        }
        return null;
    }

    private void fillTrainDeck() {
        discardDeck.transferCards(trainCardDeck);
        trainCardDeck.shuffle();
    }
}
