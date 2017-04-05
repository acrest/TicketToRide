package com.example.alec.phase_05.Client.Presenter;

import java.util.Observer;

/**
 * Created by samuel on 3/2/17.
 */

public interface IPresenter extends Observer {
    void update(UpdateIndicator updateIndicator);

    void detach();
}
