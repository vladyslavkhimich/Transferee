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

import com.example.transferee.R;
import com.example.transferee.adapters.MatchAdapter;
import com.example.transferee.models.Match;

import java.util.ArrayList;

public class PlayerStatsFragment extends Fragment {

    private PlayerStatsViewModel playerStatsViewModel;
    public MatchAdapter MatchAdapter;
    public RecyclerView PlayerMatchesRecyclerView;

    public static PlayerStatsFragment newInstance() {
        return new PlayerStatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        playerStatsViewModel = new ViewModelProvider(this).get(PlayerStatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_player_stats, container, false);
        MatchAdapter = new MatchAdapter();
        playerStatsViewModel.getMatches().observe(getViewLifecycleOwner(), new Observer<ArrayList<Match>>() {
            @Override
            public void onChanged(ArrayList<Match> matches) {
                MatchAdapter.setMatches(matches);
            }
        });
        PlayerMatchesRecyclerView = (RecyclerView) root.findViewById(R.id.playerMatchesRecyclerView);
        PlayerMatchesRecyclerView.setAdapter(MatchAdapter);
        PlayerMatchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerStatsViewModel.class);
        // TODO: Use the ViewModel
    }*/

}