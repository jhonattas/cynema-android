package com.soucriador.cynema.model.response

import com.soucriador.cynema.model.Pizza
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class PizzaResponse {
    @SerializedName("pizzas")
    var results: ArrayList<Pizza>? = null
}