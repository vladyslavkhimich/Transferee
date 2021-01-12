package com.example.transferee.ui.playeroverview;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transferee.R;

public class PlayerOverviewFragment extends Fragment {

    private PlayerOverviewViewModel mViewModel;

    public static PlayerOverviewFragment newInstance() {
        return new PlayerOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_overview, container, false);
    }

    /*@Override*/
    /*public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerOverviewViewModel.class);
        // TODO: Use the ViewModel
    }*/

}