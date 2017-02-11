package com.example.alec.phase_05.Client.Presenter;

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
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void logIn(String username, String password) {

    }

    @Override
    public void register(String username, String password) {

    }
}
