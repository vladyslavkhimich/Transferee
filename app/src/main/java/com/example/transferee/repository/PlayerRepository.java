package com.example.transferee.repository;

import android.util.Log;

import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.WebService;
import com.example.transferee.web.pojo.FoundPlayersPOJO;
import com.example.transferee.web.pojo.LatestTransfersPOJO;
import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.example.transferee.web.pojo.response.FoundPlayersResponse;
import com.example.transferee.web.pojo.response.LatestTransfersResponse;
import com.example.transferee.web.pojo.response.TopMarketPlayersResponse;
import com.example.transferee.web.pojo.TopMarketValuePlayersPOJO;
import com.example.transferee.web.pojo.response.TopRatedPlayersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerRepository {
    private static PlayerRepository instance = null;
    private static WebService WebService;

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
                if (response.isSuccessful()) {
                    TopRatedPlayersResponse topRatedPlayersResponse = response.body();
                    callback.next(new ArrayList<>(topRatedPlayersResponse.getTopRatedPlayersPOJO()));
                }
            }

            @Override
            public void onFailure(Call<TopRatedPlayersResponse> call, Throwable t) {
                Log.e("Error with server", t.getMessage());
            }
        });
    }

    public void getTopMarketValuePlayersFromServer(CallbackCustom<List<TopMarketValuePlayersPOJO>> callback) {
        WebService.getTopMarketValuePlayers().enqueue(new Callback<TopMarketPlayersResponse>() {
            @Override
            public void onResponse(Call<TopMarketPlayersResponse> call, Response<TopMarketPlayersResponse> response) {
                if (response.isSuccessful()) {
                    TopMarketPlayersResponse topMarketPlayersResponse = response.body();
                    callback.next(new ArrayList<>(topMarketPlayersResponse != null ? topMarketPlayersResponse.getTopMarketValuePlayersPOJO(): null));
                }
            }

            @Override
            public void onFailure(Call<TopMarketPlayersResponse> call, Throwable t) {

            }
        });
    }
    
    public void getLatestTransfersFromServer(CallbackCustom<List<LatestTransfersPOJO>> callback) {
        WebService.getLatestTransfers().enqueue(new Callback<LatestTransfersResponse>() {
            @Override
            public void onResponse(Call<LatestTransfersResponse> call, Response<LatestTransfersResponse> response) {
                if (response.isSuccessful()) {
                    LatestTransfersResponse latestTransfersResponse = response.body();
                    callback.next(new ArrayList<>(latestTransfersResponse != null ? latestTransfersResponse.getLatestTransfersPOJO() : null));
                }
            }

            @Override
            public void onFailure(Call<LatestTransfersResponse> call, Throwable t) {

            }
        });
    }

    public void findPlayersByNameOnServer(CallbackCustom<List<FoundPlayersPOJO>> callback, String searchText) {
        WebService.findPlayers(searchText).enqueue(new Callback<FoundPlayersResponse>() {
            @Override
            public void onResponse(Call<FoundPlayersResponse> call, Response<FoundPlayersResponse> response) {
                if (response.code() == 404)
                    callback.next(null);
                if (response.isSuccessful()) {
                    FoundPlayersResponse foundPlayersResponse = response.body();
                    callback.next(new ArrayList<>(foundPlayersResponse != null ? foundPlayersResponse.getFoundPlayersPOJO() : null));
                }
            }

            @Override
            public void onFailure(Call<FoundPlayersResponse> call, Throwable t) {

            }
        });
    }

}