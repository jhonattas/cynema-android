package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Session implements Serializable {

    @SerializedName("start")
    private String start;
    @SerializedName("category")
    private String category;
    @SerializedName("language")
    private String language;

    public Session(){
    }

    public Session(String start, String category, String language) {
        this.start = start;
        this.category = category;
        this.language = language;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
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
}
