package com.example.transferee.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.Country;
import com.example.transferee.models.Player;
import com.example.transferee.models.SearchedPlayer;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {
    MutableLiveData<ArrayList<SearchedPlayer>> SearchedPlayers;

    public SearchViewModel() {
        SearchedPlayers = new MutableLiveData<>();
        generateSearchedPlayers();
    }

    public void generateSearchedPlayers() {
        ArrayList<SearchedPlayer> searchedPlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club ManchesterCity = new Club("Manchester City", R.drawable.manchestercity);
        Country Portugal = new Country("Portugal", R.drawable.portugal);
        Country Belgium = new Country("Belgium", R.drawable.belgium);
        searchedPlayers.add(new SearchedPlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), ManchesterUnited, Portugal));
        searchedPlayers.add(new SearchedPlayer(new Player(R.drawable.kevin, "Kevin De Bruyne"), ManchesterCity, Belgium));
        SearchedPlayers.setValue(searchedPlayers);
    }

    public MutableLiveData<ArrayList<SearchedPlayer>> getSearchedPlayers() {
        return SearchedPlayers;
    }
}