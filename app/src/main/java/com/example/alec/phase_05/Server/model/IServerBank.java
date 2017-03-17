package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public interface IServerBank extends IBank {
    DestinationCard drawDestinationCard();

    void addDestinationCardToBottom(Integer id, DestinationCard card);

    TrainCard drawTrainCard();

    void discardTrainCard(TrainCard card);

    TrainCard drawVisibleTrainCard(int index);
}
