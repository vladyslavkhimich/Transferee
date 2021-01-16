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
import android.widget.TextView;

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.adapters.PlayerPriceChangesAdapter;
import com.example.transferee.helpers.DateHelper;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.helpers.StringHelper;
import com.example.transferee.models.PriceChange;
import com.example.transferee.web.pojo.PlayerMarketPOJO;

import java.text.ParseException;
import java.util.ArrayList;

public class PlayerMarketFragment extends Fragment {

    private PlayerMarketViewModel playerMarketViewModel;
    public PlayerPriceChangesAdapter PlayerPriceChangesAdapter;
    public RecyclerViewEmptySupport PriceChangesRecyclerView;
    public TextView PlayerCurrentValueTextView;
    public TextView LastPriceChangeDateTextView;
    public TextView WorldwidePriceTextView;
    public TextView CountryPriceTextView;

    public static PlayerMarketFragment newInstance() {
        return new PlayerMarketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_player_market, container, false);
        PlayerCurrentValueTextView = (TextView) root.findViewById(R.id.playerCurrentValueTextView);
        LastPriceChangeDateTextView = (TextView) root.findViewById(R.id.lastPriceChangeTextView);
        WorldwidePriceTextView = (TextView) root.findViewById(R.id.worldwidePriceTextView);
        CountryPriceTextView = (TextView) root.findViewById(R.id.countryPriceTextView);
        PlayerPriceChangesAdapter = new PlayerPriceChangesAdapter();
        PriceChangesRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.priceChangesRecyclerView);
        PriceChangesRecyclerView.setAdapter(PlayerPriceChangesAdapter);
        PriceChangesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PriceChangesRecyclerView.setEmptyView(root.findViewById(R.id.playerPriceChangesEmpty));
        playerMarketViewModel.getPlayerMarket().observe(getViewLifecycleOwner(), new Observer<PlayerMarketPOJO>() {
            @Override
            public void onChanged(PlayerMarketPOJO playerMarketPOJO) {
                try {
                    setTextViews(playerMarketPOJO);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                PlayerPriceChangesAdapter.setPriceChanges(new ArrayList<>(playerMarketPOJO.getPriceChangesPOJO()));
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerMarketViewModel = new ViewModelProvider(this).get(PlayerMarketViewModel.class);
        playerMarketViewModel.setPlayerMarketVoid(((PlayerActivity)getActivity()).getPlayerID());
    }

    public void setTextViews(PlayerMarketPOJO playerMarketPOJO) throws ParseException {
        if (playerMarketPOJO.getCurrentPrice() > 10.0) {
            int currentPriceInteger = (int) playerMarketPOJO.getCurrentPrice();
            PlayerCurrentValueTextView.setText(getContext().getString(R.string.market_value_formatted_integer, currentPriceInteger));
        }
        else
            PlayerCurrentValueTextView.setText(getContext().getString(R.string.market_value_formatted_float, playerMarketPOJO.getCurrentPrice()));
        LastPriceChangeDateTextView.setText(DateHelper.getStringDateWithDay(playerMarketPOJO.getLastPriceChange()));
        WorldwidePriceTextView.setText(StringHelper.getOrdinalFromInteger(playerMarketPOJO.getWorldwidePrice()));
        CountryPriceTextView.setText(StringHelper.getOrdinalFromInteger(playerMarketPOJO.getCountryPrice()));
    }
}