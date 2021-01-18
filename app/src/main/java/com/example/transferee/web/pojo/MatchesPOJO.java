package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchesPOJO {

    @SerializedName("Match_Date")
    @Expose
    private String matchDate;
    @SerializedName("Club_URL")
    @Expose
    private String clubURL;
    @SerializedName("Club_Name")
    @Expose
    private String clubName;
    @SerializedName("Match_Score")
    @Expose
    private String matchScore;
    @SerializedName("Is_Win")
    @Expose
    private Boolean isWin;
    @SerializedName("Is_Loss")
    @Expose
    private Boolean isLoss;
    @SerializedName("Yellow_Card")
    @Expose
    private Integer yellowCard;
    @SerializedName("Red_Card")
    @Expose
    private Integer redCard;
    @SerializedName("Minutes_Played")
    @Expose
    private Integer minutesPlayed;
    @SerializedName("Player_Goals")
    @Expose
    private Integer playerGoals;
    @SerializedName("Player_Assists")
    @Expose
    private Integer playerAssists;
    @SerializedName("Player_Rating")
    @Expose
    private Double playerRating;
    @SerializedName("Match_Position")
    @Expose
    private String matchPosition;

    /**
     * No args constructor for use in serialization
     *
     */
    public MatchesPOJO() {
    }

    /**
     *
     * @param matchScore
     * @param matchDate
     * @param clubName
     * @param minutesPlayed
     * @param isLoss
     * @param yellowCard
     * @param playerAssists
     * @param playerRating
     * @param clubURL
     * @param isWin
     * @param playerGoals
     */
    public MatchesPOJO(String matchDate, String clubURL, String clubName, String matchScore, Boolean isWin, Boolean isLoss, Integer yellowCard, Integer minutesPlayed, Integer playerGoals, Integer playerAssists, Double playerRating) {
        super();
        this.matchDate = matchDate;
        this.clubURL = clubURL;
        this.clubName = clubName;
        this.matchScore = matchScore;
        this.isWin = isWin;
        this.isLoss = isLoss;
        this.yellowCard = yellowCard;
        this.minutesPlayed = minutesPlayed;
        this.playerGoals = playerGoals;
        this.playerAssists = playerAssists;
        this.playerRating = playerRating;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getClubURL() {
        return clubURL;
    }

    public void setClubURL(String clubURL) {
        this.clubURL = clubURL;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public Boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(Boolean isWin) {
        this.isWin = isWin;
    }

    public Boolean getIsLoss() {
        return isLoss;
    }

    public void setIsLoss(Boolean isLoss) {
        this.isLoss = isLoss;
    }

    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }

    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public Integer getPlayerGoals() {
        return playerGoals;
    }

    public void setPlayerGoals(Integer playerGoals) {
        this.playerGoals = playerGoals;
    }

    public Integer getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(Integer playerAssists) {
        this.playerAssists = playerAssists;
    }

    public Double getPlayerRating() {
        return playerRating;
    }

    public void setPlayerRating(Double playerRating) {
        this.playerRating = playerRating;
    }
    public String getMatchPosition() {
        return matchPosition;
    }

    public void setMatchPosition(String matchPosition) {
        this.matchPosition = matchPosition;
    }

}
