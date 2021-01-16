package com.example.transferee.ui.playermarket;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.models.Club;
import com.example.transferee.models.PriceChange;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.PlayerMarketPOJO;

import java.util.ArrayList;
import java.util.Date;

public class PlayerMarketViewModel extends ViewModel {
    MutableLiveData<PlayerMarketPOJO> PlayerMarket = new MutableLiveData<>();

    public PlayerMarketViewModel() {

    }

    public void generatePriceChanges() {
        ArrayList<PriceChange> priceChanges = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        priceChanges.add(new PriceChange(80.0, 90.0, ManchesterUnited, new Date(120, 11, 17)));
        priceChanges.add(new PriceChange(70.0, 80.0, ManchesterUnited, new Date(120, 6, 5)));
        //PriceChanges.setValue(priceChanges);
    }

    public MutableLiveData<PlayerMarketPOJO> getPlayerMarket() {
        return PlayerMarket;
    }

    public void setPlayerMarket(PlayerMarketPOJO playerMarket) {
        PlayerMarket.setValue(playerMarket);
    }

    public void setPlayerMarketVoid(int id) {
        PlayerRepository.getInstance().getPlayerMarketByIdFromServer(new CallbackCustom<PlayerMarketPOJO>() {
            @Override
            public void next(PlayerMarketPOJO result) {
              if (result != null)
                  setPlayerMarket(result);
            }
        }, id);
    }
}