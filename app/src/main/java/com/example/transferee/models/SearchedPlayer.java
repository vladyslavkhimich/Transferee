package com.example.transferee.models;

public class SearchedPlayer {
    public Player Player;
    public Club Club;
    public Country Country;

    public SearchedPlayer(Player player, Club club, Country country) {
        Player = player;
        Club = club;
        Country = country;
    }
}
