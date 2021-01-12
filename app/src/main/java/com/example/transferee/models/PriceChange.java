package com.example.transferee.models;

import java.util.Date;

public class PriceChange {
    public double PreviousPrice;
    public double NewPrice;
    public boolean IsRise;
    public Club Club;
    public Date PriceChangeDate;

    public PriceChange(double previousPrice, double newPrice, Club club, Date priceChangeDate) {
        PreviousPrice = previousPrice;
        NewPrice = newPrice;
        setRise(newPrice > previousPrice);
        Club = club;
        PriceChangeDate = priceChangeDate;
    }

    public void setRise(boolean rise) {
        IsRise = rise;
    }
}
