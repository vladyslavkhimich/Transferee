package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.LatestTransfersPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatestTransfersResponse {

    @SerializedName("latestTransfersPOJO")
    @Expose
    private List<LatestTransfersPOJO> latestTransfersPOJO = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public LatestTransfersResponse() {
    }

    /**
     *
     * @param latestTransfersPOJO
     */
    public LatestTransfersResponse(List<LatestTransfersPOJO> latestTransfersPOJO) {
        super();
        this.latestTransfersPOJO = latestTransfersPOJO;
    }

    public List<LatestTransfersPOJO> getLatestTransfersPOJO() {
        return latestTransfersPOJO;
    }

    public void setLatestTransfersPOJO(List<LatestTransfersPOJO> latestTransfersPOJO) {
        this.latestTransfersPOJO = latestTransfersPOJO;
    }

}
