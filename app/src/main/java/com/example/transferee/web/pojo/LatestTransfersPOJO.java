package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestTransfersPOJO {

    @SerializedName("Player_ID")
    @Expose
    private Integer playerID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image_URL")
    @Expose
    private String imageURL;
    @SerializedName("Date_Of_Transfer")
    @Expose
    private String dateOfTransfer;
    @SerializedName("Departure_Club_Name")
    @Expose
    private String departureClubName;
    @SerializedName("Departure_Club_URL")
    @Expose
    private String departureClubURL;
    @SerializedName("Joining_Club_Name")
    @Expose
    private String joiningClubName;
    @SerializedName("Joining_Club_URL")
    @Expose
    private String joiningClubURL;
    @SerializedName("Transfer_Price")
    @Expose
    private Integer transferPrice;
    @SerializedName("Contract_Start_Date")
    @Expose
    private String contractStartDate;
    @SerializedName("Contract_Finish_Date")
    @Expose
    private String contractFinishDate;
    @SerializedName("Market_Value")
    @Expose
    private double marketValue;

    /**
     * No args constructor for use in serialization
     *
     */
    public LatestTransfersPOJO() {
    }

    /**
     *
     * @param dateOfTransfer
     * @param joiningClubURL
     * @param departureClubName
     * @param joiningClubName
     * @param contractStartDate
     * @param contractFinishDate
     * @param imageURL
     * @param departureClubURL
     * @param name
     * @param marketValue
     * @param playerID
     * @param transferPrice
     */
    public LatestTransfersPOJO(Integer playerID, String name, String imageURL, String dateOfTransfer, String departureClubName, String departureClubURL, String joiningClubName, String joiningClubURL, Integer transferPrice, String contractStartDate, String contractFinishDate, Integer marketValue) {
        super();
        this.playerID = playerID;
        this.name = name;
        this.imageURL = imageURL;
        this.dateOfTransfer = dateOfTransfer;
        this.departureClubName = departureClubName;
        this.departureClubURL = departureClubURL;
        this.joiningClubName = joiningClubName;
        this.joiningClubURL = joiningClubURL;
        this.transferPrice = transferPrice;
        this.contractStartDate = contractStartDate;
        this.contractFinishDate = contractFinishDate;
        this.marketValue = marketValue;
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

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(String dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getDepartureClubName() {
        return departureClubName;
    }

    public void setDepartureClubName(String departureClubName) {
        this.departureClubName = departureClubName;
    }

    public String getDepartureClubURL() {
        return departureClubURL;
    }

    public void setDepartureClubURL(String departureClubURL) {
        this.departureClubURL = departureClubURL;
    }

    public String getJoiningClubName() {
        return joiningClubName;
    }

    public void setJoiningClubName(String joiningClubName) {
        this.joiningClubName = joiningClubName;
    }

    public String getJoiningClubURL() {
        return joiningClubURL;
    }

    public void setJoiningClubURL(String joiningClubURL) {
        this.joiningClubURL = joiningClubURL;
    }

    public Integer getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(Integer transferPrice) {
        this.transferPrice = transferPrice;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractFinishDate() {
        return contractFinishDate;
    }

    public void setContractFinishDate(String contractFinishDate) {
        this.contractFinishDate = contractFinishDate;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Integer marketValue) {
        this.marketValue = marketValue;
    }

}
