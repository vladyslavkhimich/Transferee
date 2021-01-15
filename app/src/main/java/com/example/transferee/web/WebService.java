package com.example.transferee.web;


import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.example.transferee.web.pojo.response.LatestTransfersResponse;
import com.example.transferee.web.pojo.response.TopMarketPlayersResponse;
import com.example.transferee.web.pojo.response.TopRatedPlayersResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("player/gettoprated")
    Call<TopRatedPlayersResponse> getTopRatedPlayers();
    @GET("player/gettopmarket")
    Call<TopMarketPlayersResponse> getTopMarketValuePlayers();
    @GET("player/gettransfers")
    Call<LatestTransfersResponse> getLatestTransfers();
}
