package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends AbstractPlayer {
    private ArrayList<TrainCard> trainCards;
    private ArrayList<DestinationCard> destinationCards;


    public Player(String name) {
        super(name);
        trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
    }

    @Override
    public int getTrainCardCount() {
        return trainCards.size();
    }

    @Override
    public int getDestinationCardCount() {
        return destinationCards.size();
    }

    public void addTrainCard(TrainCard card) {
        trainCards.add(card);
    }

    public void removeTrainCard(int index) {
        trainCards.remove(index);
    }

    public TrainCard getTrainCard(int index) {
        return trainCards.get(index);
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void addDestinationCard(DestinationCard card) {
        destinationCards.add(card);
    }

    public void removeDestinationCard(int index) {
        destinationCards.remove(index);
    }

    public DestinationCard getDestinationCard(int index) {
        return destinationCards.get(index);
    }

    public List<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public boolean removeCardsOfType(TrainType cardType, int cardCount) {
        Iterator<TrainCard> it = trainCards.iterator();
        while (cardCount > 0 && it.hasNext()) {
            if (it.next().getType().equals(cardType)) {
                it.remove();
                cardCount--;
            }
        }
        return cardCount == 0;
    }

    public int countCardsOfType(TrainType type) {
        int count = 0;
        for (TrainCard card : trainCards) {
            if (card.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Player) {
            return getName().equals(((Player) other).getName());
        } else if (other instanceof String) {
            return getName().equals((String) other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}


/*+getTrainCardNumber() : Int
        +getInitialDestinationCards():Array<DestinationCard>
+returnInitialDestCard(DestCard dc): void
        +getDestinationCard():DesinationCard*/
