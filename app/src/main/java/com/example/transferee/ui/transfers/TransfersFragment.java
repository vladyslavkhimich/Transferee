package com.example.transferee.ui.transfers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferee.R;
import com.example.transferee.adapters.TransferAdapter;
import com.example.transferee.models.Transfer;

import java.util.ArrayList;

public class TransfersFragment extends Fragment {

    private TransfersViewModel transfersViewModel;
    public TransferAdapter TransferAdapter;
    public RecyclerView TransferRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transfersViewModel =
                new ViewModelProvider(this).get(TransfersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transfers, container, false);
        TransferAdapter = new TransferAdapter();
        transfersViewModel.getTransfers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Transfer>>() {
            @Override
            public void onChanged(ArrayList<Transfer> transfers) {
                TransferAdapter.setTransfers(transfers);
            }
        });
        TransferRecyclerView = (RecyclerView) root.findViewById(R.id.latestTransfersRecyclerView);
        TransferRecyclerView.setAdapter(TransferAdapter);
        TransferRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}