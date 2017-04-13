package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Presenter.IGameStationListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterGameStation;
import com.example.alec.phase_05.Client.Presenter.PresenterGameStation;
import com.example.alec.phase_05.Client.ServerProxy;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Server.model.ServerModel;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;

import java.util.List;

public class TestGameStationActivity extends Activity implements IGameStationListener {
    private Button mCreateGameButton, mJoinGameButton;
    private Button mButtonDialogRed, mButtonDialogBlue, mButtonDialogYellow, mButtonDialogGreen, mButtonDialogBlack;
    private View selectedColor = null;
    private int selectedGameID = -1;
    private IPresenterGameStation presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_station);
        presenter = new PresenterGameStation(this);
        System.out.println("inside test "+ ClientModel.getInstance().getPlayerPoints());
        presenter.createGame("red", "Test Game", 2);
    }

    private View newGameColorSelect(View color, View selectedColor, View mView)
    {
        selectedColor.setBackgroundColor(Color.TRANSPARENT);
        selectedColor = color;
        selectedColor.setBackgroundColor(Color.parseColor("#FFFFFF"));

        return selectedColor;
    }

    @Override
    public void hideRed(boolean visible) {

    }

    @Override
    public void hideGreen(boolean visible) {

    }

    @Override
    public void hideBlue(boolean visible) {

    }

    @Override
    public void hideYellow(boolean visible) {

    }

    @Override
    public void hideBlack(boolean visible) {

    }

    @Override
    public void updateGameList(List<GameDescription> gameDescriptions) {

    }

    @Override
    public void joinGameSuccess(boolean success) {

    }

    @Override
    public void reJoinGameSuccess(boolean success) {
    }

    @Override
    public void createGameSuccess(boolean success) {
        if(success) {
            System.out.println("There is success!!");
            Intent i = new Intent(TestGameStationActivity.this, TestLobbyActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Failed to create game", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getCurrentGameID() {
        return selectedGameID;
    }

}