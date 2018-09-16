package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("full_title")
    private String fullTitle;
    @SerializedName("sinopse")
    private String sinopse;
    @SerializedName("duration")
    private int duration;
    @SerializedName("genre")
    private String genre;
    @SerializedName("censorship")
    private String censorship;
    @SerializedName("image")
    private String image;
    @SerializedName("image_mini")
    private String image_mini;
    @SerializedName("slide01")
    private String slide01;
    @SerializedName("slide02")
    private String slide02;
    @SerializedName("slide03")
    private String slide03;
    @SerializedName("trailer")
    private String trailer;
    @SerializedName("sessions")
    private ArrayList<Grid> grid;

    public Movie(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCensorship() {
        return censorship;
    }

    public void setCensorship(String censorship) {
        this.censorship = censorship;
    }

    public String getImage() {
        this.image = "https://api.patrocine.com.br/v1/static/images/covers/" + this.image;
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_mini() {
        return image_mini;
    }

    public void setImage_mini(String image_mini) {
        this.image_mini = image_mini;
    }

    public String getSlide01() {
        return slide01;
    }

    public void setSlide01(String slide01) {
        this.slide01 = slide01;
    }

    public String getSlide02() {
        return slide02;
    }

    public void setSlide02(String slide02) {
        this.slide02 = slide02;
    }

    public String getSlide03() {
        return slide03;
    }

    public void setSlide03(String slide03) {
        this.slide03 = slide03;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public ArrayList<Grid> getGrid() {
        return grid;
    }

    public void setGrid(ArrayList<Grid> grid) {
        this.grid = grid;
    }
}
