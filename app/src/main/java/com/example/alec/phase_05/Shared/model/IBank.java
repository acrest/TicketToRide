package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by samuel on 2/22/17.
 */

public interface IBank extends Serializable {
    TrainCard getVisibleCard(int index);

    int getNumberOfTrainCards();

    int getNumberOfDestinationCards();
}
