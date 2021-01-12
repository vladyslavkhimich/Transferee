package com.example.transferee.models;

import java.util.Date;

public class Match {
    public Date MatchDate;
    public Club ClubAgainst;
    public int GoalsFor;
    public int GoalsAgainst;
    public boolean IsHomeGame;
    public boolean IsWin = false;
    public boolean IsLoss = false;
    public boolean IsDraw = false;
    public String MatchScore;
    public int PlayerGoals;
    public int PlayerAssists;
    public String PlayerPosition;
    public int YellowCardMinute;
    public int RedCardMinute;
    public int MinutesPlayed;
    public double PlayerMatchRating;

    public Match() {

    }

    public Match(Date matchDate, Club clubAgainst, int goalsFor, int goalsAgainst, boolean isHomeGame, int playerGoals, int playerAssists, String playerPosition, int yellowCardMinute, int redCardMinute, int minutesPlayed, double playerMatchRating) {
        MatchDate = matchDate;
        ClubAgainst = clubAgainst;
        GoalsFor = goalsFor;
        GoalsAgainst = goalsAgainst;
        IsHomeGame = isHomeGame;
        setGameResultForPlayerTeam(goalsFor, goalsAgainst);
        setMatchScore(isHomeGame);
        PlayerGoals = playerGoals;
        PlayerAssists = playerAssists;
        PlayerPosition = playerPosition;
        YellowCardMinute = yellowCardMinute;
        RedCardMinute = redCardMinute;
        MinutesPlayed = minutesPlayed;
        PlayerMatchRating = playerMatchRating;
    }

    public void setGameResultForPlayerTeam(int goalsFor, int goalsAgainst) {
        if (goalsFor > goalsAgainst)
            IsWin = true;
        else if (goalsAgainst > goalsFor)
            IsLoss = true;
        else
            IsDraw = true;
    }

    public void setMatchScore(boolean isHomeGame) {
        if (isHomeGame)
            MatchScore = GoalsFor + " : " + GoalsAgainst;
        else
            MatchScore = GoalsAgainst + " : " + GoalsFor;
    }
}
