package br.com.patrocine.patrocine.ui.interfaces;

import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.model.response.PizzaResponse;
import br.com.patrocine.patrocine.model.response.SnackResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("pizzas")
    Call<PizzaResponse> getAllPizzas();

    @GET("lanches")
    Call<SnackResponse> getAllSnacks();

    @GET("v1/movies")
    Call<ArrayList<Movie>> getAllMovies();

    @GET("v1/movies_soon")
    Call<ArrayList<Movie>> getAllMoviesSoon();
}
