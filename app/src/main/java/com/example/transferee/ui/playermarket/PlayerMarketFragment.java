package com.example.transferee.ui.playermarket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transferee.R;
import com.example.transferee.adapters.PlayerPriceChangesAdapter;
import com.example.transferee.models.PriceChange;

import java.util.ArrayList;

public class PlayerMarketFragment extends Fragment {

    private PlayerMarketViewModel playerMarketViewModel;
    public PlayerPriceChangesAdapter PlayerPriceChangesAdapter;
    public RecyclerView PriceChangesRecyclerView;

    public static PlayerMarketFragment newInstance() {
        return new PlayerMarketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        playerMarketViewModel = new ViewModelProvider(this).get(PlayerMarketViewModel.class);
        View root =  inflater.inflate(R.layout.fragment_player_market, container, false);
        PlayerPriceChangesAdapter = new PlayerPriceChangesAdapter();
        playerMarketViewModel.getPriceChanges().observe(getViewLifecycleOwner(), new Observer<ArrayList<PriceChange>>() {
            @Override
            public void onChanged(ArrayList<PriceChange> priceChanges) {
                PlayerPriceChangesAdapter.setPriceChanges(priceChanges);
            }
        });
        PriceChangesRecyclerView = (RecyclerView) root.findViewById(R.id.priceChangesRecyclerView);
        PriceChangesRecyclerView.setAdapter(PlayerPriceChangesAdapter);
        PriceChangesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerMarketViewModel.class);
        // TODO: Use the ViewModel
    }*/

}