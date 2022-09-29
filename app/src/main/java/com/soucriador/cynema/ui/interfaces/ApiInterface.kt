package com.soucriador.cynema.ui.interfaces

import com.soucriador.cynema.model.Layout
import com.soucriador.cynema.model.Movie
import com.soucriador.cynema.model.Partners
import com.soucriador.cynema.model.response.FeaturedImagesResponse
import com.soucriador.cynema.model.response.PizzaResponse
import com.soucriador.cynema.model.response.SnackResponse
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