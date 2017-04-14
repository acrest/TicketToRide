package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.io.Serializable;

/**
 * Created by samuel on 2/25/17.
 */

public interface IServerBank extends IBank, Serializable {
    DestinationCard drawDestinationCard();

    void addDestinationCardToBottom(DestinationCard card);

    TrainCard drawTrainCard();

    void discardTrainCard(TrainCard card);

    TrainCard drawVisibleTrainCard(int index);
}
