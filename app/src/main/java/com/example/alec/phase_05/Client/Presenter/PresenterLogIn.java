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
    public void logIn(String username, String password) {
        System.out.println("in presenter login");
        Facade.getInstance().login(username, password);
    }

    @Override
    public void register(String username, String password) {
        Facade.getInstance().registerUser(username, password);
    }

    @Override
    public void update(UpdateIndicator u) {
        if(u.needUpdate(ClientModel.REGISTER_SUCCESS)){
            listener.onRegisterResults(true);
        }
        if(u.needUpdate(ClientModel.REGISTER_FAILURE)){
            listener.onRegisterResults(false);
        }
        if(u.needUpdate(ClientModel.LOGIN_SUCCESS)){
            listener.onLoginResults(true);
        }
        if(u.needUpdate(ClientModel.LOGIN_FAILURE)){
            listener.onLoginResults(false);
        }
    }
}
