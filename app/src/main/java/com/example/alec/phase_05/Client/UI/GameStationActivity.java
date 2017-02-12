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
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.alec.phase_05.Client.Model.ListItem;
import com.example.alec.phase_05.Client.Model.DerpData;
import com.example.alec.phase_05.Client.Presenter.IGameStationListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterGameStation;
import com.example.alec.phase_05.Client.Presenter.PresenterGameStation;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.GameDescription;

import java.util.List;

public class GameStationActivity extends Activity implements IGameStationListener {
    private RecyclerView mGameRecView;
    private DerpAdapter mAdapter;
    private Button mCreateGameButton, mJoinGameButton;
    private Button mButtonDialogRed, mButtonDialogBlue, mButtonDialogYellow, mButtonDialogGreen, mButtonDialogBlack;
    private View selectedColor = null;
    private IPresenterGameStation presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_station);

        mGameRecView = (RecyclerView) findViewById(R.id.rec_game_list);
        mGameRecView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new DerpAdapter(DerpData.getListData(), this);
        mGameRecView.setAdapter(mAdapter);

        mCreateGameButton = (Button) findViewById(R.id.create_game_button);
        mCreateGameButton = (Button) findViewById(R.id.create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameStationActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_create_game, null);
                final EditText mGameName = (EditText) mView.findViewById(R.id.new_game_edit_text);
                final NumberPicker mNoPicker = (NumberPicker) mView.findViewById(R.id.new_game_number_picker);
                mButtonDialogRed = (Button) mView.findViewById(R.id.new_game_button_red);
                mButtonDialogBlue = (Button) mView.findViewById(R.id.new_game_button_blue);
                mButtonDialogYellow = (Button) mView.findViewById(R.id.new_game_button_yellow);
                mButtonDialogGreen = (Button) mView.findViewById(R.id.new_game_button_green);
                mButtonDialogBlack = (Button) mView.findViewById(R.id.new_game_button_black);
                final Button mButtonDialogCancel = (Button) mView.findViewById(R.id.new_game_button_cancel);
                final Button mButtonDialogOk = (Button) mView.findViewById(R.id.new_game_button_ok);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mNoPicker.setMaxValue(5);
                mNoPicker.setMinValue(2);
                mNoPicker.setWrapSelectorWheel(false);
                selectedColor = (View) mView.findViewById(R.id.new_game_container_red);
                selectedColor.setBackgroundColor(Color.parseColor("#FFFFFF"));

                mButtonDialogRed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = newGameColorSelect(mView.findViewById(R.id.new_game_container_red), selectedColor, mView);
                    }
                });

                mButtonDialogBlue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = newGameColorSelect(mView.findViewById(R.id.new_game_container_blue), selectedColor, mView);
                    }
                });

                mButtonDialogYellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = newGameColorSelect(mView.findViewById(R.id.new_game_container_yellow), selectedColor, mView);
                    }
                });

                mButtonDialogGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = newGameColorSelect(mView.findViewById(R.id.new_game_container_green), selectedColor, mView);
                    }
                });

                mButtonDialogBlack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedColor = newGameColorSelect(mView.findViewById(R.id.new_game_container_black), selectedColor, mView);
                    }
                });

                mButtonDialogCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                mButtonDialogOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent i = new Intent(GameStationActivity.this, LobbyActivity.class);
                        startActivity(i);
                    }
                });
            }
        });

        mJoinGameButton = (Button) findViewById(R.id.join_game_button);
        mJoinGameButton.setEnabled(false);
        mJoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameStationActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_join_game, null);

                final Button mButtonDialogRed = (Button) mView.findViewById(R.id.join_game_button_red);
                final Button mButtonDialogBlue = (Button) mView.findViewById(R.id.join_game_button_blue);
                final Button mButtonDialogYellow = (Button) mView.findViewById(R.id.join_game_button_yellow);
                final Button mButtonDialogGreen = (Button) mView.findViewById(R.id.join_game_button_green);
                final Button mButtonDialogBlack = (Button) mView.findViewById(R.id.join_game_button_black);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mButtonDialogRed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinGame(mView.findViewById(R.id.join_game_button_red), mView, dialog);
                    }
                });

                mButtonDialogBlue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinGame(mView.findViewById(R.id.join_game_button_blue), mView, dialog);
                    }
                });

                mButtonDialogYellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinGame(mView.findViewById(R.id.join_game_button_yellow), mView, dialog);
                    }
                });

                mButtonDialogGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinGame(mView.findViewById(R.id.join_game_button_green), mView, dialog);
                    }
                });

                mButtonDialogBlack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinGame(mView.findViewById(R.id.join_game_button_black), mView, dialog);
                    }
                });
            }
        });

        presenter = new PresenterGameStation(this);
    }


    public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {
        private List<ListItem> listData;
        private LayoutInflater inflater;
        private View selected = null;

        public void cancelSelected()
        {
            selected.setBackgroundColor(Color.TRANSPARENT);
            selected = null;
        }

        public DerpAdapter(List<ListItem> listData, Context c)
        {
            this.inflater = LayoutInflater.from(c);
            this.listData = listData;
        }


        @Override
        public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_item, parent, false);
            return new DerpHolder(v);
        }

        @Override
        public void onBindViewHolder(DerpHolder holder, int position) {
            ListItem item = listData.get(position);
            holder.title.setText(item.getTitle());
            holder.icon.setImageResource(item.getImageResId());
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView title;
            private ImageView icon;
            private View container;


            public DerpHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                title = (TextView)itemView.findViewById(R.id.lbl_item_text);
                icon = (ImageView)itemView.findViewById(R.id.im_item_icon);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {
                if(selected == null)
                {
                    mJoinGameButton.setEnabled(true);
                }
                else
                {
                    selected.setBackgroundColor(Color.TRANSPARENT);
                }

                selected = view;
                view.setBackgroundColor(Color.parseColor("#8866B2FF"));
            }
        }

    }

    private View newGameColorSelect(View color, View selectedColor, View mView)
    {
        selectedColor.setBackgroundColor(Color.TRANSPARENT);
        selectedColor = color;
        selectedColor.setBackgroundColor(Color.parseColor("#FFFFFF"));

        return selectedColor;
    }

    private void joinGame(View colorView, View mView, AlertDialog dialog)
    {
        String color = null;

        if(mView.findViewById(R.id.join_game_button_red) == colorView)
        {
            color = "red";
        }
        if(mView.findViewById(R.id.join_game_button_blue) == colorView)
        {
            color = "blue";
        }
        if(mView.findViewById(R.id.join_game_button_yellow) == colorView)
        {
            color = "yellow";
        }
        if(mView.findViewById(R.id.join_game_button_green) == colorView)
        {
            color = "green";
        }
        if(mView.findViewById(R.id.join_game_button_black) == colorView)
        {
            color = "black";
        }

        Intent i = new Intent(GameStationActivity.this, LobbyActivity.class);
        mAdapter.cancelSelected();
        mJoinGameButton.setEnabled(false);
        dialog.dismiss();
        startActivity(i);
    }

    @Override
    public void hideRed(boolean visible) {
        if(visible)
        {
            mButtonDialogRed.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideGreen(boolean visible) {
        if(visible)
        {
            mButtonDialogGreen.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideBlue(boolean visible) {
        if(visible)
        {
            mButtonDialogBlue.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideYellow(boolean visible) {
        if(visible)
        {
            mButtonDialogYellow.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideBlack(boolean visible) {
        if(visible)
        {
            mButtonDialogBlack.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateGameList(List<GameDescription> gameDescriptions) {

    }

    @Override
    public void joinGameSuccess(boolean success) {
        if(success) {
            Intent i = new Intent(GameStationActivity.this, LobbyActivity.class);
            mAdapter.cancelSelected();
            mJoinGameButton.setEnabled(false);
            startActivity(i);
        } else {

        }
    }

    @Override
    public void createGameSuccess(boolean success) {
        if(success) {
            Intent i = new Intent(GameStationActivity.this, LobbyActivity.class);
            mAdapter.cancelSelected();
            mJoinGameButton.setEnabled(false);
            startActivity(i);
        } else {

        }
    }
}