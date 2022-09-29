package com.soucriador.cynema.model.response

import com.soucriador.cynema.model.Movie
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class MovieResponse {
    @SerializedName("movies")
    var results: ArrayList<Movie>? = null
}