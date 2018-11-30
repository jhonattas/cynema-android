package br.com.patrocine.patrocine.ui.interfaces

import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.model.response.PizzaResponse
import br.com.patrocine.patrocine.model.response.SnackResponse
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

interface ApiInterface {

    @get:GET("pizzas")
    val allPizzas: Call<PizzaResponse>

    @get:GET("lanches")
    val allSnacks: Call<SnackResponse>

    @get:GET("v1/movies")
    val allMovies: Call<ArrayList<Movie>>

    @get:GET("v1/movies_soon")
    val allMoviesSoon: Call<ArrayList<Movie>>
}