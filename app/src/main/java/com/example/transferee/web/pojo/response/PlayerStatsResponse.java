package com.example.transferee.web.pojo.response;

import com.example.transferee.web.pojo.MatchesPOJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerStatsResponse {

    @SerializedName("Total_Goals")
    @Expose
    private Integer totalGoals;
    @SerializedName("Total_Assists")
    @Expose
    private Integer totalAssists;
    @SerializedName("Total_Rating")
    @Expose
    private Double totalRating;
    @SerializedName("matchesPOJO")
    @Expose
    private List<MatchesPOJO> matchesPOJO = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerStatsResponse() {
    }

    /**
     *
     * @param matchesPOJO
     * @param totalAssists
     * @param totalRating
     * @param totalGoals
     */
    public PlayerStatsResponse(Integer totalGoals, Integer totalAssists, Double totalRating, List<MatchesPOJO> matchesPOJO) {
        super();
        this.totalGoals = totalGoals;
        this.totalAssists = totalAssists;
        this.totalRating = totalRating;
        this.matchesPOJO = matchesPOJO;
    }

    public Integer getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(Integer totalGoals) {
        this.totalGoals = totalGoals;
    }

    public Integer getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(Integer totalAssists) {
        this.totalAssists = totalAssists;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public List<MatchesPOJO> getMatchesPOJO() {
        return matchesPOJO;
    }

    public void setMatchesPOJO(List<MatchesPOJO> matchesPOJO) {
        this.matchesPOJO = matchesPOJO;
    }

}
