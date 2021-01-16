package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceChangesPOJO {

    @SerializedName("Change_Date")
    @Expose
    private String changeDate;
    @SerializedName("Previous_Price")
    @Expose
    private Object previousPrice;
    @SerializedName("Club_URL")
    @Expose
    private String clubURL;
    @SerializedName("Is_Rise")
    @Expose
    private Object isRise;
    @SerializedName("New_Price")
    @Expose
    private Double newPrice;

    /**
     * No args constructor for use in serialization
     *
     */
    public PriceChangesPOJO() {
    }

    /**
     *
     * @param changeDate
     * @param previousPrice
     * @param clubURL
     * @param isRise
     * @param newPrice
     */
    public PriceChangesPOJO(String changeDate, Object previousPrice, String clubURL, Object isRise, Double newPrice) {
        super();
        this.changeDate = changeDate;
        this.previousPrice = previousPrice;
        this.clubURL = clubURL;
        this.isRise = isRise;
        this.newPrice = newPrice;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public Object getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(Object previousPrice) {
        this.previousPrice = previousPrice;
    }

    public String getClubURL() {
        return clubURL;
    }

    public void setClubURL(String clubURL) {
        this.clubURL = clubURL;
    }

    public Object getIsRise() {
        return isRise;
    }

    public void setIsRise(Object isRise) {
        this.isRise = isRise;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

}
