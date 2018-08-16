package br.com.patrocine.patrocine.model.response;

import com.google.gson.annotations.SerializedName;
import br.com.patrocine.patrocine.model.Snack;

import java.util.ArrayList;
import java.util.List;

public class SnackResponse {

    @SerializedName("snacks")
    private ArrayList<Snack> results;

    public SnackResponse(
            ArrayList<Snack> results
    ){
        setResults(results);
    }

    public ArrayList<Snack> getResults(){
        return results;
    }

    public void setResults(ArrayList<Snack> results){
        this.results = results;
    }
}
