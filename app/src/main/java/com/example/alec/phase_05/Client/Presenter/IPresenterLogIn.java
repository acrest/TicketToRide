package com.example.alec.phase_05.Client.Presenter;

import java.util.Observer;

/**
 * Created by samuel on 2/11/17.
 */

public interface IPresenterLogIn extends IPresenter {
    void logIn(String username, String password);
    void register(String username, String password);
}
