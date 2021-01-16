package com.example.transferee.ui.playeroverview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.PlayerOverviewPOJO;

public class PlayerOverviewViewModel extends ViewModel {
    MutableLiveData<PlayerOverviewPOJO> PlayerOverview = new MutableLiveData<>();

    public PlayerOverviewViewModel() {

    }

    public void setPlayerOverview(PlayerOverviewPOJO playerOverview) {
        PlayerOverview.setValue(playerOverview);
    }

    public MutableLiveData<PlayerOverviewPOJO> getPlayerOverview() {
        return PlayerOverview;
    }

    public void setPlayerOverviewVoid(int id) {
        PlayerRepository.getInstance().getPlayerOverviewByIdFromServer(new CallbackCustom<PlayerOverviewPOJO>() {
            @Override
            public void next(PlayerOverviewPOJO result) {
               if (result != null)
                   setPlayerOverview(result);
            }
        }, id);
    }
}