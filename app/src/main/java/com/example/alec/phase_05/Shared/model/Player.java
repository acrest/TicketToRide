package com.example.alec.phase_05.Shared.model;

import com.example.alec.phase_05.Client.Model.ClientModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    private Map<TrainType, Integer> getCardCounts() {
        Map<TrainType, Integer> counts = new HashMap<>();
        for(TrainCard card : trainCards) {
            if(!counts.containsKey(card.getType())) {
                counts.put(card.getType(), 0);
            }
            counts.put(card.getType(), counts.get(card.getType()) + 1);
        }
        return counts;
    }

    public boolean hasCardsForRoute(TrainType routeType, int routeLength) {
        Map<TrainType, Integer> cardCounts = getCardCounts();
        int wildCount = cardCounts.containsKey(TrainType.LOCOMOTIVE) ? cardCounts.get(TrainType.LOCOMOTIVE) : 0;
        if(routeType.equals(TrainType.ANY)) {
            routeType = ClientModel.getInstance().getRouteAnyCardType();
        }
        return (routeType != null && cardCounts.containsKey(routeType) && ((cardCounts.get(routeType) + wildCount) >= routeLength)) || wildCount >= routeLength;
    }

    //this method assumes that the player has enough cards
    //hasCardsForRoute should be called to check this
    public List<TrainCard> removeCardsForRoute(TrainType routeType, int routeLength) {
        List<TrainCard> removedCards = new ArrayList<>();
        if(routeType.equals(TrainType.ANY)) {
            routeType = ClientModel.getInstance().getRouteAnyCardType();
        }

        Iterator<TrainCard> cards = trainCards.iterator();
        while(cards.hasNext() && routeLength > 0) {
            TrainCard card = cards.next();
            if(card.getType().equals(routeType)) {
                cards.remove();
                removedCards.add(card);
                routeLength--;
            }
        }

        cards = trainCards.iterator();
        while(cards.hasNext() && routeLength > 0) {
            TrainCard card = cards.next();
            if(card.getType().equals(TrainType.LOCOMOTIVE)) {
                cards.remove();
                removedCards.add(card);
                routeLength--;
            }
        }
        return removedCards;
    }

    //will not return LOCOMOTIVE
    private TrainType getTypeOfMaxCount() {
        Map<TrainType, Integer> cardCounts = getCardCounts();
        TrainType maxType = null;
        int maxCount = 0;
        for (Map.Entry<TrainType, Integer> cardCount : cardCounts.entrySet()) {
            if(!cardCount.getKey().equals(TrainType.LOCOMOTIVE) && (maxType == null || cardCount.getValue() > maxCount)) {
                maxCount = cardCount.getValue();
                maxType = cardCount.getKey();
            }
        }
        return maxType;
    }

    public boolean removeCardsOfType(TrainType trainType, int cardCount, TrainType cardType) {
        if(trainType.equals(TrainType.ANY)){
            cardCount = removeCardsOfTypeAny(cardCount, cardType);
        }
        else{
            Iterator<TrainCard> it = trainCards.iterator();
            while (cardCount > 0 && it.hasNext()) {
                if (it.next().getType().equals(trainType)) {
                    it.remove();
                    cardCount--;
                }
            }
        }

        return cardCount == 0;
    }

    private int removeCardsOfTypeAny(int cardCount, TrainType type){
        Iterator<TrainCard> it = trainCards.iterator();
        while (cardCount > 0 && it.hasNext()) {
            if (it.next().getType().equals(type)) {
                it.remove();
                cardCount--;
            }
        }

        if(cardCount != 0){
            removeWilds(cardCount);
        }

        return cardCount;
    }

    private void removeWilds(int count){
        for(TrainCard card: trainCards){
            if(card.getType().equals(TrainType.LOCOMOTIVE))
            {
                trainCards.remove(card);
                ClientModel.getInstance().updateTrainCardDisplay();
                count--;

                if(count == 0){
                    return;
                }
            }
        }
    }

    public int countCardsOfType(TrainType type) {
        int count = 0;
        int anyCount = 0;

        for (TrainCard card : trainCards) {
            /*if (type.equals(TrainType.ANY)){ //Has to all be the same color of any type.
                count++;
            }*/
            if (card.getType().equals(type) || card.getType().equals(TrainType.LOCOMOTIVE)) {
                count++;
            }
        }

        anyCount = giveHighestCountOfColors() + numOfWilds();
        String test = type.toString();
        String test2 = TrainType.ANY.toString();

        if(ClientModel.getInstance().getRouteAnyCardType() != null){
            if(test.equals(test2)){
                return anyCount;
            }
        }

        return count;
    }

    private int giveHighestCountOfColors(){
//        int redCount = 0;
//        int orangeCount = 0;
//        int yellowCount = 0;
//        int greenCount = 0;
//        int blueCount = 0;
//        int purpleCount = 0;
//        int whiteCount = 0;
//        int blackCount = 0;
        int count = 0;

        if(ClientModel.getInstance().getRouteAnyCardType() != null){
            for(TrainCard card: trainCards){
                if(card.getType().equals(ClientModel.getInstance().getRouteAnyCardType())){
                    count++;
                }
            }
        }

        /*for(TrainCard card : trainCards) {
            if(card.getType().equals(TrainType.CABOOSE)) {
                greenCount++;
                //green
            }
            if(card.getType().equals(TrainType.TANKER)){
                orangeCount++;
            }
            if(card.getType().equals(TrainType.FREIGHT)){
                purpleCount++;
                //purple
            }
            if(card.getType().equals(TrainType.PASSENGER)){
                blueCount++;
            }
            if(card.getType().equals(TrainType.REEFER)){
                whiteCount++;
            }
            if(card.getType().equals(TrainType.BOX)){
                yellowCount++;
                //yellow
            }
            if(card.getType().equals(TrainType.COAL)){
                redCount++;
            }
            if(card.getType().equals(TrainType.HOPPER)){
                blackCount++;
            }
        }

        int count = redCount;

        if(orangeCount>count){
            count = orangeCount;
        }
        if(yellowCount > count){
            count = yellowCount;
        }
        if(greenCount > count){
            count = greenCount;
        }
        if(blueCount > count){
            count = blueCount;
        }
        if(purpleCount > count){
            count = purpleCount;
        }
        if(whiteCount > count){
            count = whiteCount;
        }
        if(blackCount > count){
            count = blackCount;
        }*/

        return count;
    }

    private int numOfWilds(){
        int count = 0;

        for(TrainCard card:trainCards){
            if(card.getType().equals(TrainType.LOCOMOTIVE)){
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
            return getName().equals(other);
        } else if (other instanceof OtherPlayer) {
            return getName().equals(((OtherPlayer) other).getName());
        } else if (other instanceof IPlayer) {
            return getName().equals(((IPlayer) other).getName());
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
