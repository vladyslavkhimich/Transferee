package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerMarketPOJO {

    @SerializedName("Current_Price")
    @Expose
    private double currentPrice;
    @SerializedName("Last_Price_Change")
    @Expose
    private String lastPriceChange;
    @SerializedName("Price_Changes_POJO")
    @Expose
    private List<PriceChangesPOJO> priceChangesPOJO = null;
    @SerializedName("Worldwide_Price")
    @Expose
    private Integer worldwidePrice;
    @SerializedName("Country_Price")
    @Expose
    private Integer countryPrice;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerMarketPOJO() {
    }

    /**
     *
     * @param worldwidePrice
     * @param currentPrice
     * @param countryPrice
     * @param lastPriceChange
     * @param priceChangesPOJO
     */
    public PlayerMarketPOJO(double currentPrice, String lastPriceChange, List<PriceChangesPOJO> priceChangesPOJO, Integer worldwidePrice, Integer countryPrice) {
        super();
        this.currentPrice = currentPrice;
        this.lastPriceChange = lastPriceChange;
        this.priceChangesPOJO = priceChangesPOJO;
        this.worldwidePrice = worldwidePrice;
        this.countryPrice = countryPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getLastPriceChange() {
        return lastPriceChange;
    }

    public void setLastPriceChange(String lastPriceChange) {
        this.lastPriceChange = lastPriceChange;
    }

    public List<PriceChangesPOJO> getPriceChangesPOJO() {
        return priceChangesPOJO;
    }

    public void setPriceChangesPOJO(List<PriceChangesPOJO> priceChangesPOJO) {
        this.priceChangesPOJO = priceChangesPOJO;
    }

    public Integer getWorldwidePrice() {
        return worldwidePrice;
    }

    public void setWorldwidePrice(Integer worldwidePrice) {
        this.worldwidePrice = worldwidePrice;
    }

    public Integer getCountryPrice() {
        return countryPrice;
    }

    public void setCountryPrice(Integer countryPrice) {
        this.countryPrice = countryPrice;
    }

}
