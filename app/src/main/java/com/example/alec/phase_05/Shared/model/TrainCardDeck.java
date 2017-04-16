package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 3/16/17.
 */

public class TrainCardDeck implements Serializable {
    private List<TrainCard> cards;

    public TrainCardDeck(){
        cards=new ArrayList<>();
    }

    public TrainCardDeck(Collection<TrainCard> cards){
        this();
        this.cards.addAll(cards);
    }

    public TrainCard drawCard() {
        if(cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public void addCard(TrainCard card) {
        cards.add(0, card);
    }

    public int getSize() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void transferCards(TrainCardDeck deck){
        deck.cards.addAll(cards);
        cards.clear();
    }
}
