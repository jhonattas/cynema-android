package br.com.patrocine.patrocine.model.response

import br.com.patrocine.patrocine.model.Movie
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class MovieResponse {
    @SerializedName("movies")
    var results: ArrayList<Movie>? = null
}