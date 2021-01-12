package com.example.transferee.ui.transfers;

import android.text.format.DateUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.Player;
import com.example.transferee.models.Transfer;

import java.util.ArrayList;
import java.util.Date;

public class TransfersViewModel extends ViewModel {
    MutableLiveData<ArrayList<Transfer>> Transfers;
    public TransfersViewModel() {
        Transfers = new MutableLiveData<>();
        generateTransfers();
    }

    private void generateTransfers() {
        ArrayList<Transfer> transfers = new ArrayList<>();
        Club Sporting = new Club("Sporting", R.drawable.sporting);
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club Wolfsburg = new Club("Wolfsburg", R.drawable.wolfsburg);
        Club ManchesterCity = new Club("Manchester City", R.drawable.manchestercity);
        transfers.add(new Transfer(new Player(R.drawable.bruno, "Bruno Fernandes"), new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS), Sporting, ManchesterUnited, 60.0, new Date(120, 0, 1), new Date(123, 6, 1), 90.0));
        transfers.add(new Transfer(new Player(R.drawable.kevin, "Kevin De Bruyne"), new Date(System.currentTimeMillis()), Wolfsburg, ManchesterCity, 75.0, new Date(120, 0, 1), new Date(123, 6, 1), 120.0));
        Transfers.setValue(transfers);
    }

    public MutableLiveData<ArrayList<Transfer>> getTransfers() {
        return Transfers;
    }
}