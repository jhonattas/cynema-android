package br.com.patrocine.patrocine.model.response

import br.com.patrocine.patrocine.model.Pizza
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class PizzaResponse {
    @SerializedName("pizzas")
    var results: ArrayList<Pizza>? = null
}