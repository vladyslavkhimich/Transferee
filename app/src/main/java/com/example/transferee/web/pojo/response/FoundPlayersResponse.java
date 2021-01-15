package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.FoundPlayersPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoundPlayersResponse {

    @SerializedName("foundPlayersPOJO")
    @Expose
    private List<FoundPlayersPOJO> foundPlayersPOJO = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public FoundPlayersResponse() {
    }

    /**
     *
     * @param foundPlayersPOJO
     */
    public FoundPlayersResponse(List<FoundPlayersPOJO> foundPlayersPOJO) {
        super();
        this.foundPlayersPOJO = foundPlayersPOJO;
    }

    public List<FoundPlayersPOJO> getFoundPlayersPOJO() {
        return foundPlayersPOJO;
    }

    public void setFoundPlayersPOJO(List<FoundPlayersPOJO> foundPlayersPOJO) {
        this.foundPlayersPOJO = foundPlayersPOJO;
    }

}