package com.example.transferee.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.models.Club;
import com.example.transferee.models.Player;
import com.example.transferee.models.TopMarketValuePlayer;
import com.example.transferee.models.TopRatedPlayer;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.TopRatedPlayersPOJO;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<TopRatedPlayersPOJO>> TopRatedPlayers = new MutableLiveData<>();
    MutableLiveData<ArrayList<TopMarketValuePlayer>> TopMarketValuePlayers;

    public HomeViewModel() {
        TopMarketValuePlayers = new MutableLiveData<>();
        //generateTopRatedPlayers();
        generateTopMarketValuePlayers();
    }

    private void generateTopRatedPlayers() {
        ArrayList<TopRatedPlayer> topRatedPlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club ManchesterCity = new Club("Manchester City", R.drawable.manchestercity);
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), 8.02, ManchesterUnited));
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.marcus, "Marcus Rashford"), 7.52, ManchesterUnited));
        topRatedPlayers.add(new TopRatedPlayer(new Player(R.drawable.kevin, "Kevin De Bruyne"), 7.13, ManchesterCity));
        //TopRatedPlayers.setValue(topRatedPlayers);
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

    public MutableLiveData<ArrayList<TopRatedPlayersPOJO>> getTopRatedPlayers() {
        Log.v("GettingPlayers", "true");
        return TopRatedPlayers;
    }

    public MutableLiveData<ArrayList<TopMarketValuePlayer>> getTopMarketValuePlayers() {
        return TopMarketValuePlayers;
    }

    public void setTopRatedPlayers(ArrayList<TopRatedPlayersPOJO> topRatedPlayers) {
        TopRatedPlayers.setValue(topRatedPlayers);
    }

    public void setTopRatedPlayersVoid() {
        PlayerRepository.getInstance().getTopRatedPlayersFromServer(new CallbackCustom<List<TopRatedPlayersPOJO>>() {
            @Override
            public void next(List<TopRatedPlayersPOJO> result) {
                Log.v("result", result.toString());
                setTopRatedPlayers(new ArrayList<TopRatedPlayersPOJO>(result));
            }
        });
    }
}