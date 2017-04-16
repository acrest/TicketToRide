package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;

/**
 * Created by Andrew on 2/20/2017.
 */

public class TrainCard implements Serializable {
    private TrainType type;
    private int ID;


    public TrainCard(TrainType type) {
        this.type = type;
        ID = -1;
    }

    public TrainCard(TrainType type, int ID) {
        this.type = type;
        this.ID = ID;
    }

    public TrainType getType() {
        return type;
    }


}
