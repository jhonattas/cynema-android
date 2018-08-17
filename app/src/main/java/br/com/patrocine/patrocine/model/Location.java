package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("sessions")
    private ArrayList<Session> sessions;

    public Location(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
