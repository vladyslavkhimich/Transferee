package com.example.transferee.ui.playerstats;

import android.text.format.DateUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.transferee.R;
import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.models.Club;
import com.example.transferee.models.Match;
import com.example.transferee.repository.PlayerRepository;
import com.example.transferee.web.pojo.response.PlayerStatsResponse;

import java.util.ArrayList;
import java.util.Date;

public class PlayerStatsViewModel extends ViewModel {
    MutableLiveData<PlayerStatsResponse> PlayerStats = new MutableLiveData<>();

    public PlayerStatsViewModel() {

    }

    public void generateMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        Club Everton = new Club("Everton", R.drawable.everton);
        Club Arsenal = new Club("Arsenal", R.drawable.arsenal);
        matches.add(new Match(new Date(System.currentTimeMillis()), Everton, 3, 1, true, 2, 1, "CAM", 50, 0, 90, 8.25));
        matches.add(new Match(new Date(System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS * 3), Arsenal, 0, 1, true, 0, 0, "CM", 0, 0, 75, 6.5));
        //Matches.setValue(matches);
    }

    public MutableLiveData<PlayerStatsResponse> getPlayerStats() {
        return PlayerStats;
    }

    public void setPlayerStats(PlayerStatsResponse playerStats) {
            PlayerStats.setValue(playerStats);
    }

    public void setPlayerStatsVoid(int id) {
        PlayerRepository.getInstance().getPlayerStatsByIdFromServer(new CallbackCustom<PlayerStatsResponse>() {
            @Override
            public void next(PlayerStatsResponse result) {
                if (result != null)
                    setPlayerStats(result);
            }
        }, id);
    }
}