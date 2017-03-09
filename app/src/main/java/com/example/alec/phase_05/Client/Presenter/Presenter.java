package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;

import java.util.Observable;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class Presenter implements IPresenter {

    public Presenter() {
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
//        if(!(o instanceof UpdateIndicator)) {
//            throw new IllegalArgumentException("object passed to update() must be of type UpdateIndicator");
//        }
        final UpdateIndicator u = (UpdateIndicator) o;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                update(u);
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
