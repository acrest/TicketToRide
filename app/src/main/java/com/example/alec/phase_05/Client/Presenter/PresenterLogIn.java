package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Facade;

import java.util.Observable;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLogIn extends Presenter implements IPresenterLogIn {
    private ILogInListener listener;

    public PresenterLogIn(ILogInListener listener) {
        this.listener = listener;
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
    public void update(UpdateIndicator u) {

    }
}
