package com.example.transferee.ui.transfers;

import android.os.Bundle;
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
import com.example.transferee.adapters.TransferAdapter;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.models.Transfer;
import com.example.transferee.web.pojo.LatestTransfersPOJO;

import java.util.ArrayList;

public class TransfersFragment extends Fragment {

    private TransfersViewModel transfersViewModel;
    public TransferAdapter TransferAdapter;
    public RecyclerViewEmptySupport TransferRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transfers, container, false);
        TransferAdapter = new TransferAdapter();
        TransferRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.latestTransfersRecyclerView);
        TransferRecyclerView.setAdapter(TransferAdapter);
        TransferRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TransferRecyclerView.setEmptyView(root.findViewById(R.id.latestTransfersEmpty));
        transfersViewModel.getTransfers().observe(getViewLifecycleOwner(), new Observer<ArrayList<LatestTransfersPOJO>>() {
            @Override
            public void onChanged(ArrayList<LatestTransfersPOJO> transfers) {
                TransferAdapter.setLatestTransfersPOJO(transfers);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transfersViewModel =
                new ViewModelProvider(this).get(TransfersViewModel.class);
        transfersViewModel.setTransfersVoid();
    }
}