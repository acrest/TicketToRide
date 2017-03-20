package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by samuel on 3/16/17.
 */

public class DestinationCardDeck {
    private List<DestinationCard> cards;

    public DestinationCardDeck() {
        cards = new ArrayList<>();
    }

    public DestinationCardDeck(Collection<DestinationCard> cards) {
        this();
        this.cards.addAll(cards);
    }

    public DestinationCard drawCard() {
        if(cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public void addCardToBottom(DestinationCard card) {
        cards.add(0, card);
    }

    public int getSize() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
