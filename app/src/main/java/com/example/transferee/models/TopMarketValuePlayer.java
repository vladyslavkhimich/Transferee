package com.example.transferee.models;

public class TopMarketValuePlayer {
    public Player Player;
    public double MarketValue;
    public Club Club;
    public TopMarketValuePlayer(Player player, double marketValue, Club club) {
        Player = player;
        MarketValue = marketValue;
        Club = club;
    }
}
