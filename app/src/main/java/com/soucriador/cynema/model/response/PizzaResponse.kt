package br.com.soucriador.cynema.cynema.model.response

import br.com.patrocine.cynema.model.Pizza
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class PizzaResponse {
    @SerializedName("pizzas")
    var results: ArrayList<Pizza>? = null
}