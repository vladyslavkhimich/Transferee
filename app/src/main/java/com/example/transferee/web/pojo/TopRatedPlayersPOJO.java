package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopRatedPlayersPOJO {

    @SerializedName("Player_ID")
    @Expose
    private int playerID;
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
    @SerializedName("Average_Rating")
    @Expose
    private double averageRating;

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public TopRatedPlayersPOJO(int playerID, String name, String imageURL, String clubURL, String clubName, double averageRating) {
        this.playerID = playerID;
        this.name = name;
        this.imageURL = imageURL;
        this.clubURL = clubURL;
        this.clubName = clubName;
        this.averageRating = averageRating;
    }

    public TopRatedPlayersPOJO() {

    }
}
