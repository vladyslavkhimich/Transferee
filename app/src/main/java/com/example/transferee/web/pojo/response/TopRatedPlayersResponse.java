package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.TopRatedPlayersPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedPlayersResponse {

    @SerializedName("topRatedPlayersPOJO")
    @Expose
    private List<TopRatedPlayersPOJO> topRatedPlayersPOJO;

    public List<TopRatedPlayersPOJO> getTopRatedPlayersPOJO() {
        return topRatedPlayersPOJO;
    }

    public void setTopRatedPlayersPOJO(List<TopRatedPlayersPOJO> topRatedPlayersPOJO) {
        this.topRatedPlayersPOJO = topRatedPlayersPOJO;
    }

    public TopRatedPlayersResponse() {

    }

}
