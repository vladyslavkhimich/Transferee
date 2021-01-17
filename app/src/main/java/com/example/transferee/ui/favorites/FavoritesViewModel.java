package com.example.transferee.ui.favorites;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.models.Club;
import com.example.transferee.models.FavoritePlayer;
import com.example.transferee.models.Player;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.PlayerPOJO;

import java.util.ArrayList;

public class FavoritesViewModel extends ViewModel {
    MutableLiveData<ArrayList<PlayerPOJO>> FavoritePlayers = new MutableLiveData<>();
    ArrayList<PlayerPOJO> FavoritePlayersArrayList = new ArrayList<>();

    public FavoritesViewModel() {
    }

    public void generateFavoritePlayers() {
        ArrayList<FavoritePlayer> favoritePlayers = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        favoritePlayers.add(new FavoritePlayer(new Player(R.drawable.bruno, "Bruno Fernandes"), ManchesterUnited));
        favoritePlayers.add(new FavoritePlayer(new Player(R.drawable.marcus, "Marcus Rashford"), ManchesterUnited));
        //FavoritePlayers.setValue(favoritePlayers);
    }

    public MutableLiveData<ArrayList<PlayerPOJO>> getFavoritePlayers() {
        return FavoritePlayers;
    }

    public void setFavoritePlayers() {
        FavoritePlayers.setValue(FavoritePlayersArrayList);
    }

    public ArrayList<PlayerPOJO> getFavoritePlayersArrayList() {
        return getFavoritePlayers().getValue();
    }

    public void addToFavoritePlayers(PlayerPOJO favoritePlayer) {
        FavoritePlayersArrayList.add(favoritePlayer);
        setFavoritePlayers();
    }

    public void setFavoritePlayersVoid(ArrayList<Integer> favoritePlayersIds) {
        for (Integer playerId : favoritePlayersIds) {
            PlayerRepository.getInstance().getPlayerByIdFromServer(new CallbackCustom<PlayerPOJO>() {
                @Override
                public void next(PlayerPOJO result) {
                    if (result != null)
                        addToFavoritePlayers(result);
                }
            }, playerId);
        }
    }
}