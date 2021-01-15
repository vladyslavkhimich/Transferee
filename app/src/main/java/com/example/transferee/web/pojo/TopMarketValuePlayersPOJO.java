package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopMarketValuePlayersPOJO {
    @SerializedName("Player_ID")
    @Expose
    private Integer playerID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image_URL")
    @Expose
    private String imageURL;
    @SerializedName("Club_URL")
    @Expose
    private String clubURL;
    @SerializedName("Club_Name")
    @Expose
    private String clubName;
    @SerializedName("Market_Price")
    @Expose
    private double marketPrice;

    /**
     * No args constructor for use in serialization
     *
     */
    public TopMarketValuePlayersPOJO() {
    }

    /**
     *
     * @param marketPrice
     * @param imageURL
     * @param clubName
     * @param name
     * @param clubURL
     * @param playerID
     */
    public TopMarketValuePlayersPOJO(Integer playerID, String name, String imageURL, String clubURL, String clubName, Integer marketPrice) {
        super();
        this.playerID = playerID;
        this.name = name;
        this.imageURL = imageURL;
        this.clubURL = clubURL;
        this.clubName = clubName;
        this.marketPrice = marketPrice;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }
}
