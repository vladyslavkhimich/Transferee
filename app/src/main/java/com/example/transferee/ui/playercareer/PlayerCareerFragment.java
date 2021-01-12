package com.example.transferee.ui.playercareer;

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
import com.example.transferee.adapters.PlayerTransferAdapter;
import com.example.transferee.models.PlayerTransfer;

import java.util.ArrayList;

public class PlayerCareerFragment extends Fragment {

    private PlayerCareerViewModel playerCareerViewModel;
    public PlayerTransferAdapter PlayerTransferAdapter;
    public RecyclerView PlayerTransfersRecyclerView;

    public static PlayerCareerFragment newInstance() {
        return new PlayerCareerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        playerCareerViewModel = new ViewModelProvider(this).get(PlayerCareerViewModel.class);
        View root =  inflater.inflate(R.layout.fragment_player_career, container, false);
        PlayerTransferAdapter = new PlayerTransferAdapter();
        playerCareerViewModel.getPlayerTransfers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PlayerTransfer>>() {
            @Override
            public void onChanged(ArrayList<PlayerTransfer> playerTransfers) {
                PlayerTransferAdapter.setPlayerTransfers(playerTransfers);
            }
        });
        PlayerTransfersRecyclerView = (RecyclerView) root.findViewById(R.id.playerTransfersRecyclerView);
        PlayerTransfersRecyclerView.setAdapter(PlayerTransferAdapter);
        PlayerTransfersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PlayerCareerViewModel.class);
        // TODO: Use the ViewModel
    }*/

}