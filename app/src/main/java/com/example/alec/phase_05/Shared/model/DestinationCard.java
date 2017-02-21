package com.example.alec.phase_05.Shared.model;

/**
 * Created by Andrew on 2/20/2017.
 */

public class DestinationCard {

    private City city1;
    private City city2;
    private Integer value;

    public DestinationCard(City _city1, City _city2, Integer _value){
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
