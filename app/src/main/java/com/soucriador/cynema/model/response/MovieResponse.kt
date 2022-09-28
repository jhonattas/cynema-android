package br.com.soucriador.cynema.cynema.model.response

import br.com.patrocine.cynema.model.Movie
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class MovieResponse {
    @SerializedName("movies")
    var results: ArrayList<Movie>? = null
}