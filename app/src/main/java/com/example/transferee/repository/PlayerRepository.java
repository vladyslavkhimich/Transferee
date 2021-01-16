package com.example.transferee.repository;

import android.util.Log;

import com.example.transferee.helpers.CallbackCustom;
import com.example.transferee.web.RetrofitService;
import com.example.transferee.web.WebService;
import com.example.transferee.web.pojo.FoundPlayersPOJO;
import com.example.transferee.web.pojo.LatestTransfersPOJO;
import com.example.transferee.web.pojo.PlayerMarketPOJO;
import com.example.transferee.web.pojo.PlayerOverviewPOJO;
import com.example.transferee.web.pojo.PlayerPOJO;
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

    public void getPlayerByIdFromServer(CallbackCustom<PlayerPOJO> callback, int id) {
        WebService.getPlayerById(id).enqueue(new Callback<PlayerPOJO>() {
            @Override
            public void onResponse(Call<PlayerPOJO> call, Response<PlayerPOJO> response) {
                if (response.isSuccessful()) {
                    PlayerPOJO playerPOJO = response.body();
                    callback.next(playerPOJO);
                }
            }

            @Override
            public void onFailure(Call<PlayerPOJO> call, Throwable t) {

            }
        });
    }

    public void getPlayerOverviewByIdFromServer(CallbackCustom<PlayerOverviewPOJO> callback, int id) {
        WebService.getPlayerOverviewById(id).enqueue(new Callback<PlayerOverviewPOJO>() {
            @Override
            public void onResponse(Call<PlayerOverviewPOJO> call, Response<PlayerOverviewPOJO> response) {
                if (response.isSuccessful()) {
                    PlayerOverviewPOJO playerOverviewPOJO = response.body();
                    callback.next(playerOverviewPOJO);
                }
            }

            @Override
            public void onFailure(Call<PlayerOverviewPOJO> call, Throwable t) {

            }
        });
    }

    public void getPlayerMarketByIdFromServer(CallbackCustom<PlayerMarketPOJO> callback, int id) {
        WebService.getPlayerMarketById(id).enqueue(new Callback<PlayerMarketPOJO>() {
            @Override
            public void onResponse(Call<PlayerMarketPOJO> call, Response<PlayerMarketPOJO> response) {
                if (response.isSuccessful()) {
                    PlayerMarketPOJO playerMarketPOJO = response.body();
                    callback.next(playerMarketPOJO);
                }
            }

            @Override
            public void onFailure(Call<PlayerMarketPOJO> call, Throwable t) {

            }
        });
    }

}
