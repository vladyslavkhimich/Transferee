package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.PlayerTransfersPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerCareerResponse {

    @SerializedName("playerTransfersPOJO")
    @Expose
    private List<PlayerTransfersPOJO> playerTransfersPOJO = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerCareerResponse() {
    }

    /**
     *
     * @param playerTransfersPOJO
     */
    public PlayerCareerResponse(List<PlayerTransfersPOJO> playerTransfersPOJO) {
        super();
        this.playerTransfersPOJO = playerTransfersPOJO;
    }

    public List<PlayerTransfersPOJO> getPlayerTransfersPOJO() {
        return playerTransfersPOJO;
    }

    public void setPlayerTransfersPOJO(List<PlayerTransfersPOJO> playerTransfersPOJO) {
        this.playerTransfersPOJO = playerTransfersPOJO;
    }

}
