package com.example.transferee.ui.home;

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
import com.example.transferee.web.pojo.TopMarketValuePlayersPOJO;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<TopRatedPlayersPOJO>> TopRatedPlayers = new MutableLiveData<>();
    MutableLiveData<ArrayList<TopMarketValuePlayersPOJO>> TopMarketValuePlayers = new MutableLiveData<>();

    public HomeViewModel() {
        //generateTopRatedPlayers();
        //generateTopMarketValuePlayers();
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
        //TopMarketValuePlayers.setValue(topMarketValuePlayers);
    }

    public MutableLiveData<ArrayList<TopRatedPlayersPOJO>> getTopRatedPlayers() {
        return TopRatedPlayers;
    }

    public void setTopRatedPlayers(ArrayList<TopRatedPlayersPOJO> topRatedPlayers) {
        TopRatedPlayers.setValue(topRatedPlayers);
    }

    public void setTopRatedPlayersVoid() {
        PlayerRepository.getInstance().getTopRatedPlayersFromServer(new CallbackCustom<List<TopRatedPlayersPOJO>>() {
            @Override
            public void next(List<TopRatedPlayersPOJO> result) {
                if (result != null)
                    setTopRatedPlayers(new ArrayList<TopRatedPlayersPOJO>(result));
            }
        });
    }

    public MutableLiveData<ArrayList<TopMarketValuePlayersPOJO>> getTopMarketValuePlayers() {
        return TopMarketValuePlayers;
    }

    public void setTopMarketValuePlayers(ArrayList<TopMarketValuePlayersPOJO> topMarketValuePlayers) {
        TopMarketValuePlayers.setValue(topMarketValuePlayers);
    }

    public void setTopMarketValuePlayersVoid() {
        PlayerRepository.getInstance().getTopMarketValuePlayersFromServer(new CallbackCustom<List<TopMarketValuePlayersPOJO>>() {
            @Override
            public void next(List<TopMarketValuePlayersPOJO> result) {
                if (result != null)
                    setTopMarketValuePlayers(new ArrayList<TopMarketValuePlayersPOJO>(result));
            }
        });
    }
}