package com.example.transferee.models;

public class TopRatedPlayer {
    public Player Player;
    public double Rating;
    public Club Club;
    public TopRatedPlayer(Player player, double rating, Club club) {
        Player = player;
        Rating = rating;
        Club = club;
    }
}
