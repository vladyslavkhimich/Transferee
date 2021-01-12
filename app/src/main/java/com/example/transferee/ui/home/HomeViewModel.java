package com.example.transferee.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.Player;
import com.example.transferee.models.TopMarketValuePlayer;
import com.example.transferee.models.TopRatedPlayer;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<TopRatedPlayer>> TopRatedPlayers;
    MutableLiveData<ArrayList<TopMarketValuePlayer>> TopMarketValuePlayers;

    public HomeViewModel() {
        TopRatedPlayers = new MutableLiveData<>();
        TopMarketValuePlayers = new MutableLiveData<>();
        generateTopRatedPlayers();
        generateTopMarketValuePlayers();
    }

    private void generateTopRatedPlayers() {
        ArrayList<TopRatedPlayer> topRatedPlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club ManchesterCity = new Club("Manchester City", R.drawable.manchestercity);
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), 8.02, ManchesterUnited));
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.marcus, "Marcus Rashford"), 7.52, ManchesterUnited));
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.kevin, "Kevin De Bruyne"), 7.13, ManchesterCity));
        TopRatedPlayers.setValue(topRatedPlayers);
    }

    private void generateTopMarketValuePlayers() {
        ArrayList<TopMarketValuePlayer> topMarketValuePlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club ManchesterCity = new Club("Manchester City", R.drawable.manchestercity);
        topMarketValuePlayers.add(new TopMarketValuePlayer(new Player(R.drawable.kevin, "Kevin De Bruyne"), 120, ManchesterCity));
        topMarketValuePlayers.add(new TopMarketValuePlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), 90, ManchesterUnited));
        topMarketValuePlayers.add(new TopMarketValuePlayer(new Player(R.drawable.marcus, "Marcus Rashford"), 80, ManchesterUnited));
        TopMarketValuePlayers.setValue(topMarketValuePlayers);
    }

    public MutableLiveData<ArrayList<TopRatedPlayer>> getTopRatedPlayers() {
        return TopRatedPlayers;
    }

    public MutableLiveData<ArrayList<TopMarketValuePlayer>> getTopMarketValuePlayers() {
        return TopMarketValuePlayers;
    }
}