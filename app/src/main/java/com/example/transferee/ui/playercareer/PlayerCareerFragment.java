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

import com.example.transferee.PlayerActivity;
import com.example.transferee.R;
import com.example.transferee.adapters.PlayerTransferAdapter;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.models.PlayerTransfer;
import com.example.transferee.web.pojo.PlayerTransfersPOJO;

import java.util.ArrayList;

public class PlayerCareerFragment extends Fragment {

    private PlayerCareerViewModel playerCareerViewModel;
    public PlayerTransferAdapter PlayerTransferAdapter;
    public RecyclerViewEmptySupport PlayerTransfersRecyclerView;

    public static PlayerCareerFragment newInstance() {
        return new PlayerCareerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_player_career, container, false);
        PlayerTransferAdapter = new PlayerTransferAdapter();
        PlayerTransfersRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.playerTransfersRecyclerView);
        PlayerTransfersRecyclerView.setAdapter(PlayerTransferAdapter);
        PlayerTransfersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PlayerTransfersRecyclerView.setEmptyView(root.findViewById(R.id.playerTransfersEmpty));
        playerCareerViewModel.getPlayerTransfers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PlayerTransfersPOJO>>() {
            @Override
            public void onChanged(ArrayList<PlayerTransfersPOJO> playerTransfers) {
                PlayerTransferAdapter.setPlayerTransfers(playerTransfers);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerCareerViewModel = new ViewModelProvider(this).get(PlayerCareerViewModel.class);
        playerCareerViewModel.setPlayerTransfersVoid(((PlayerActivity)getActivity()).getPlayerID());
    }
}