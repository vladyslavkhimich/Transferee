package com.example.transferee.ui.favorites;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.FavoritePlayer;
import com.example.transferee.models.Player;

import java.util.ArrayList;

public class FavoritesViewModel extends ViewModel {
    MutableLiveData<ArrayList<FavoritePlayer>> FavoritePlayers;

    public FavoritesViewModel() {
        FavoritePlayers = new MutableLiveData<>();
        generateFavoritePlayers();
    }

    public void generateFavoritePlayers() {
        ArrayList<FavoritePlayer> favoritePlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        favoritePlayers.add(new FavoritePlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), ManchesterUnited));
        favoritePlayers.add(new FavoritePlayer(new Player(R.drawable.marcus, "Marcus Rashford"), ManchesterUnited));
        FavoritePlayers.setValue(favoritePlayers);
    }

    public MutableLiveData<ArrayList<FavoritePlayer>> getFavoritePlayers() {
        return FavoritePlayers;
    }
}