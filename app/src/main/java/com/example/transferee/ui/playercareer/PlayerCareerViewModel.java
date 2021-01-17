package com.example.transferee.ui.playercareer;

import android.text.format.DateUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.models.Club;
import com.example.transferee.models.PlayerTransfer;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.PlayerTransfersPOJO;
import com.example.transferee.web.pojo.response.PlayerCareerResponse;

import java.util.ArrayList;
import java.util.Date;

public class PlayerCareerViewModel extends ViewModel {
    MutableLiveData<ArrayList<PlayerTransfersPOJO>> PlayerTransfers = new MutableLiveData<>();

    public PlayerCareerViewModel() {

    }

    public void generatePlayerTransfers() {
        ArrayList<PlayerTransfer> playerTransfers = new ArrayList<>();
        Club Sporting = new Club("Sporting", R.drawable.sporting);
        Club ManchesterUnited = new Club("Manchester United", R.drawable.manchesterunitedsmall);
        Club Sampdoria = new Club("Sampdoria", R.drawable.sampdoria);
        playerTransfers.add(new PlayerTransfer(new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS), Sporting, ManchesterUnited, 60.0, new Date(120, 0, 1), new Date(123, 6, 1), 90.0));
        playerTransfers.add(new PlayerTransfer(new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS), Sampdoria, Sporting, 7.5, new Date(120, 0, 1), new Date(123, 6, 1), 9.5));
        //PlayerTransfers.setValue(playerTransfers);
    }

    public MutableLiveData<ArrayList<PlayerTransfersPOJO>> getPlayerTransfers() {
        return PlayerTransfers;
    }

    public void setPlayerTransfers(ArrayList<PlayerTransfersPOJO> playerTransfers) {
        PlayerTransfers.setValue(playerTransfers);
    }

    public void setPlayerTransfersVoid(int id) {
        PlayerRepository.getInstance().getPlayerCareerByIdFromServer(new CallbackCustom<PlayerCareerResponse>() {
            @Override
            public void next(PlayerCareerResponse result) {
                if (result != null)
                    setPlayerTransfers(new ArrayList<>(result.getPlayerTransfersPOJO()));
            }
        }, id);
    }

}