package com.example.transferee.ui.search;

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
import com.example.transferee.adapters.SearchedPlayerAdapter;
import com.example.transferee.models.SearchedPlayer;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    public SearchedPlayerAdapter SearchedPlayerAdapter;
    public RecyclerView SearchResultsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        SearchedPlayerAdapter = new SearchedPlayerAdapter();
        searchViewModel.getSearchedPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<SearchedPlayer>>() {
            @Override
            public void onChanged(ArrayList<SearchedPlayer> searchedPlayers) {
                SearchedPlayerAdapter.setSearchedPlayers(searchedPlayers);
            }
        });
        SearchResultsRecyclerView = (RecyclerView) root.findViewById(R.id.searchResultsRecyclerView);
        SearchResultsRecyclerView.setAdapter(SearchedPlayerAdapter);
        SearchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}