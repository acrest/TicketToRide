package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;

/**
 * Created by Andrew on 2/20/2017.
 */

public class DestinationCard implements Serializable {

    private City city1;
    private City city2;
    private int value;

    public DestinationCard(City _city1, City _city2, int _value){
        city1 = _city1;
        city2 = _city2;
        value = _value;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return city1.getName() + " to " + city2.getName() + " " + getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DestinationCard) {
            DestinationCard card = (DestinationCard) obj;
            return city1.equals(card.city1) && city2.equals(card.city2) && value == card.value;
        }
        return false;
    }
}
