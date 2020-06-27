package com.cinemabooking;

public class Show {
    private String movieName;
    private Theater theater;
    private int date;
    private int duration;
    private int playTime;

    public Show(String movieName, int date, int playTime, Theater theater) {
        this.movieName = movieName;
        this.date = date;
        this.playTime = playTime;
        this.theater = theater;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getDuration() {
        return duration;
    }

    public int getShowTime() {
        return playTime;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
