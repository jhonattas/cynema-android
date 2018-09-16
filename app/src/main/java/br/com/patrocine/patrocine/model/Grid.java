package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Grid implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("movie_id")
    private int MovieId;
    @SerializedName("date")
    private String date;
    @SerializedName("room")
    private String room;
    @SerializedName("category")
    private String category;
    @SerializedName("language")
    private String language;
    @SerializedName("day")
    private String day;
    @SerializedName("hour")
    private String hour;

    public Grid(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
