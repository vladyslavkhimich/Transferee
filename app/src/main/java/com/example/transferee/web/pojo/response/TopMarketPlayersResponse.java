package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.TopMarketValuePlayersPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopMarketPlayersResponse {
    @SerializedName("topMarketValuePlayersPOJO")
    @Expose
    private List<TopMarketValuePlayersPOJO> topMarketValuePlayersPOJO = null;

    /**
     * No args constructor for use in serialization
     */
    public TopMarketPlayersResponse() {
    }

    /**
     * @param topMarketValuePlayersPOJO
     */
    public TopMarketPlayersResponse(List<TopMarketValuePlayersPOJO> topMarketValuePlayersPOJO) {
        super();
        this.topMarketValuePlayersPOJO = topMarketValuePlayersPOJO;
    }

    public List<TopMarketValuePlayersPOJO> getTopMarketValuePlayersPOJO() {
        return topMarketValuePlayersPOJO;
    }

    public void setTopMarketValuePlayersPOJO(List<TopMarketValuePlayersPOJO> topMarketValuePlayersPOJO) {
        this.topMarketValuePlayersPOJO = topMarketValuePlayersPOJO;
    }
}
