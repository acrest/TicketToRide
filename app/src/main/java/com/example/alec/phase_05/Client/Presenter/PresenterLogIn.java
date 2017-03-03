package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.UI.LogInActivity;
import com.example.alec.phase_05.Shared.model.Game;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLogIn extends Presenter implements IPresenterLogIn {
    private ILogInListener listener;

    public PresenterLogIn(ILogInListener listener) {
        this.listener = listener;
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public boolean logIn(String username, String password) {
        return Facade.getInstance().login(username, password);
    }

    @Override
    public boolean register(String username, String password) {
        return Facade.getInstance().registerUser(username, password);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(!(o instanceof UpdateIndicator)) {
            throw new IllegalArgumentException("object passed to update() must be of type UpdateIndicator");
        }
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

    @Override
    public void update(UpdateIndicator u) {

    }
}
