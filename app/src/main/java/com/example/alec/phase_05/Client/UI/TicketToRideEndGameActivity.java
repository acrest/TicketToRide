package com.example.alec.phase_05.Client.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alec.phase_05.Client.Presenter.IPresenterTicketToRideEndGame;
import com.example.alec.phase_05.Client.Presenter.ITicketToRideEndGameListener;
import com.example.alec.phase_05.Client.Presenter.PresenterTicketToRideEndGame;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TicketToRideEndGameActivity extends AppCompatActivity implements ITicketToRideEndGameListener {
    private IPresenterTicketToRideEndGame presenter;
    private ResultsAdapter mResultsAdapter;
    private TextView mWinner;
    private TextView mLongestRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_to_ride_end_game);

        presenter = new PresenterTicketToRideEndGame(this);

        RecyclerView mResults = (RecyclerView) findViewById(R.id.results_list);
        mWinner = (TextView) findViewById(R.id.results_winner_text);
        mLongestRoute = (TextView) findViewById(R.id.results_longest_route);

        mWinner.setText(presenter.getWinner());
        mLongestRoute.setText(presenter.getLongestRouteHolder());

        List<ResultEntry> entries = new ArrayList<>();
        Iterator<IPlayer> players = presenter.getPlayers();
        while(players.hasNext()) {
            entries.add(ResultEntry.createEntry(players.next()));
        }
        mResultsAdapter = new ResultsAdapter(entries, this);

        mResults.setLayoutManager(new LinearLayoutManager(this));
        mResults.setAdapter(mResultsAdapter);
    }

    private class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultHolder> {
        private List<ResultEntry> data;
        private LayoutInflater inflater;

        private ResultsAdapter(List<ResultEntry> data, Context context) {
            this.data = data;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ResultHolder(inflater.inflate(R.layout.results_entry, parent, false));
        }

        @Override
        public void onBindViewHolder(ResultHolder holder, int position) {
            ResultEntry entry = data.get(position);
            holder.name.setText(entry.getName());
            holder.points.setText(String.format(Locale.getDefault(), "%d", entry.getPoints()));
            holder.penalties.setText(String.format(Locale.getDefault(), "%d", entry.getPenalties()));
            holder.total.setText(String.format(Locale.getDefault(), "%d", entry.getTotal()));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        private int getPlayerIndex(String playerName) {
            for(int i = 0; i < data.size(); i++) {
                if(data.get(i).getName().equals(playerName)) {
                    return i;
                }
            }
            return -1;
        }

        private void addPlayerResult(IPlayer player) {
            data.add(ResultEntry.createEntry(player));
            notifyItemInserted(data.size() - 1);
        }

        class ResultHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView points;
            private TextView penalties;
            private TextView total;

            private ResultHolder(View itemView) {
                super(itemView);

                name = (TextView) findViewById(R.id.results_name);
                points = (TextView) findViewById(R.id.results_points);
                penalties = (TextView) findViewById(R.id.results_penalties);
                total = (TextView) findViewById(R.id.results_total);
            }
        }
    }

    private static class ResultEntry {
        private static ResultEntry createEntry(IPlayer player) {
            return new ResultEntry(player.getName(), player.getPoints(), 0, player.getPoints()); //TODO add penalties
        }

        private String name;
        private int points;
        private int penalties;
        private int total;

        private ResultEntry(String name, int points, int penalties, int total) {
            this.name = name;
            this.points = points;
            this.penalties = penalties;
            this.total = total;
        }

        public String getName() {
            return name;
        }

        public int getPoints() {
            return points;
        }

        public int getPenalties() {
            return penalties;
        }

        public int getTotal() {
            return total;
        }
    }
}
