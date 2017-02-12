package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.UI.LogInActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLogIn implements IPresenterLogIn {
    private ILogInListener listener;

    public PresenterLogIn(ILogInListener listener) {
        this.listener = listener;
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public void logIn(String username, String password) {
        Facade.getInstance().login(username, password);
    }

    @Override
    public void register(String username, String password) {
        Facade.getInstance().registerUser(username, password);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
