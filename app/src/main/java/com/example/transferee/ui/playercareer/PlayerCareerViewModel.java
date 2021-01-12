package com.example.transferee.ui.playercareer;

import android.text.format.DateUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.models.Club;
import com.example.transferee.models.PlayerTransfer;

import java.util.ArrayList;
import java.util.Date;

public class PlayerCareerViewModel extends ViewModel {
    MutableLiveData<ArrayList<PlayerTransfer>> PlayerTransfers;

    public PlayerCareerViewModel() {
        PlayerTransfers = new MutableLiveData<>();
        generatePlayerTransfers();
    }

    public void generatePlayerTransfers() {
        ArrayList<PlayerTransfer> playerTransfers = new ArrayList<>();
        Club Sporting = new Club("Sporting", R.drawable.sporting);
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club Sampdoria = new Club("Sampdoria", R.drawable.sampdoria);
        playerTransfers.add(new PlayerTransfer(new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS), Sporting, ManchesterUnited, 60.0, new Date(120, 0, 1), new Date(123, 6, 1), 90.0));
        playerTransfers.add(new PlayerTransfer(new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS), Sampdoria, Sporting, 7.5, new Date(120, 0, 1), new Date(123, 6, 1), 9.5));
        PlayerTransfers.setValue(playerTransfers);
    }

    public MutableLiveData<ArrayList<PlayerTransfer>> getPlayerTransfers() {
        return PlayerTransfers;
    }
}