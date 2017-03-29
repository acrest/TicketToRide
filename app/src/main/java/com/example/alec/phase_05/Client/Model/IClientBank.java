package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public interface IClientBank extends IBank {
    void decNumberOfDestinationCards();

    void incNumberOfDestinationCards();

    void decNumberOfTrainCards();

    void setNumberOfTrainCards(int num);

    void setNumberOfDestinationCards(int num);

    void setVisibleCard(int index, TrainCard card);
}
