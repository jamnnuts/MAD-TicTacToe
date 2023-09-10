package com.example.mad_tictactoe;

/*
 Player class which holds all numerical data pertaining to the player.
 */
public class Player{
    public String playerName;
    public int gamesPlayed;
    public int wins;
    public int losses;
    public int draws;
    public Player() {
        this.playerName = "default";
        this.gamesPlayed = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public String getPlayerName() {return playerName;}
    public int getDraws() {return draws;}
    public int getGamesPlayed() {return gamesPlayed;}
    public int getLosses() {return losses;}
    public int getWins() {return wins;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}
    public void setDraws(int draws) {this.draws = draws;}
    public void setGamesPlayed(int gamesPlayed) {this.gamesPlayed = gamesPlayed;}
    public void setLosses(int losses) {this.losses = losses;}
    public void setWins(int wins) {this.wins = wins;}
}