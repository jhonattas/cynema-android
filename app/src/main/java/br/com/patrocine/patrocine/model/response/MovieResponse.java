package br.com.patrocine.patrocine.model.response;

import com.google.gson.annotations.SerializedName;
import br.com.patrocine.patrocine.model.Movie;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("movies")
    private ArrayList<Movie> results;


    public MovieResponse(ArrayList<Movie> results){
        setResults(results);
    }

    public ArrayList<Movie> getResults(){
        return results;
    }

    public void setResults(ArrayList<Movie> results){
        this.results = results;
    }

}
