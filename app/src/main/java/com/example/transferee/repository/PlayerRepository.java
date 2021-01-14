package com.example.transferee.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.WebService;
import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.example.transferee.web.pojo.response.TopRatedPlayersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerRepository {
    private static PlayerRepository instance = null;
    private static WebService WebService;
    private final MutableLiveData<ArrayList<TopRatedPlayersPOJO>> topRatedPlayersPOJO = new MutableLiveData<>();

    public PlayerRepository() {
        WebService = RetrofitService.getWebService();
    }

    public static PlayerRepository getInstance() {
        if (instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    public void getTopRatedPlayersFromServer(CallbackCustom<List<TopRatedPlayersPOJO>> callback) {
        WebService.getTopRatedPlayers().enqueue(new Callback<TopRatedPlayersResponse>() {
            @Override
            public void onResponse(Call<TopRatedPlayersResponse> call, Response<TopRatedPlayersResponse> response) {
                TopRatedPlayersResponse topRatedPlayersResponse = response.body();
                callback.next(new ArrayList<>(topRatedPlayersResponse.getTopRatedPlayersPOJO()));
            }

            @Override
            public void onFailure(Call<TopRatedPlayersResponse> call, Throwable t) {
                Log.e("Error with server", t.getMessage());
            }
        });
    }

   /* public MutableLiveData<ArrayList<TopRatedPlayerPOJO>> getTopRatedPlayersPOJO() {
        return topRatedPlayersPOJO;
    }*/

}
