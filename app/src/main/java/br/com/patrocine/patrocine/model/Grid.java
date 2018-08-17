package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {

    @SerializedName("day")
    private int day;
    @SerializedName("monthNumber")
    private int monthNumber;
    @SerializedName("weekDay")
    private String weekDay;
    @SerializedName("locations")
    private ArrayList<Location> locations;

    public Grid(){
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
