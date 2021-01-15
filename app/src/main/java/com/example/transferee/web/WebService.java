package com.example.transferee.web;


import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.example.transferee.web.pojo.response.FoundPlayersResponse;
import com.example.transferee.web.pojo.response.LatestTransfersResponse;
import com.example.transferee.web.pojo.response.TopMarketPlayersResponse;
import com.example.transferee.web.pojo.response.TopRatedPlayersResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {
    @GET("player/gettoprated")
    Call<TopRatedPlayersResponse> getTopRatedPlayers();
    @GET("player/gettopmarket")
    Call<TopMarketPlayersResponse> getTopMarketValuePlayers();
    @GET("player/gettransfers")
    Call<LatestTransfersResponse> getLatestTransfers();
    @GET("player/getplayersbyname")
    Call<FoundPlayersResponse> findPlayers(@Query("searchText") String searchText);
}
