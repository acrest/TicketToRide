package com.example.alec.phase_05.Client.Presenter;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.Observable;
import java.util.Random;

/**
 * Created by samuel on 2/13/17.
 */

public class MockPresenterLobby implements IPresenterLobby {
    private static final long TIMER_DELAY = 1000;
    private ILobbyListener listener;

    public MockPresenterLobby(ILobbyListener listener) {
        this.listener = listener;
        startRandomUpdates();
    }

    @Override
    public void update(Observable observable, Object o) {
        randomlyUpdatePlayerNumbers();
    }

    public void randomlyUpdatePlayerNumbers() {
        Random random = new Random();
        int max = random.nextInt(9) + 1;
        int num = random.nextInt(max);
        listener.updateNumberOfPlayers(num, max);
    }

    private void startRandomUpdates() {
        new RandomUpdateTask().execute();
    }

    private class RandomUpdateTask extends AsyncTask<Void, Void, Void> {
        Runnable runnable;

        public RandomUpdateTask() {
            runnable = new Runnable() {
                @Override
                public void run() {
                    update(null, null);
                }
            };
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while(true) {
                try {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(runnable);
                    Thread.sleep(TIMER_DELAY);
                } catch(InterruptedException e) {}
            }
        }
    }
}
