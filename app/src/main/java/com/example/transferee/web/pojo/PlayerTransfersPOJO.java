package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerTransfersPOJO {

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
    private Double transferPrice;
    @SerializedName("Contract_Start_Date")
    @Expose
    private String contractStartDate;
    @SerializedName("Contract_Finish_Date")
    @Expose
    private String contractFinishDate;
    @SerializedName("Market_Value")
    @Expose
    private Double marketValue;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerTransfersPOJO() {
    }

    /**
     *
     * @param dateOfTransfer
     * @param joiningClubURL
     * @param departureClubName
     * @param joiningClubName
     * @param contractStartDate
     * @param contractFinishDate
     * @param departureClubURL
     * @param marketValue
     * @param transferPrice
     */
    public PlayerTransfersPOJO(String dateOfTransfer, String departureClubName, String departureClubURL, String joiningClubName, String joiningClubURL, Double transferPrice, String contractStartDate, String contractFinishDate, Double marketValue) {
        super();
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

    public Double getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(Double transferPrice) {
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

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

}
