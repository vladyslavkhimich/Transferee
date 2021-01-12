package com.example.transferee.ui.playermarket;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.PriceChange;

import java.util.ArrayList;
import java.util.Date;

public class PlayerMarketViewModel extends ViewModel {
    MutableLiveData<ArrayList<PriceChange>> PriceChanges;

    public PlayerMarketViewModel() {
        PriceChanges = new MutableLiveData<>();
        generatePriceChanges();
    }

    public void generatePriceChanges() {
        ArrayList<PriceChange> priceChanges = new ArrayList<>();
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        priceChanges.add(new PriceChange(80.0, 90.0, ManchesterUnited, new Date(120, 11, 17)));
        priceChanges.add(new PriceChange(70.0, 80.0, ManchesterUnited, new Date(120, 6, 5)));
        PriceChanges.setValue(priceChanges);
    }

    public MutableLiveData<ArrayList<PriceChange>> getPriceChanges() {
        return PriceChanges;
    }
}