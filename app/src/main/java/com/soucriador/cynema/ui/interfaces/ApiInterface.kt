package br.com.soucriador.cynema.cynema.ui.interfaces

import br.com.patrocine.cynema.model.Layout
import br.com.patrocine.cynema.model.Movie
import br.com.patrocine.cynema.model.Partners
import br.com.patrocine.cynema.model.response.FeaturedImagesResponse
import br.com.patrocine.cynema.model.response.PizzaResponse
import br.com.patrocine.cynema.model.response.SnackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("pizzas")
    fun allPizzas(): Call<PizzaResponse>

    @GET("lanches")
    fun allSnacks(): Call<SnackResponse>

    @GET("v1/movies")
    fun allMovies(): Call<ArrayList<Movie>>

    @GET("v1/movie_name/{id}")
    fun oneMovie(@Path("id") id: String): Call<Movie>

    @GET("v1/movies_soon")
    fun allMoviesSoon(): Call<ArrayList<Movie>>

    @GET("v1/layout/config")
    fun config(): Call<Layout>

    @GET("v1/layout/slides")
    fun featuredImages(): Call<FeaturedImagesResponse>

    @GET("v1/partners")
    fun allPartners(): Call<ArrayList<Partners>>
}