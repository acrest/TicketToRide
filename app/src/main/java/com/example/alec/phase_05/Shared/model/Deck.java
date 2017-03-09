package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Molly on 3/7/2017.
 */

public class Deck {

    private List<TrainCard> cards;
    private List<TrainCard> discard;

    public Deck(){
        cards = new ArrayList();
        discard = new ArrayList<>();

        for (TrainType t : TrainType.values()){
            List<TrainCard> cardColor = new ArrayList<TrainCard>();
            if (t == TrainType.LOCOMOTIVE){
                TrainCard card = new TrainCard(t);
                cardColor = new ArrayList<TrainCard>(Collections.nCopies(14, card));
            }
            else
            {
                if (t != TrainType.ANY){
                    TrainCard card = new TrainCard(t);
                    cardColor = new ArrayList<TrainCard>(Collections.nCopies(12, card));
                }
            }
            System.out.println("the deck size is now: " + cards.size());

            cards.addAll(cardColor);
        }
        shuffleDeck();
    }

    public int getDeckSize(){
        return cards.size();
    }

    public void shuffleDeck(){
        if (discard.size() > 0){
            Collections.shuffle(discard);
            cards = discard;
            discard.clear();
        }
        else{
            Collections.shuffle(cards);
        }
    }

    public TrainCard drawCard(){
        if (cards.size() == 1){
            shuffleDeck();
        }
       return cards.remove(0);
    }

    public void discardCard(TrainCard dis){
        discard.add(dis);
    }


}
