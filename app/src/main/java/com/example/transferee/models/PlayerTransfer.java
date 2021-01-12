package com.example.transferee.models;

import java.util.Date;

public class PlayerTransfer {
    public Date TransferDate;
    public Club DepartureClub;
    public Club JoiningClub;
    public double Fee;
    public Date StartContractDate;
    public Date EndContractDate;
    public double MarketValue;

    public PlayerTransfer(Date transferDate, Club departureClub, Club joiningClub, double fee, Date startContractDate, Date endContractDate, double marketValue) {
        TransferDate = transferDate;
        DepartureClub = departureClub;
        JoiningClub = joiningClub;
        Fee = fee;
        StartContractDate = startContractDate;
        EndContractDate = endContractDate;
        MarketValue = marketValue;
    }
}
