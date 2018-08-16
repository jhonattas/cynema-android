package br.com.patrocine.patrocine.model.response;

import com.google.gson.annotations.SerializedName;
import br.com.patrocine.patrocine.model.Pizza;
import java.util.ArrayList;

public class PizzaResponse {

    @SerializedName("pizzas")
    private ArrayList<Pizza> results;

    public PizzaResponse(
            ArrayList<Pizza> results
    ){
        setResults(results);
    }

    public ArrayList<Pizza> getResults(){
        return results;
    }

    public void setResults(ArrayList<Pizza> results){
        this.results = results;
    }

}
