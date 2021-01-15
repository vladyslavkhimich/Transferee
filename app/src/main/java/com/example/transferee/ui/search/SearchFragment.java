package com.example.transferee.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.transferee.R;
import com.example.transferee.adapters.SearchedPlayerAdapter;
import com.example.transferee.helpers.RecyclerViewEmptySupport;
import com.example.transferee.web.pojo.FoundPlayersPOJO;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    public SearchedPlayerAdapter SearchedPlayerAdapter;
    public RecyclerViewEmptySupport SearchResultsRecyclerView;
    public EditText SearchPlayersEditText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        SearchedPlayerAdapter = new SearchedPlayerAdapter();
        searchViewModel.getSearchedPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<FoundPlayersPOJO>>() {
            @Override
            public void onChanged(ArrayList<FoundPlayersPOJO> searchedPlayers) {
                SearchedPlayerAdapter.setFoundPlayers(searchedPlayers);
            }
        });
        SearchResultsRecyclerView = (RecyclerViewEmptySupport) root.findViewById(R.id.searchResultsRecyclerView);
        SearchResultsRecyclerView.setAdapter(SearchedPlayerAdapter);
        SearchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchResultsRecyclerView.setEmptyView(root.findViewById(R.id.foundPlayersEmpty));
        SearchPlayersEditText = (EditText) root.findViewById(R.id.searchPlayersEditText);
        SearchPlayersEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2)
                    searchViewModel.setSearchedPlayersVoid(SearchPlayersEditText.getText().toString());
                else
                    searchViewModel.setSearchedPlayers(new ArrayList<FoundPlayersPOJO>());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }
}