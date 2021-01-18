package com.example.transferee.ui.playerstats;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.adapters.MatchAdapter;
import com.example.transferee.helpers.DoubleHelper;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.helpers.StringHelper;
import com.example.transferee.models.Match;
import com.example.transferee.web.pojo.response.PlayerStatsResponse;

import java.util.ArrayList;

public class PlayerStatsFragment extends Fragment {

    private PlayerStatsViewModel playerStatsViewModel;
    public MatchAdapter MatchAdapter;
    public RecyclerViewEmptySupport PlayerMatchesRecyclerView;
    public TextView LastMatchesPlayerGoalsTextView;
    public TextView LastMatchesPlayerAssistsTextView;
    public TextView LastMatchesPlayerRatingTextView;

    public static PlayerStatsFragment newInstance() {
        return new PlayerStatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_player_stats, container, false);
        LastMatchesPlayerGoalsTextView = (TextView) root.findViewById(R.id.lastMatchesPlayerGoalsTextView);
        LastMatchesPlayerAssistsTextView = (TextView) root.findViewById(R.id.lastMatchesPlayerAssistsTextView);
        LastMatchesPlayerRatingTextView = (TextView) root.findViewById(R.id.lastMatchesPlayerRatingTextView);
        MatchAdapter = new MatchAdapter();
        playerStatsViewModel.getPlayerStats().observe(getViewLifecycleOwner(), new Observer<PlayerStatsResponse>() {
            @Override
            public void onChanged(PlayerStatsResponse playerStatsResponse) {
                setPlayerStats(playerStatsResponse);
                MatchAdapter.setMatches(new ArrayList<>(playerStatsResponse.getMatchesPOJO()));
            }
        });
        PlayerMatchesRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.playerMatchesRecyclerView);
        PlayerMatchesRecyclerView.setAdapter(MatchAdapter);
        PlayerMatchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PlayerMatchesRecyclerView.setEmptyView(root.findViewById(R.id.playerMatchesEmpty));
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerStatsViewModel = new ViewModelProvider(this).get(PlayerStatsViewModel.class);
        playerStatsViewModel.setPlayerStatsVoid(((PlayerActivity)getActivity()).getPlayerID());
    }

    public void setPlayerStats(PlayerStatsResponse playerStatsResponse) {
        LastMatchesPlayerGoalsTextView.setText(StringHelper.getDashIfNumberIsZero(playerStatsResponse.getTotalGoals()));
        LastMatchesPlayerAssistsTextView.setText(StringHelper.getDashIfNumberIsZero(playerStatsResponse.getTotalAssists()));
        LastMatchesPlayerRatingTextView.setText(DoubleHelper.getDashIfNumberIsNullOrZero(playerStatsResponse.getTotalRating()));
    }

}