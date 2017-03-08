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

    private List<Integer> cards;
    private List<Integer> discard;

    public Deck(){
        cards = new ArrayList();
        discard = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            List<Integer> cardColor;
            if (i == 8){
                cardColor = new ArrayList<Integer>(Collections.nCopies(14, i));
            }
            else
            {
                cardColor = new ArrayList<Integer>(Collections.nCopies(12, i));
            }
            System.out.println("the deck size is now: " + cards.size());

            cards.addAll(cardColor);
        }
        shuffleDeck();
    }

    public void shuffleDeck(){
        if (discard.size() != 0){
            Collections.shuffle(discard);
            cards = discard;
            discard.clear();
        }
        else{
            Collections.shuffle(cards);
        }
    }

    public int drawCard(){
        if (cards.size() == 1){
            shuffleDeck();
        }
       return cards.remove(0);
    }

    public void discardCard(int dis){
        discard.add(dis);
    }


}
