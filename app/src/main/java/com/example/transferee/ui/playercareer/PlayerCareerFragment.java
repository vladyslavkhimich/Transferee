package com.example.transferee.ui.playercareer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transferee.R;

public class PlayerCareerFragment extends Fragment {

    private PlayerCareerViewModel mViewModel;

    public static PlayerCareerFragment newInstance() {
        return new PlayerCareerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_career, container, false);
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerCareerViewModel.class);
        // TODO: Use the ViewModel
    }*/

}