package com.example.transferee.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.R;
import com.example.transferee.adapters.TopMarketValuePlayerAdapter;
import com.example.transferee.adapters.TopRatedPlayerAdapter;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.models.TopMarketValuePlayer;
import com.example.transferee.models.TopRatedPlayer;
import com.example.transferee.web.pojo.TopMarketValuePlayersPOJO;
import com.example.transferee.web.pojo.TopRatedPlayersPOJO;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public TopRatedPlayerAdapter TopRatedPlayerAdapter;
    public RecyclerViewEmptySupport TopRatedPlayersRecyclerView;
    public TopMarketValuePlayerAdapter TopMarketValuePlayerAdapter;
    public RecyclerViewEmptySupport TopMarketValuePlayersRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TopRatedPlayerAdapter = new TopRatedPlayerAdapter();
        homeViewModel.getTopRatedPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<TopRatedPlayersPOJO>>() {
            @Override
            public void onChanged(ArrayList<TopRatedPlayersPOJO> topRatedPlayers) {
                TopRatedPlayerAdapter.setTopRatedPlayersPOJO(topRatedPlayers);
            }
        });
        TopRatedPlayersRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.topRatedPlayersRecyclerView);
        TopRatedPlayersRecyclerView.setAdapter(TopRatedPlayerAdapter);
        TopRatedPlayersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TopRatedPlayersRecyclerView.setEmptyView(root.findViewById(R.id.topRatedPlayersEmpty));
        TopMarketValuePlayerAdapter = new TopMarketValuePlayerAdapter();
        homeViewModel.getTopMarketValuePlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<TopMarketValuePlayersPOJO>>() {
            @Override
            public void onChanged(ArrayList<TopMarketValuePlayersPOJO> topMarketValuePlayers) {
                TopMarketValuePlayerAdapter.setTopMarketValuePlayers(topMarketValuePlayers);
            }
        });
        TopMarketValuePlayersRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.topMarketValuePlayersRecyclerView);
        TopMarketValuePlayersRecyclerView.setAdapter(TopMarketValuePlayerAdapter);
        TopMarketValuePlayersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TopMarketValuePlayersRecyclerView.setEmptyView(root.findViewById(R.id.topMarketValuePlayersEmpty));
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setTopRatedPlayersVoid();
        homeViewModel.setTopMarketValuePlayersVoid();
    }
}